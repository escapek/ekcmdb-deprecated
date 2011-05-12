package org.escapek.ekcmdb.core.dao.impl
import org.escapek.ekcmdb.core.model.EKNode
import org.escapek.ekcmdb.core.model.impl.EKNodeImpl
import org.neo4j.graphdb.{ NotFoundException, Node, GraphDatabaseService, Direction, Relationship }
import org.escapek.ekcmdb.core.dao.EKNodeDao
import scala.collection.JavaConversions._

abstract class EKNodeDaoImpl[T <: EKNode](val db: GraphDatabaseService)(implicit val node2T : Node => T)
  extends EKNodeDao[T] {

  //implicit protected def node2T(node: Node): T

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