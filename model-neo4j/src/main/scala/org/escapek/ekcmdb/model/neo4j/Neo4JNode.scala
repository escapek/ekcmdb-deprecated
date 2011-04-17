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

import org.neo4j.graphdb.Node
import org.escapek.ekcmdb.tools.neo4j.Neo4JWrapper


/**
 * Created by IntelliJ IDEA.
 * User: Jouanin
 * Date: 03/03/11
 * Time: 12:00
 */

abstract class Neo4JNode(val node:Node) extends Neo4JWrapper
{
  require(node != null)
  
  def className : String

  if( !node.hasProperty(Neo4JNode.Prop_nodeClass))
    node.setProperty(Neo4JNode.Prop_nodeClass, className)

  def nodeClass =
  {
    node.getProperty(Neo4JNode.Prop_nodeClass).asInstanceOf[String]
  }
}

object Neo4JNode
{
  val Prop_nodeClass = "Neo4JNode.nodeClass"
}