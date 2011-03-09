package org.escapek.ekcmdb.dao.impl

import org.escapek.ekcmdb.model.NamedNode
import org.escapek.ekcmdb.dao.ModelNodeDao
import org.neo4j.graphdb.{Node, GraphDatabaseService}
import org.escapek.ekcmdb.model.neo4j.NamedlNodeImpl
import scala.collection.JavaConversions._


/**
 * Created by IntelliJ IDEA.
 * User: Jouanin
 * Date: 07/03/11
 * Time: 17:40
 */

abstract class ModelNodeDaoImpl[T <: NamedNode](db:GraphDatabaseService) extends EKNodeDaoImpl[T](db) with ModelNodeDao[T]
{

}