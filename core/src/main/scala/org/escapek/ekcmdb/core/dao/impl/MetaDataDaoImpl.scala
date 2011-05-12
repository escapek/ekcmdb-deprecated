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
          neo4jNode.getRelationships(MetaDataDaoImpl.Rel_EKNodeHasMetaData, Direction.OUTGOING).iterator
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
        ekNodeContainer.baseNode.createRelationshipTo(newMeta.baseNode, MetaDataDaoImpl.Rel_EKNodeHasMetaData)
        Some(newMeta)
      }
      case _ => None
    }
  }
}

import org.neo4j.graphdb.DynamicRelationshipType.withName
object MetaDataDaoImpl {
  val Rel_EKNodeHasMetaData = withName("EKNODE_HAS_METADATA")
  val RelProp_EKNodeHasMetaData_name = "metaData.name"
  val NodeProp_MetaData_value = "metaData.value"


}