package org.escapek.ekcmdb.cmdb.model.impl
import org.escapek.ekcmdb.core.model.impl.EKNodeImpl
import org.escapek.ekcmdb.cmdb.model.{Domain, Mixin}
import org.neo4j.graphdb.{ Node, Direction }
class DomainImpl(override val aNode:Node) extends EKNodeImpl(aNode) with Domain {
}