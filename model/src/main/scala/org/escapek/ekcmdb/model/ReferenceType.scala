package org.escapek.ekcmdb.model

trait ReferenceType extends PropertyType 
{
	def referencedClass : CIClass
}