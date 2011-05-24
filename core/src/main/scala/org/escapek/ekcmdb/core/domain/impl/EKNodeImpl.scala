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
import org.neo4j.graphdb.{ Node, Direction }
import scala.collection.JavaConversions._
import org.escapek.ekcmdb.core.domain.{ MetaData, EKNode }
import org.escapek.ekcmdb.core.tools.Neo4JWrapper

abstract class EKNodeImpl(val aNode: Node) extends EKNode with Neo4JNodeContainer {

  override def nodeType: String

  //Require given node is not null for property mapping
  require(aNode != null, "Neo4J node used for mapping can not be null")
  override def baseNode = aNode

  //Require given node is not already used by another object
  if( !baseNode.hasProperty(EKNodeImpl.Prop_nodeType) ) 
    baseNode.setProperty(EKNodeImpl.Prop_nodeType, nodeType)

  def id = {
    baseNode.getId
  }
}

object EKNodeImpl {
  val propPrefix = "EKNode"
  val Prop_nodeType = propPrefix + ".nodeType"
}