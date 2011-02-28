package org.escapek.ekcmdb.model.neo4j
import org.neo4j.graphdb.Relationship

object ModelRelationsTypes extends Enumeration
{
	type ModelRelationsTypes = Value
	
	val CLASS_BELONGS_TO_SCHEMA = Value
	val REPOSITORYELEMENT_TO_METADATA = Value
}