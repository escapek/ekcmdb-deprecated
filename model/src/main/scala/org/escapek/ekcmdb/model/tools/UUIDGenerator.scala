package org.escapek.ekcmdb.model.tools


object UUIDGenerator {
	def getUUID : UUID = {
		UUID.randomUUID
	}
	
	def getStringUUID : String = getUUID.toString
}