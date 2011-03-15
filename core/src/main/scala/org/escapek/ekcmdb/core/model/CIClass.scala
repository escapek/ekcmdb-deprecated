package org.escapek.ekcmdb.core.model

trait CIClass extends EKNode with NamedNode
{
	def schema : Schema
	def isAbstract : Boolean
	def isFinal : Boolean
  def isAssociation : Boolean
	def baseClass : Option[CIClass]
	def properties : Set[Property]
}