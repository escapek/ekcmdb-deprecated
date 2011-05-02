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

import org.escapek.ekcmdb.core.dao.CIClassDao
import org.neo4j.graphdb.{Node, GraphDatabaseService}
import org.escapek.ekcmdb.core.neo4j.internal.model.CIClassImpl
import scala.collection.JavaConversions._
import org.escapek.ekcmdb.core.model.{Schema, CIClass}


/**
 * Created by IntelliJ IDEA.
 * User: nico
 * Date: 07/03/11
 * Time: 21:06
 * To change this template use File | Settings | File Templates.
 */

class CIClassDaoImpl(db:GraphDatabaseService) extends EKNodeDaoImpl[CIClass](db) with CIClassDao with NamedNodeDaoImpl[CIClass]
{
  override def fromNode(node: Node) : CIClass =
  {
    new CIClassImpl(node)
  }

  def getByName(schema : Schema, className: String) : Option[CIClass] =
  {
    schema.content.find(c => c.name.equals(className))
  }

  // TODO
  def findByName(className: String) : Set[CIClass] =
  {
    /*
    val iterator = db.getAllNodes().iterator
    val foundNode = iterator.find(n => new CIClassImpl(n).name.equals(className))
    Set.empty[CIClass] ++ iterator.map(n => new CIClassImpl(n)).filter(c => c.name.equals(className))
    */
    Set.empty[CIClass]
  }
}