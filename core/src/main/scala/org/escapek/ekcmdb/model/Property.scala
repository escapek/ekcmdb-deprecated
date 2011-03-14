package org.escapek.ekcmdb.model

import org.escapek.ekcmdb.model.PropertyType._

trait Property extends NamedNode {
	def defaultValue : String
	def restrictions : String

  /**
   * Property cardinality.
   * Can be something like 1, 0..1, 0..*, 1..*, 2..5, etc
   */
  def cardinality : String
	def overrides : Option[Property]
  def propertyType : PropertyType
  def referencedClass : Option[CIClass]
}