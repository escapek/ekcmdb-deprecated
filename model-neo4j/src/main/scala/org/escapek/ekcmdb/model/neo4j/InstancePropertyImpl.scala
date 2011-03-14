package org.escapek.ekcmdb.model.neo4j

import org.escapek.ekcmdb.core.model.InstanceProperty
import org.neo4j.graphdb.{Direction, Node}

/**
 * Created by IntelliJ IDEA.
 * User: Jouanin
 * Date: 04/03/11
 * Time: 14:47
 * To change this template use File | Settings | File Templates.
 */

class InstancePropertyImpl(override val node:Node) extends EKNodeImpl(node) with InstanceProperty {

  override def className = InstancePropertyImpl.className

  def definitionProperty = {
    new PropertyImpl(
      node.getSingleRelationship(RepositoryRelationships.Rel_InstancePropertyIsDefinedBy, Direction.OUTGOING).getEndNode)
  }

}

object InstancePropertyImpl {
  def className = "InstanceProperty"
}