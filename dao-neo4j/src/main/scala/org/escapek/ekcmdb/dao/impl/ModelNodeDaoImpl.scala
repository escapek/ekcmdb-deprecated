package org.escapek.ekcmdb.dao.impl

import org.escapek.ekcmdb.model.ModelNode
import org.escapek.ekcmdb.dao.ModelNodeDao
import org.neo4j.graphdb.GraphDatabaseService

/**
 * Created by IntelliJ IDEA.
 * User: Jouanin
 * Date: 07/03/11
 * Time: 17:40
 * To change this template use File | Settings | File Templates.
 */

abstract class ModelNodeDaoImpl[T <: ModelNode](db:GraphDatabaseService) extends EKNodeDaoImpl[T](db) with ModelNodeDao
{
  def getByName(className : String) : ModelNode = {
    //db.getAllNodes
  }

}