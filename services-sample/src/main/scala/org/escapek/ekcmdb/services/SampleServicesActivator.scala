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

import org.osgi.framework.{ BundleContext, BundleActivator }
import java.util.{Hashtable}
import org.escapek.ekcmdb.core.tools.Logging
import javax.ws.rs.core.{Application}

class SampleServicesActivator extends BundleActivator with Logging {

  def start(ctx: BundleContext) = {
	  logger.debug("Register service {} with context /services", classOf[TestApplication].getName)
	  val props = new Hashtable[String, String]()
	   props.put(EKcmdbServicesActivator.ServletContext, "/services")
	  ctx.registerService(classOf[Application].getName, new TestApplication, props)
  }

  def stop(ctx: BundleContext) = {
  }

}