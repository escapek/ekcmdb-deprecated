/**
 * Copyright (C) 2011 njouanin - http://www.escapek.org/ - <EscapeK> 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.escapek.ekcmdb.core.neo4j.internal.model

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