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