package org.escapek.ekcmdb.dao.impl

import org.escapek.ekcmdb.model.neo4j.EKNodeImpl
import org.escapek.ekcmdb.dao.EKNodeDao
import org.neo4j.graphdb.{Node, GraphDatabaseService}
import reflect.{ClassManifest, Manifest}
import org.escapek.ekcmdb.model.EKNode

/**
 * Created by IntelliJ IDEA.
 * User: Jouanin
 * Date: 07/03/11
 * Time: 16:18
 */

abstract class EKNodeDaoImpl[T <: EKNode](val db : GraphDatabaseService) extends EKNodeDao[T]
{
  def newTInstance(node : Node) : T

  def getById(id:Long) : T =
  {
    newTInstance(db.getNodeById(id))
  }
}