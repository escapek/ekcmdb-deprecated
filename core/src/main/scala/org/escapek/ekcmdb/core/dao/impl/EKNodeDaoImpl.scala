package org.escapek.ekcmdb.core.dao.impl
import org.escapek.ekcmdb.core.model.EKNode
import org.escapek.ekcmdb.core.model.impl.EKNodeImpl
import org.neo4j.graphdb.{ NotFoundException, Node, GraphDatabaseService, Direction, Relationship }
import org.escapek.ekcmdb.core.dao.EKNodeDao
import scala.collection.JavaConversions._

abstract class EKNodeDaoImpl[T <: EKNode](val db: GraphDatabaseService)(implicit val node2T : Node => T)
  extends EKNodeDao[T] {

  //implicit protected def node2T(node: Node): T

  private def T2Node(ekNode : T) : Option[Node] = {
    try {
      Some(ekNode.asInstanceOf[EKNodeImpl].node)
    }
    catch {
      case cce : ClassCastException => None
    }
  }
  
  
  def getById(id: Long): Option[T] = {
    try {
      Some(db.getNodeById(id))
    }
    catch {
      case ex: NotFoundException => None
    }
  }

  def getMetaData(ekNode: T) : Map[String, Any] = {
    T2Node(ekNode) match {
      case Some(node: Node) => {
        val iterator = node.getRelationships(EKNodeDaoImpl.Rel_EKNodeHasMetaData, Direction.OUTGOING).iterator
        Map.empty[String, Any] ++ iterator.map( r => getMetaDataPair(r) )
      }
      case None => Map.empty[String, Any]
    }
  }

  private def getMetaDataPair(r: Relationship) : (String, Any) = {
    (r.getProperty(EKNodeDaoImpl.RelProp_EKNodeHasMetaData_name).asInstanceOf[String],
     r.getEndNode().getProperty(EKNodeDaoImpl.NodeProp_MetaData_value))  
  }
  
/*
  def getMetaData(ekNode: T, name : String)
    
  def addMetaData(node: T) = {
    node.asInstanceOf[EKNodeImpl].node
  }
*/  
}

object EKNodeDaoImpl {
  import org.neo4j.graphdb.DynamicRelationshipType.withName

  val Rel_EKNodeHasMetaData = withName("EKNODE_HAS_METADATA")
  val RelProp_EKNodeHasMetaData_name = "metaData.name"
  val NodeProp_MetaData_value = "metaData.value"
}