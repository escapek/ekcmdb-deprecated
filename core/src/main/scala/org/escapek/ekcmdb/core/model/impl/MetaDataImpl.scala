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
package org.escapek.ekcmdb.core.model.impl

import org.escapek.ekcmdb.core.model.{MetaData, EKNodeRelationships}
import org.neo4j.graphdb.{Direction, Node}
import org.escapek.ekcmdb.core.tools.Neo4JWrapper

class MetaDataImpl(val node:Node) extends MetaData with Neo4JWrapper
{
  //Add nodeClass property for identifying nodes of type MetaData
  if (!node.hasProperty(MetaDataImpl.Prop_nodeClass))
    node.setProperty(MetaDataImpl.Prop_nodeClass, "MetaData")

  def name = {
    val incRel = node.getSingleRelationship(EKNodeRelationships.Rel_EKNodeHasMetaData,Direction.INCOMING)
    incRel(EKNodeRelationships.RelProp_EKNodeHasMetaData_name).asInstanceOf[String]
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
  val propPrefix = "MetaData"
  val Prop_nodeClass = propPrefix + ".nodeClass"
  val Prop_value = propPrefix + ".value"
}