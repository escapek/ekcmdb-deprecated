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

  require(aNode != null)
  def baseNode = aNode

  if (!baseNode.hasProperty(EKNodeImpl.Prop_nodeType))
    baseNode.setProperty(EKNodeImpl.Prop_nodeType, nodeType)

  def id = {
    baseNode.getId
  }
}

import org.neo4j.graphdb.DynamicRelationshipType.withName
object EKNodeImpl {
  val propPrefix = "EKNode"
  val Prop_nodeType = propPrefix + ".nodeType"
  val Prop_nodeName = propPrefix + ".nodeName"
  val Prop_version = propPrefix + ".version"
  
  //Relationships
  val Rel_EKNodeHasMetaData = withName("EKNODE_HAS_METADATA")
}