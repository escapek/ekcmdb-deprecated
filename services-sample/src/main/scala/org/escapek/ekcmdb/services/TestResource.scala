package org.escapek.ekcmdb.services

import javax.ws.rs.GET
import javax.ws.rs.Path

/**
 * Created by IntelliJ IDEA.
 * User: nico
 * Date: 09/03/11
 * Time: 22:13
 */

@Path("/test")
class TestResource {
 
	@GET
	def get() = {
		"Hello world !"
	}
}