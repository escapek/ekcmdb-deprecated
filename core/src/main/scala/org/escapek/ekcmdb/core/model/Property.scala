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
package org.escapek.ekcmdb.core.model

import org.escapek.ekcmdb.core.model.PropertyType._

trait Property extends NamedNode {
	def defaultValue : String
	def restrictions : String

  /**
   * Property cardinality.
   * Can be something like 1, 0..1, 0..*, 1..*, 2..5, etc
   */
  def cardinality : String
	def overrides : Option[Property]
  def propertyType : PropertyType
  def referencedClass : Option[CIClass]
}