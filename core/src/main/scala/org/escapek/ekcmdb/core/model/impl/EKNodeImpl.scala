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
import org.neo4j.graphdb.{ Node, Direction }
import scala.collection.JavaConversions._
import org.escapek.ekcmdb.core.model.{ MetaData, EKNode, EKNodeRelationships }
import org.escapek.ekcmdb.core.tools.Neo4JWrapper

abstract class EKNodeImpl(val node: Node) extends EKNode with Neo4JWrapper {
  require(node != null)

  def typeName: String

  if (!node.hasProperty(EKNodeImpl.Prop_nodeClass))
    nodeType = typeName

  def nodeType = {
    node.getProperty(EKNodeImpl.Prop_nodeClass).asInstanceOf[String]
  }

  def nodeType_=(sType: String) {
    node.setProperty(EKNodeImpl.Prop_nodeClass, typeName)
  }

  def id = {
    node.getId
  }

  def metaData =  {
    val iterator =
      node.getRelationships(EKNodeRelationships.Rel_EKNodeHasMetaData, Direction.OUTGOING).iterator
    Map.empty[String, Object] ++
      iterator.map(
        r => (r(EKNodeRelationships.RelProp_EKNodeHasMetaData_name).asInstanceOf[String],
          r.getEndNode().getProperty(EKNodeImpl.Prop_metadataValue))
      )
    }
}

object EKNodeImpl {
  val propPrefix = "EKNode"
  val Prop_nodeClass = propPrefix + ".nodeClass"
  val Prop_nodeName = propPrefix + ".nodeName"
  val Prop_version = propPrefix + ".version"
  val Prop_metadataValue = "Metadata.value"
}