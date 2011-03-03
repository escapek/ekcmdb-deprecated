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

  def typeParameter = {
    node(PropertyImpl.Prop_typeParameter).asInstanceOf[String]
  }

  def isArray = {
    node(PropertyImpl.Prop_isArray).asInstanceOf[Boolean]
  }

  def defaultValue = {
    node(PropertyImpl.Prop_defaultValue).asInstanceOf[String]
  }

  def isNullAccepted = {
    node(PropertyImpl.Prop_isNullAccepted).asInstanceOf[Boolean]
  }
}

object PropertyImpl
{
  val className = "Property"
  val Prop_typeParameter = className + "." + "typeParameter"
  val Prop_isArray = className + "." + "isArray"
  val Prop_defaultValue = className + "." + "defaultValue"
  val Prop_isNullAccepted = className + "." + "isNullAccepted"
}