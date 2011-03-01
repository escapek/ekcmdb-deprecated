package org.escapek.ekcmdb.model.neo4j

import org.escapek.ekcmdb.model.MetaData
import org.neo4j.graphdb.{Relationship, Direction, Node}
import org.escapek.ekcmdb.tools.neo4j.Neo4JWrapper

class MetaDataImpl(val node:Node) extends MetaData with Neo4JWrapper
{
  def name = {
    val incRel = node.getSingleRelationship(RepositoryRelationships.Rel_RepositoryElementToMedaData,Direction.INCOMING)
    incRel(RepositoryRelationships.RelProp_RepositoryElementToMedaData_name).asInstanceOf[String]
  }

  def value = {
    node.getProperty(MetaDataImpl.Prop_value).asInstanceOf[String]
  }
}

object MetaDataImpl
{
  val Prop_value = "value"
}