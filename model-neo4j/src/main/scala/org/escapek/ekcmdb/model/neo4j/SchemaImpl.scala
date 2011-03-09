package org.escapek.ekcmdb.model.neo4j

import org.escapek.ekcmdb.model.CIClass
import org.escapek.ekcmdb.model.Schema
import scala.collection.JavaConversions._
import org.neo4j.graphdb.{Direction, Node}

class SchemaImpl(override val node: Node) extends EKNodeImpl(node) with Schema with NamedlNodeImpl
{
	override def className = SchemaImpl.className

  override def content : Set[CIClass] = {
    val iterator =
      node.getRelationships(RepositoryRelationships.Rel_ClassBelongsToSchema,Direction.INCOMING).iterator
    Set.empty[CIClass] ++ iterator.map( c => new CIClassImpl(c.getEndNode()))
	}
}

object SchemaImpl
{
  val className = "Schema"
}