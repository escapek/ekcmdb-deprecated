package org.escapek.ekcmdb.model

import org.escapek.ekcmdb.model.PropertyType._

trait Property extends ModelNode {
	def defaultValue : String
	def restrictions : String
	def minCardinality : Int
  def maxCardinality : Int
	def overrides : Option[Property]
  def propertyType : PropertyType
  def referencedClass : Option[CIClass]
}