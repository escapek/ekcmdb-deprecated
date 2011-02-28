package org.escapek.ekcmdb.model

trait PropertyType {
	def isArray : Boolean
	def typeParameter : String
}