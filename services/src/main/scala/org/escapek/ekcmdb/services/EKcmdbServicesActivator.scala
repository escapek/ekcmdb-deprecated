package org.escapek.ekcmdb.services

import org.osgi.framework.{ServiceListener, ServiceEvent, ServiceReference, BundleContext, BundleActivator}
import org.osgi.service.http.HttpService
import org.osgi.util.tracker.ServiceTracker
import java.util.Dictionary
import java.util.Hashtable
import com.sun.jersey.spi.container.servlet.ServletContainer

/**
 * Created by IntelliJ IDEA.
 * User: nico
 * Date: 13/03/11
 * Time: 16:38
 */

abstract class EKcmdbServicesActivator extends BundleActivator
{
	var httpTracker : ServiceTracker
	var context : BundleContext
	
	// Implicit conversion from function to ServiceListener
	implicit def function2ServiceListener(f: ServiceEvent => Unit) =
		new ServiceListener {
			def serviceChanged(event: ServiceEvent) = f(event)
		}

	def start(ctx: BundleContext) =
	{
		context = ctx
		
		httpTracker = new ServiceTracker(context, classOf[HttpService].getName, null)
		httpTracker.open
		
		// Add RS-Application services listener
		val filter = "objectClass=" + classOf[Application].getName + ")"
		context.addServiceListener(
				(ev:ServiceEvent) => handleEvent(ev), 
				filter)
		
		for(ref <- context.getServiceReferences(null, filter))
		{
			handleEvent(new ServiceEvent(ServiceEvent.REGISTERED, ref))
		}
	}

	def handleEvent(event : ServiceEvent) = {
		event.getType match {
			case ServiceEvent.REGISTERED => register(event.getServiceReference)
			case ServiceEvent.UNREGISTERING => register(event.getServiceReference)
			case _ => println("Unhandled event")
		}
	}
	
	def register(sr : ServiceReference) = {
		//TODO
		val app : Application = context.getService(sr).asInstanceOf[Application]
		val contextRoot : String = sr.getProperty("").asInstanceOf[String]
		
		val jerseyServletParams : Dictionary[String, String] = new Hashtable[String, String]()
		jerseyServletParams.put("javax.ws.rs.Application", app.getClass().getName())

		for(obj <- httpTracker.getServices)
		{
			val httpService : HttpService = obj.asInstanceOf[HttpService]
			httpService.registerServlet(contextRoot, new ServletContainer(), jerseyServletParams, null)
		}

	}
	
	def unregister(sr : ServiceReference) = {
		//TODO
	}
	
}