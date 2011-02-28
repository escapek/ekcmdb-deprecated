package org.escapek.ekcmdb.model

trait CIClass extends ModelElement
{
	def schema : Schema
	def isAbstract : Boolean
	def isFinal : Boolean
	def baseClass : CIClass
	def properties : Set[Property]
}