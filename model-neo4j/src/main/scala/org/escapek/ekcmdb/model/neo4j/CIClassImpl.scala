package org.escapek.ekcmdb.model.neo4j

import org.escapek.ekcmdb.model.Property
import org.escapek.ekcmdb.model.CIClass
import org.escapek.ekcmdb.model.Schema
import org.neo4j.graphdb._
import org.escapek.ekcmdb.tools.neo4j.Neo4JWrapper
import scala.collection.JavaConversions._


class CIClassImpl(override val node:Node) extends ModelNodeImpl(node) with CIClass with Neo4JWrapper
{
  def className = CIClassImpl.className

	def schema : Schema = {
		new SchemaImpl(
				node.getSingleRelationship(RepositoryRelationships.Rel_ClassBelongsToSchema, Direction.OUTGOING).getEndNode)
	}

  def isAbstract : Boolean = {
    node(CIClassImpl.Prop_isAbstract).asInstanceOf[Boolean]
  }

	def isFinal : Boolean = {
    node(CIClassImpl.Prop_isFinal).asInstanceOf[Boolean]
  }

	def baseClass : Option[CIClass] = {
    if(node.hasRelationship(RepositoryRelationships.Rel_ClassHasParentClass, Direction.OUTGOING))
      Some(new CIClassImpl(
        node.getSingleRelationship(RepositoryRelationships.Rel_ClassHasParentClass, Direction.OUTGOING).getEndNode))
    else
      None
  }

	def properties : Set[Property] = {
    val iterator =
      node.getRelationships(RepositoryRelationships.Rel_ClassHasProperties,Direction.OUTGOING).iterator
    Set.empty[Property] ++ iterator.map( c => new PropertyImpl(c.getEndNode()))
  }
}

object CIClassImpl
{
  val className = "CIClass"
  val Prop_isAbstract = className + "." + "isAbstract"
  val Prop_isFinal = className + "." + "isFinal"
}