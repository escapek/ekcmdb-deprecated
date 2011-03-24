package org.escapek.ekcmdb.services

import org.osgi.framework.{ ServiceListener, ServiceEvent, ServiceReference, BundleContext, BundleActivator }
import org.osgi.service.http.HttpService
import org.osgi.util.tracker.ServiceTracker
import java.util.Dictionary
import java.util.Hashtable
import com.sun.jersey.spi.container.servlet.ServletContainer
import akka.actor.{Actor, ActorRef}
import org.escapek.ekcmdb.core.tools.Logging
import javax.ws.rs.core.{Application}

/**
 * Created by IntelliJ IDEA.
 * User: nico
 * Date: 13/03/11
 * Time: 16:38
 */

class EKcmdbServicesActivator extends BundleActivator with Logging {
  var httpTracker: ServiceTracker = _
  var context: BundleContext = _

  var eventAdminActor : ActorRef = _

  // Implicit conversion from function to ServiceListener
  implicit def function2ServiceListener(f: ServiceEvent => Unit) =
    new ServiceListener {
      def serviceChanged(event: ServiceEvent) = f(event)
    }

  def start(ctx: BundleContext) =
    {
      context = ctx
      
      eventAdminActor = Actor.actorOf(new EventAdminActor(context))
      eventAdminActor.start
      logger.debug("ApplicationEventAdmin actor started")

      //Track HTTPService
      httpTracker = new ServiceTracker(context, classOf[HttpService].getName, null)
      httpTracker.open

      // Add RS-Application services listener
      // When new RS-Application are added, handleEvent is called
      val filter = "(objectClass=" + classOf[Application].getName + ")"
      context.addServiceListener(
        (ev: ServiceEvent) => handleEvent(ev),
        filter)

      // Deploy currently registered JAX-RS Applications
      val refs : Array[ServiceReference] = context.getServiceReferences(null, filter)
      if(refs != null)
      {
        for (ref <- context.getServiceReferences(null, filter))
        {
        	handleEvent(new ServiceEvent(ServiceEvent.REGISTERED, ref))
        }
      }
    }

  def stop(ctx: BundleContext) = {
	  httpTracker.close
  }
  
  /**
   * Handle RS-Application Service Event
   */
  private def handleEvent(event: ServiceEvent) = {
	  logger.debug("ServiceEvent : {}", event)
    event.getType match {
      case ServiceEvent.REGISTERED => register(event.getServiceReference)
      case ServiceEvent.UNREGISTERING => unregister(event.getServiceReference)
      case _ => println("Unhandled event")
    }
  }

  /**
   * Register a JAX-RS application on every HTTPService  
   */
  private def register(sr: ServiceReference) = {
    val app: Application = context.getService(sr).asInstanceOf[Application]
    val contextRoot: String = sr.getProperty(EKcmdbServicesActivator.ServletContext).asInstanceOf[String]
    logger.info("Registering JAX-RS application %s with contextRoot %s".format(app.getClass.getName(), contextRoot))

    val jerseyServletParams: Dictionary[String, String] = new Hashtable[String, String]()
    jerseyServletParams.put("javax.ws.rs.Application", app.getClass().getName())

    for (obj <- httpTracker.getServices) {
      val httpService: HttpService = obj.asInstanceOf[HttpService]
      httpService.registerServlet(contextRoot, new ServletContainer(), jerseyServletParams, null)
      logger.info("Servlet deployed")
    }
    eventAdminActor ! ApplicationDeployed(sr)
  }

  /**
   * Un-Register a JAX-RS application on every HTTPService  
   */
  private def unregister(sr: ServiceReference) = {
    val contextRoot: String = sr.getProperty(EKcmdbServicesActivator.ServletContext).asInstanceOf[String]

    for (obj <- httpTracker.getServices) {
      val httpService: HttpService = obj.asInstanceOf[HttpService]
      httpService.unregister(contextRoot)
    }

  }
}

object EKcmdbServicesActivator {
  val ServletContext = "org.escapek.ekcmdb.services.servletContext"
}