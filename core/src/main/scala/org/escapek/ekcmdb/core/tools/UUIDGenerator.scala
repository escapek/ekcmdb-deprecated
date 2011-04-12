package org.escapek.ekcmdb.core.tools
import java.util.UUID

object UUIDGenerator {
	def getUUID : UUID = {
		UUID.randomUUID
	}
	
	def getStringUUID : String = getUUID.toString
}