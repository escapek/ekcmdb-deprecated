package org.escapek.ekcmdb.model.neo4j

import org.escapek.ekcmdb.model.CIClass
import org.escapek.ekcmdb.model.Schema
import org.neo4j.graphdb.{Direction, Node}

class SchemaImpl(override val node: Node) extends ModelElementImpl(node) with Schema
{
	override def content : Set[CIClass] = {
    val iterator =
      node.getRelationships(RepositoryRelationships.Rel_ClassBelongsToSchema,Direction.INCOMING).iterator
    Set.empty[CIClass] ++ iterator.map( c => new MetaDataImpl(c.getEndNode()))
	}
}