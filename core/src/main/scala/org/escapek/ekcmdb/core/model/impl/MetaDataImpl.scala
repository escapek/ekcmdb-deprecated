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

class MetaDataImpl(val aNode:Node) extends MetaData with Neo4JNodeContainer
{
  require(aNode != null)
  override def baseNode = aNode

  //Add nodeClass property for identifying nodes of type MetaData
  if (!baseNode.hasProperty(MetaDataImpl.Prop_nodeClass))
    baseNode.setProperty(MetaDataImpl.Prop_nodeClass, "MetaData")

  def key = {
    baseNode.getProperty(MetaDataImpl.Prop_key).asInstanceOf[String]
  }

  def key_=(v: String) = {
    baseNode.setProperty(MetaDataImpl.Prop_key, v)
  }

  def value = {
    baseNode.getProperty(MetaDataImpl.Prop_value).asInstanceOf[String]
  }

  def value_=(v: Any) = {
    baseNode.setProperty(MetaDataImpl.Prop_value, v)
  }

}

object MetaDataImpl
{
  val propPrefix = "MetaData"
  val Prop_nodeClass = propPrefix + ".nodeClass"
  val Prop_key = propPrefix + ".key"
  val Prop_value = propPrefix + ".value"
}