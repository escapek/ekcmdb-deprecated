package org.escapek.ekcmdb.model.neo4j

import org.neo4j.graphdb.{Relationship, Node, Direction}
import org.escapek.ekcmdb.tools.neo4j.Neo4JWrapper
import scala.collection.JavaConversions._
import collection.immutable.HashMap
import org.escapek.ekcmdb.model.{MetaData, RepositoryElement}

abstract class RepositoryElementImpl(val node:Node) extends RepositoryElement with Neo4JWrapper
{
	override def id =
	{
		node.getId
	}

  override def version =
  {
    node.getProperty(RepositoryElementImpl.Prop_version).asInstanceOf[String]
  }

  def version_=(v:String) {
    node.setProperty(RepositoryElementImpl.Prop_version , v)
  }

	override def metaData =
	{
    val iterator =
      node.getRelationships(RepositoryRelationships.Rel_RepositoryElementToMedaData,Direction.OUTGOING).iterator
    Map.empty[String, MetaData] ++
      iterator.map(
        r => (r(RepositoryRelationships.RelProp_RepositoryElementToMedaData_name).asInstanceOf[String],
          new MetaDataImpl(r.getEndNode())))
	}

}

object RepositoryElementImpl
{
	val Prop_version = "version"
}