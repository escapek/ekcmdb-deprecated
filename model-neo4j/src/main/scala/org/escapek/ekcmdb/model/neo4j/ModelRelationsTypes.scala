package org.escapek.ekcmdb.model.neo4j


object ModelRelationsTypes extends Enumeration
{
	type ModelRelationsTypes = Value
	
	val CLASS_BELONGS_TO_SCHEMA = Value
	val REPOSITORYELEMENT_TO_METADATA = Value
}