package org.escapek.ekcmdb.core.tools


object UUIDGenerator {
	def getUUID : UUID = {
		UUID.randomUUID
	}
	
	def getStringUUID : String = getUUID.toString
}