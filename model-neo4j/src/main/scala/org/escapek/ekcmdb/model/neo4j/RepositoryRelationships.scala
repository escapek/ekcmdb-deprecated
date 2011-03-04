package org.escapek.ekcmdb.model.neo4j

import org.neo4j.graphdb.DynamicRelationshipType.withName

object RepositoryRelationships
{
  val Rel_RepositoryElementToMedaData = withName("REPOSITORYELEMENT_TO_METADATA")
  val RelProp_RepositoryElementToMedaData_name = "name"

  val Rel_ClassBelongsToSchema = withName("CLASS_BELONGS_TO_SCHEMA")
  val Rel_ClassHasProperties = withName("CLASS_HAS_PROPERTIES")
  val Rel_ClassHasParentClass = withName("CLASS_HAS_PARENTCLASS")
  val Rel_PropertyOverrides = withName("PROPERTY_OVERRIDES")
  val Rel_PropertyReferencesClass = withName("PROPERTY_REFERENCES_CLASS")
  val Rel_InstancePropertyIsDefinedBy = withName("INSTANCE_PROPERTY_IS_DEFINED_BY")
  val Rel_InstanceHasProperties = withName("INSTANCE_HAS_PROPERTIES")
  val Rel_IsInstanceOf = withName("IS_INSTANCE_OF")

}