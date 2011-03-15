package org.escapek.ekcmdb.services

import org.osgi.util.tracker.ServiceTracker
import org.osgi.service.http.HttpService
import org.osgi.framework.{ServiceReference, BundleContext, BundleActivator}

/**
 * Created by IntelliJ IDEA.
 * User: nico
 * Date: 13/03/11
 * Time: 16:38
 */

abstract class EKcmdbServicesActivator extends BundleActivator
{

  def start(context: BundleContext) =
  {
    val tracker : ServiceTracker = new ServiceTracker(context, classOf[HttpService].getName, null)
    tracker.open
  }
}