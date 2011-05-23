package org.escapek.ekcmdb.core.repository.impl

import org.neo4j.graphdb.DynamicRelationshipType.withName

object Relationships {
  val Rel_EKNodeHasMetaData = withName("EKNODE_HAS_METADATA")
}