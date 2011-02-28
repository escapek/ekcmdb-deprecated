package org.escapek.ekcmdb.model

trait Property extends ModelElement {
	def isNullAccepted : Boolean
	def defaultValue : Unit
	def isArray : Boolean
	def typeParameter
	def overrides : Property
}