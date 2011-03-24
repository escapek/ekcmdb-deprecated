package org.escapek.ekcmdb.services

import org.osgi.framework.{ ServiceReference, BundleContext}
import akka.actor.Actor
import org.osgi.service.event.EventAdmin
import org.osgi.service.event.Event
import java.util.HashMap

/**
 * EventAdminActor is responsible for sending asynchronous events to EventAdmin service
 * when a JAX-RS application has been deployed
 */
case class ApplicationDeployed(sr: ServiceReference)

class EventAdminActor(val context : BundleContext) extends Actor {
  def receive = {
    case ApplicationDeployed(sr) => sendDeployedEvent(sr)
    case _ => println("Unkown message")
  }

  def sendDeployedEvent(sr: ServiceReference) = {
    val contextRoot: String = sr.getProperty(EKcmdbServicesActivator.ServletContext).asInstanceOf[String]

    val eaRef: ServiceReference = context.getServiceReference(classOf[EventAdmin].getName())
    if (eaRef != null) {
      val ea: EventAdmin = context.getService(eaRef).asInstanceOf[EventAdmin]
      ea.sendEvent(new Event("ekcmdb/services" + contextRoot + "/DEPLOYED", new HashMap()))
      context.ungetService(eaRef);
    }
  }
}
