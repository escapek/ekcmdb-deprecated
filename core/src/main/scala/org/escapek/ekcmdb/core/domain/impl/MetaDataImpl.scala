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
package org.escapek.ekcmdb.core.domain.impl

import org.escapek.ekcmdb.core.domain.MetaData
import org.neo4j.graphdb.{Direction, Node}
import org.escapek.ekcmdb.core.tools.Neo4JWrapper

class MetaDataImpl(val aNode:Node) extends MetaData with Neo4JNodeContainer
{
  //Require given node is not null for property mapping
  require(aNode != null, "Neo4J node used for mapping can not be null")
  override def baseNode = aNode

  //Require given node is not already used by another object
  require( !baseNode.hasProperty(MetaDataImpl.Prop_nodeType), 
      "Neo4J node used for mapping is already assigned to another object" )
  baseNode.setProperty(MetaDataImpl.Prop_nodeType, "MetaData")

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
  val Prop_nodeType = propPrefix + ".nodeType"
  val Prop_key = propPrefix + ".key"
  val Prop_value = propPrefix + ".value"
}