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
package org.escapek.ekcmdb.core.dao.impl
import org.escapek.ekcmdb.core.model.impl.Neo4JNodeContainer
import org.escapek.ekcmdb.core.model.impl.MetaDataImpl
import org.escapek.ekcmdb.core.model.{EKNode, MetaData}
import org.escapek.ekcmdb.core.model.impl.EKNodeImpl
import org.neo4j.graphdb.{ NotFoundException, Node, GraphDatabaseService, Direction, Relationship }
import org.escapek.ekcmdb.core.dao.EKNodeDao
import org.escapek.ekcmdb.core.dao.MetaDataDao
import scala.collection.JavaConversions._

class MetaDataDaoImpl(val db: GraphDatabaseService) extends MetaDataDao {
  
  override def getMetaData(ekNode: EKNode): Set[MetaData] = {
    ekNode match {
      case nodeContainer : Neo4JNodeContainer => {
        val neo4jNode = nodeContainer.baseNode
        val iterator = 
          neo4jNode.getRelationships(EKNodeImpl.Rel_EKNodeHasMetaData, Direction.OUTGOING).iterator
        Set.empty[MetaData] ++ iterator.map( r => new MetaDataImpl(r.getEndNode()) )
      }
      case _ => Set.empty[MetaData]
    }
  }

  override def getMetaData(ekNode: EKNode, name: String): Option[MetaData] = {
    getMetaData(ekNode).filter( _.key.equals(name) ).headOption
  }
    
  override def addMetaData(ekNode: EKNode, name: String, value: Any): Option[MetaData] = {
    ekNode match {
      case ekNodeContainer : Neo4JNodeContainer => {
        val newMeta = new MetaDataImpl(db.createNode)
        newMeta.key = name
        newMeta.value = value
        ekNodeContainer.baseNode.createRelationshipTo(newMeta.baseNode, EKNodeImpl.Rel_EKNodeHasMetaData)
        Some(newMeta)
      }
      case _ => None
    }
  }
}

object MetaDataDaoImpl {
  val RelProp_EKNodeHasMetaData_name = "metaData.name"
  val NodeProp_MetaData_value = "metaData.value"
}