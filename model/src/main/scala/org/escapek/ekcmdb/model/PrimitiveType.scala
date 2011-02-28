package org.escapek.ekcmdb.model

trait PrimitiveType extends PropertyType {
	def propertyType : PrimitiveType
}

object PrimitiveType extends Enumeration
{
	val Numeric, Real, Bool, DateTime = Value
}