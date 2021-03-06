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
package org.escapek.ekcmdb.core.neo4j.internal.dao

import org.escapek.ekcmdb.core.neo4j.internal.model.EKNodeImpl
import org.escapek.ekcmdb.core.dao.EKNodeDao
import org.escapek.ekcmdb.core.model.EKNode
import org.neo4j.graphdb.{NotFoundException, Node, GraphDatabaseService}

/**
 * Created by IntelliJ IDEA.
 * User: Jouanin
 * Date: 07/03/11
 * Time: 16:18
 */

abstract class EKNodeDaoImpl[T <: EKNode](val db : GraphDatabaseService) extends EKNodeDao[T]
{
  protected def fromNode(node : Node) : T

  def getById(id:Long) : Option[T] = {
    try {
      Some(fromNode(db.getNodeById(id)))
    }
    catch {
      case ex:NotFoundException => None
    }
  }

  def getClassName(node : Node) : String =
  {
    new EKNodeImpl(node).className
  }

  def createNew() : T = {
    fromNode(db.createNode)
  }
}