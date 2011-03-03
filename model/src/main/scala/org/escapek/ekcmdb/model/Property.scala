package org.escapek.ekcmdb.model

trait Property extends ModelNode {
	def defaultValue : String
	def restrictions : String
	def minCardinality : Int
  def maxCardinality : Int
	def overrides : Option[Property]
  def propertyType : PropertyType
}