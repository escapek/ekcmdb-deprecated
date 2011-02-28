package org.escapek.ekcmdb.model.neo4j

import org.escapek.ekcmdb.model.RepositoryElement
import org.neo4j.graphdb.{Relationship, Node, Direction}
import org.escapek.ekcmdb.tools.neo4j.Neo4JWrapper
import scala.collection.JavaConversions._
import collection.immutable.HashMap

abstract class RepositoryElementImpl(val node:Node) extends RepositoryElement with Neo4JWrapper
{
	override def id =
	{
		node.getId
	}

  override def version =
  {
    node.getProperty(RepositoryElementImpl.Prop_version).asInstanceOf[Long]
  }

	override def metaData =
	{
    val iterator =
      node.getRelationships(RepositoryRelationships.Rel_RepositoryElementToMedaData,Direction.OUTGOING).iterator
    //iterator.map( r => (r.getProperty(RepositoryRelationships.RelProp_RepositoryElementToMedaData_name), r.getEndNode()))
    iterator.map( r => (r(RepositoryRelationships.RelProp_RepositoryElementToMedaData_name), new MetaDataImpl(r.getEndNode())))
	}
}

object RepositoryElementImpl
{
	val Prop_version = "version"
}