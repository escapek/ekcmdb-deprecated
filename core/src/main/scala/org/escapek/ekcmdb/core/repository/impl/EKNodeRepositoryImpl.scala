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
package org.escapek.ekcmdb.core.repository.impl
import org.escapek.ekcmdb.core.domain.EKNode
import org.escapek.ekcmdb.core.domain.impl.EKNodeImpl
import org.neo4j.graphdb.{ NotFoundException, Node, GraphDatabaseService, Direction, Relationship }
import org.escapek.ekcmdb.core.repository.EKNodeRepository
import scala.collection.JavaConversions._

abstract class EKNodeRepositoryImpl[T <: EKNode](val db: GraphDatabaseService)
  extends EKNodeRepository[T] {

  implicit def load(node: Node): T

  override def getById(id: Long): Option[T] = {
    try {
      Some(db.getNodeById(id))
    }
    catch {
      case ex: NotFoundException => None
    }
  }
}

object EKNodeDaoImpl {
  import org.neo4j.graphdb.DynamicRelationshipType.withName
}