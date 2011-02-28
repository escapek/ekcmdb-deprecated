package org.escapek.ekcmdb.model.neo4j

import org.neo4j.graphdb.DynamicRelationshipType.withName

object RepositoryRelationships
{
  val Rel_RepositoryElementToMedaData = withName("REPOSITORYELEMENT_TO_METADATA")
  val RelProp_RepositoryElementToMedaData_name = "name"
}