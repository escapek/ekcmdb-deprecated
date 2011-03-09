package org.escapek.ekcmdb.dao.impl

import org.escapek.ekcmdb.model.NamedNode
import org.escapek.ekcmdb.dao.NameNodeDao
import org.neo4j.graphdb.{Node, GraphDatabaseService}
import org.escapek.ekcmdb.model.neo4j.NamedlNodeImpl


/**
 * Created by IntelliJ IDEA.
 * User: Jouanin
 * Date: 07/03/11
 * Time: 17:40
 */

trait NamedNodeDaoImpl[T <: NamedNode] extends NameNodeDao[T]
{

}