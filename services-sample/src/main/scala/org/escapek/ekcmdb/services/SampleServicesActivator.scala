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