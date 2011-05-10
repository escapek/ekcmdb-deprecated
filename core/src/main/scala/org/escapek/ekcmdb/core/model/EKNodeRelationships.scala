package org.escapek.ekcmdb.core.model

import org.neo4j.graphdb.DynamicRelationshipType.withName

object EKNodeRelationships {
  val Rel_EKNodeHasMetaData = withName("REPOSITORYELEMENT_HAS_METADATA")
  val RelProp_EKNodeHasMetaData_name = "name"

}