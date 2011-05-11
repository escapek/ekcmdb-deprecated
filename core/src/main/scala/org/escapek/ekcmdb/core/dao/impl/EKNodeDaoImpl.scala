package org.escapek.ekcmdb.core.dao.impl
import org.escapek.ekcmdb.core.model.EKNode
import org.neo4j.graphdb.{ NotFoundException, Node, GraphDatabaseService }
import org.escapek.ekcmdb.core.dao.EKNodeDao

abstract class EKNodeDaoImpl[T <: EKNode](val db: GraphDatabaseService) extends EKNodeDao[T] {

  implicit protected def fromNode(node: Node): T

  def getById(id: Long): Option[T] = {
    try {
      Some(db.getNodeById(id))
    }
    catch {
      case ex: NotFoundException => None
    }
  }
}