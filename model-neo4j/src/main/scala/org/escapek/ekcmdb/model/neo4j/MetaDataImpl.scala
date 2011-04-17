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
package org.escapek.ekcmdb.model.neo4j

import org.escapek.ekcmdb.core.model.MetaData
import org.neo4j.graphdb.{Direction, Node}

class MetaDataImpl(override val node:Node) extends Neo4JNode(node) with MetaData
{
  override def className = MetaDataImpl.className

  def name = {
    val incRel = node.getSingleRelationship(RepositoryRelationships.Rel_RepositoryElementToMedaData,Direction.INCOMING)
    incRel(RepositoryRelationships.RelProp_RepositoryElementToMedaData_name).asInstanceOf[String]
  }

  def value = {
    node.getProperty(MetaDataImpl.Prop_value).asInstanceOf[String]
  }

  def value_=(v:String) = {
    node.setProperty(MetaDataImpl.Prop_value, v)
  }

}

object MetaDataImpl
{
  val className = "MetaData"
  val Prop_value = className + ".value"
}