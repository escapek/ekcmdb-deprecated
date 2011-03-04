package org.escapek.ekcmdb.model

trait CIClass extends ModelNode
{
	def schema : Schema
	def isAbstract : Boolean
	def isFinal : Boolean
  def isAssociation : Boolean
	def baseClass : Option[CIClass]
	def properties : Set[Property]
}