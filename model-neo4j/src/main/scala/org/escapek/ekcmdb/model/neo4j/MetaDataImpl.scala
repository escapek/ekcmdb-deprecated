package org.escapek.ekcmdb.model.neo4j

import org.escapek.ekcmdb.model.MetaData
import org.neo4j.graphdb.{Relationship, Direction, Node}

class MetaDataImpl(override val node:Node) extends MetaData
{
  def name = {
    val incRel = node.getSingleRelationship(RepositoryRelationships.Rel_RepositoryElementToMedaData,Direction.INCOMING)
    incRel.getProperty(RepositoryRelationships.RelProp_RepositoryElementToMedaData_name).asInstanceOf[String]
  }

  def value = null
}

object MetaDataImpl
{
  val Key_value = "value"
}