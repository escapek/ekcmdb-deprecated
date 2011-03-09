package org.escapek.ekcmdb.model.neo4j

import org.neo4j.graphdb.{Node, Direction}
import scala.collection.JavaConversions._
import org.escapek.ekcmdb.model.{MetaData, EKNode}

class EKNodeImpl(override val node:Node) extends Neo4JNode(node) with EKNode
{
  def className = EKNodeImpl.className

	override def id =
	{
		node.getId
	}

  override def version =
  {
    node.getProperty(EKNodeImpl.Prop_version).asInstanceOf[String]
  }

  def version_=(v:String) {
    node.setProperty(EKNodeImpl.Prop_version , v)
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

object EKNodeImpl
{
  val className = "EKNode"
  val Prop_version = className + ".version"
}