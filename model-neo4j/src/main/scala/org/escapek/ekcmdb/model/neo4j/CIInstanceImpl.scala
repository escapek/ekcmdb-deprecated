package org.escapek.ekcmdb.model.neo4j

import org.escapek.ekcmdb.core.model.CIInstance
import org.neo4j.graphdb.{Node, Direction}
import scala.collection.JavaConversions._

/**
 * Created by IntelliJ IDEA.
 * User: Jouanin
 * Date: 04/03/11
 * Time: 14:37
 */

class CIInstanceImpl(override val node:Node) extends EKNodeImpl(node) with CIInstance {

  override def className = CIInstanceImpl.className

  def properties =  {
    val iterator =
      node.getRelationships(RepositoryRelationships.Rel_InstanceHasProperties,Direction.OUTGOING).iterator
    Set.empty[InstancePropertyImpl] ++ iterator.map( c => new InstancePropertyImpl(c.getEndNode()))
  }

  def definitionClass = {
    new CIClassImpl(
      node.getSingleRelationship(RepositoryRelationships.Rel_IsInstanceOf, Direction.OUTGOING).getEndNode)
  }
}

object CIInstanceImpl {
  val className = "CIInstance"
}