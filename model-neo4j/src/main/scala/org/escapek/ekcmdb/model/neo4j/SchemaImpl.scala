package org.escapek.ekcmdb.model.neo4j

import org.escapek.ekcmdb.model.CIClass
import org.escapek.ekcmdb.model.Schema
import org.neo4j.graphdb.Node

class SchemaImpl(override val node: Node) extends ModelElementImpl(node) with Schema
{
/*	override def content : Set[CIClass] = {
		node.getRelationships
	}
*/
  def content = null
}