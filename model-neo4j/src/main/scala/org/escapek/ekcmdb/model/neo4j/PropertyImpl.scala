package org.escapek.ekcmdb.model.neo4j

import org.escapek.ekcmdb.tools.neo4j.Neo4JWrapper
import org.neo4j.graphdb.{Direction, Node}
import org.escapek.ekcmdb.model.{PropertyType, Property}

/**
 * Created by IntelliJ IDEA.
 * User: Jouanin
 * Date: 01/03/11
 * Time: 15:20
 */

class PropertyImpl(override val node:Node) extends ModelNodeImpl(node) with Property
{
  def className = PropertyImpl.className

  def overrides = {
    if(node.hasRelationship(RepositoryRelationships.Rel_PropertyOverrides, Direction.OUTGOING))
      Some(new PropertyImpl(
        node.getSingleRelationship(RepositoryRelationships.Rel_PropertyOverrides, Direction.OUTGOING).getEndNode))
    else
      None
  }

  def defaultValue = {
    node(PropertyImpl.Prop_defaultValue).asInstanceOf[String]
  }

  def defaultValue_=(value:String) = {
    node.setProperty(PropertyImpl.Prop_defaultValue, value)
  }

  def restrictions = {
    node(PropertyImpl.Prop_restrictions).asInstanceOf[String]
  }

  def restrictions_=(value:String) = {
    node.setProperty(PropertyImpl.Prop_restrictions, value)
  }

  def cardinality = {
    node(PropertyImpl.Prop_cardinality).asInstanceOf[String]
  }

  def cardinality_=(card:String) = {
    node.setProperty(PropertyImpl.Prop_cardinality, card)
  }

  def referencedClass = {
    if(node.hasRelationship(RepositoryRelationships.Rel_PropertyReferencesClass, Direction.OUTGOING))
      Some(new CIClassImpl(
        node.getSingleRelationship(RepositoryRelationships.Rel_PropertyReferencesClass, Direction.OUTGOING).getEndNode))
    else
      None
  }

  def propertyType = {
    PropertyType.withName(node(PropertyImpl.Prop_propertyType).asInstanceOf[String])
  }

  def propertyType_=(t:PropertyType.PropertyType) = {
    node.setProperty(PropertyImpl.Prop_propertyType, t.toString)
  }

}

object PropertyImpl
{
  val className = "Property"
  val Prop_defaultValue = className + "." + "defaultValue"
  val Prop_restrictions = className + "." + "restrictions"
  val Prop_cardinality = className + "." + "cardinality"
  val Prop_propertyType = className + "." + "propertyType"
}