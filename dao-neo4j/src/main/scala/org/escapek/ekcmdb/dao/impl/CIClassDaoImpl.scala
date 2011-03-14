package org.escapek.ekcmdb.dao.impl

import org.escapek.ekcmdb.dao.CIClassDao
import org.neo4j.graphdb.{Node, GraphDatabaseService}
import org.escapek.ekcmdb.model.neo4j.CIClassImpl
import scala.collection.JavaConversions._
import org.escapek.ekcmdb.core.model.{Schema, CIClass}


/**
 * Created by IntelliJ IDEA.
 * User: nico
 * Date: 07/03/11
 * Time: 21:06
 * To change this template use File | Settings | File Templates.
 */

class CIClassDaoImpl(db:GraphDatabaseService) extends EKNodeDaoImpl[CIClass](db) with CIClassDao with NamedNodeDaoImpl[CIClass]
{
  override def fromNode(node: Node) : CIClass =
  {
    new CIClassImpl(node)
  }

  def getByName(schema : Schema, className: String) : Option[CIClass] =
  {
    schema.content.find(c => c.name.equals(className))
  }

  // TODO
  def findByName(className: String) : Set[CIClass] =
  {
    /*
    val iterator = db.getAllNodes().iterator
    val foundNode = iterator.find(n => new CIClassImpl(n).name.equals(className))
    Set.empty[CIClass] ++ iterator.map(n => new CIClassImpl(n)).filter(c => c.name.equals(className))
    */
    Set.empty[CIClass]
  }
}