package org.escapek.ekcmdb.core.internal

import org.osgi.framework.{ ServiceListener, ServiceEvent, ServiceReference, BundleContext, BundleActivator }
import org.osgi.util.tracker.ServiceTracker
import org.osgi.service.event.{Event,EventAdmin}
import org.escapek.ekcmdb.core.tools.Logging
import org.escapek.ekcmdb.core.ApplicationEvent

class CoreActivator extends BundleActivator with Logging
{
  var context: BundleContext = _
  var eventAdminTracker : ServiceTracker = _

    def start(ctx: BundleContext) =
    {
      context = ctx
      eventAdminTracker = new ServiceTracker(context, classOf[EventAdmin].getName, null)
      eventAdminTracker.open
      
      postEvent(ApplicationEvent.Topic_startup)
    }
  
    def stop(ctx: BundleContext) = {
      sendEvent(ApplicationEvent.Topic_shutdownInProgress)
      postEvent(ApplicationEvent.Topic_stopped)
      eventAdminTracker.close
    }
    
    private def sendEvent(topic: String) = {
      import java.util.HashMap
      val eventAdmin = eventAdminTracker.getService().asInstanceOf[EventAdmin]
      if(eventAdmin != null)
        eventAdmin.sendEvent( new Event(topic, new HashMap()) )
    }

    private def postEvent(topic: String) = {
      import java.util.HashMap
      val eventAdmin = eventAdminTracker.getService().asInstanceOf[EventAdmin]
      if(eventAdmin != null)
        eventAdmin.postEvent( new Event(topic, new HashMap()) )
    }
}