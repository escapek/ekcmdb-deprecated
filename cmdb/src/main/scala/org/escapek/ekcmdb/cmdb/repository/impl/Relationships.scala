package org.escapek.ekcmdb.cmdb.repository.impl

import org.neo4j.graphdb.DynamicRelationshipType.withName

object Relationships {
  val Rel_DomainContainsCIClass = withName("CONTAINS_CI_CLASS")
  val Rel_CIClassHasSuperClass = withName("HAS_SUPERCLASS")
}