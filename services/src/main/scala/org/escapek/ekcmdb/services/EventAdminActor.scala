/**
 * Copyright (C) 2011 njouanin - http://www.escapek.org/ - <EscapeK> 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
