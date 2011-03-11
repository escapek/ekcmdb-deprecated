package org.escapek.ekcmdb.services.common

import org.osgi.service.http.HttpService
import javax.ws.rs.core.Application
import com.sun.jersey.spi.container.servlet.ServletContainer
import java.util.{Hashtable, Dictionary}

/**
 * Created by IntelliJ IDEA.
 * User: Jouanin
 * Date: 11/03/11
 * Time: 10:22
 * To change this template use File | Settings | File Templates.
 */

class JerseyServletFactory {

  var contextRoot : String = _
  var application : Application = _

  def setHttpService(httpService : HttpService) = {
    val jerseyServletParams : Dictionary[String, String] = new Hashtable[String, String]()
    jerseyServletParams.put("javax.ws.rs.Application", application.getClass().getName())
    httpService.registerServlet(contextRoot, new ServletContainer(), jerseyServletParams, null);
  }
}