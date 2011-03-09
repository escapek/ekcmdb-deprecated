package org.escapek.ekcmdb.dao.impl

import org.escapek.ekcmdb.model.neo4j.EKNodeImpl
import org.escapek.ekcmdb.dao.EKNodeDao
import org.escapek.ekcmdb.model.EKNode
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

  def getById(id:Long) : Option[T] =
  {
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