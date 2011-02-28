package org.escapek.ekcmdb.model.neo4j

import org.escapek.ekcmdb.model.Property
import org.escapek.ekcmdb.model.CIClass
import org.escapek.ekcmdb.model.Schema
import org.neo4j.graphdb._

class CIClassImpl(override val node:Node) extends ModelElementImpl(node) with CIClass
{
	def schema : Schema = {
		new SchemaImpl(
				node.getSingleRelationship(
						DynamicRelationshipType.withName(CIClassImpl.Rel_ClassBelongsToSchema), Direction.OUTGOING).getEndNode)
	}

	def isAbstract : Boolean = false
	def isFinal : Boolean = false
	def baseClass : CIClass = null
	def properties : Set[Property] = Set[Property]()
}

object CIClassImpl
{
	val Rel_ClassBelongsToSchema = "CLASS_BELONGS_TO_SCHEMA";
}