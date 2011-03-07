package org.escapek.ekcmdb.dao.impl

import org.escapek.ekcmdb.model.ModelNode
import org.escapek.ekcmdb.dao.ModelNodeDao
import org.neo4j.graphdb.{Node, GraphDatabaseService}
import org.escapek.ekcmdb.model.neo4j.ModelNodeImpl
import scala.collection.JavaConversions._


/**
 * Created by IntelliJ IDEA.
 * User: Jouanin
 * Date: 07/03/11
 * Time: 17:40
 * To change this template use File | Settings | File Templates.
 */

abstract class ModelNodeDaoImpl[T <: ModelNode](db:GraphDatabaseService) extends EKNodeDaoImpl[T](db) with ModelNodeDao[T]
{
  def getByName(nodeName: String) : Option[T] =
  {
    val iterator = db.getAllNodes().iterator
    val foundNode = iterator.find(n => new ModelNodeImpl(n).name.equals(nodeName))
    foundNode match {
      case Some(n:Node) => Some(newTInstance(n))
      case _ => None
  }
/*
    for(n : Node <- iterator;
        modelNode = new ModelNodeImpl(n);
        if modelNode.name.equals(nodeName))
    yield newTInstance(n)
*/
  }

}