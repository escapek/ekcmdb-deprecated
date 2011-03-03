package org.escapek.ekcmdb.model.neo4j

import org.escapek.ekcmdb.model.MetaData
import org.neo4j.graphdb.{Direction, Node}

class MetaDataImpl(override val node:Node) extends Neo4JNode(node) with MetaData
{
  def name = {
    val incRel = node.getSingleRelationship(RepositoryRelationships.Rel_RepositoryElementToMedaData,Direction.INCOMING)
    incRel(RepositoryRelationships.RelProp_RepositoryElementToMedaData_name).asInstanceOf[String]
  }

  def value = {
    node.getProperty(MetaDataImpl.Prop_value).asInstanceOf[String]
  }

  def value_=(v:String) = {
    node.setProperty(MetaDataImpl.Prop_value, v)
  }

  def className = MetaDataImpl.className
}

object MetaDataImpl
{
  val className = "MetaData"
  val Prop_value = className + "." + "value"
}