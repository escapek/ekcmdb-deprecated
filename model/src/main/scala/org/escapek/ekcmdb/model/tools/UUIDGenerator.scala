package org.escapek.ekcmdb.model.tools
import java.util.UUID

object UUIDGenerator {
	def getUUID : UUID = {
		UUID.randomUUID
	}
	
	def getStringUUID : String = getUUID.toString
}