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

import org.neo4j.graphdb.{Node, Direction}
import scala.collection.JavaConversions._
import org.escapek.ekcmdb.core.model.{MetaData, EKNode}

class EKNodeImpl(override val node:Node) extends Neo4JNode(node) with EKNode
{
  def className = EKNodeImpl.className

	override def id =
	{
		node.getId
	}

  override def version =
  {
    node.getProperty(EKNodeImpl.Prop_version).asInstanceOf[String]
  }

  def version_=(v:String) {
    node.setProperty(EKNodeImpl.Prop_version , v)
  }

	override def metaData =
	{
    val iterator =
      node.getRelationships(RepositoryRelationships.Rel_RepositoryElementToMedaData,Direction.OUTGOING).iterator
    Map.empty[String, MetaData] ++
      iterator.map(
        r => (r(RepositoryRelationships.RelProp_RepositoryElementToMedaData_name).asInstanceOf[String],
          new MetaDataImpl(r.getEndNode())))
	}

}

object EKNodeImpl
{
  val className = "EKNode"
  val Prop_version = className + ".version"
}