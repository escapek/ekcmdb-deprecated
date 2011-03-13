package org.escapek.ekcmdb.services

import org.osgi.util.tracker.ServiceTracker
import org.osgi.service.http.HttpService
import org.osgi.framework.{ServiceReference, BundleContext, BundleActivator}

/**
 * Created by IntelliJ IDEA.
 * User: nico
 * Date: 13/03/11
 * Time: 16:38
 * To change this template use File | Settings | File Templates.
 */

abstract class HttpServiceAwareActivator extends BundleActivator {

  def doOnAddingService(httpService : HttpService)
  def doOnRemovingService(httpService : HttpService)

  class HttpServiceTracker extends ServiceTracker {
    def addingService(serviceRef : ServiceReference) : Object = {

    }
    def serviceRemoved(serviceRef : ServiceReference, service : Object) = {

    }
  }

  def start(context: BundleContext) =
  {
/*
    val tracker : ServiceTracker = new ServiceTracker(context, ClassOf[HttpService], null) {
      def adding
    }
*/
  }
}