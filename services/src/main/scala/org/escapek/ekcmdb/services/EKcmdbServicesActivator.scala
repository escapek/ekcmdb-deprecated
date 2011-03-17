package org.escapek.ekcmdb.services

import org.osgi.framework.{ServiceListener, ServiceEvent, ServiceReference, BundleContext, BundleActivator}
import org.osgi.service.http.HttpService
import org.osgi.util.tracker.ServiceTracker

/**
 * Created by IntelliJ IDEA.
 * User: nico
 * Date: 13/03/11
 * Time: 16:38
 */

abstract class EKcmdbServicesActivator extends BundleActivator
{
	var tracker : ServiceTracker =_
	
	// Implicit conversion from function to ServiceListener
	implicit def function2ServiceListener(f: ServiceEvent => Unit) =
		new ServiceListener {
			def serviceChanged(event: ServiceEvent) = f(event)
		}

	def start(context: BundleContext) =
	{
		tracker = new ServiceTracker(context, classOf[HttpService].getName, null)
		tracker.open
		
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
	}
	
	def unregister(sr : ServiceReference) = {
		//TODO
	}
	
}