package org.escapek.ekcmdb.core.dao.impl
import org.escapek.ekcmdb.core.model.EKNode
import org.neo4j.graphdb.{ NotFoundException, Node, GraphDatabaseService }
import org.escapek.ekcmdb.core.dao.EKNodeDao

abstract class EKNodeDaoImpl[T <: EKNode](val db: GraphDatabaseService)(implicit val node2T : Node => T)
  extends EKNodeDao[T] {

  //implicit protected def node2T(node: Node): T

  def getById(id: Long): Option[T] = {
    try {
      Some(db.getNodeById(id))
    }
    catch {
      case ex: NotFoundException => None
    }
  }
  
  def addMetaData(node: T) = {
    //node.
  }
}