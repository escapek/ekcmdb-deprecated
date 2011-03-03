package org.escapek.ekcmdb.model.neo4j

import org.escapek.ekcmdb.tools.neo4j.Neo4JWrapper
import org.escapek.ekcmdb.model.Property
import org.neo4j.graphdb.{Direction, Node}

/**
 * Created by IntelliJ IDEA.
 * User: Jouanin
 * Date: 01/03/11
 * Time: 15:20
 */

class PropertyImpl(override val node:Node) extends ModelNodeImpl(node) with Property with Neo4JWrapper
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

  def minCardinality = {
    node(PropertyImpl.Prop_minCardinality).asInstanceOf[Int]
  }

  def minCardinality_=(card:Int) = {
    node.setProperty(PropertyImpl.Prop_minCardinality, card)
  }

  def maxCardinality = {
    node(PropertyImpl.Prop_maxCardinality).asInstanceOf[Int]
  }

  def maxCardinality_=(card:Int) = {
    node.setProperty(PropertyImpl.Prop_maxCardinality, card)
  }

  //TODO : Implement
  def referencedClass = null

  //TODO : Implement
  def propertyType = null
}

object PropertyImpl
{
  val className = "Property"
  val Prop_defaultValue = className + "." + "defaultValue"
  val Prop_restrictions = className + "." + "restrictions"
  val Prop_minCardinality = className + "." + "minCardinality"
  val Prop_maxCardinality = className + "." + "maxCardinality"
}