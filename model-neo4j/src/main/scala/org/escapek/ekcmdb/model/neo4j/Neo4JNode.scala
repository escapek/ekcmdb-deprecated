package org.escapek.ekcmdb.model.neo4j

import org.neo4j.graphdb.Node
import org.escapek.ekcmdb.tools.neo4j.Neo4JWrapper


/**
 * Created by IntelliJ IDEA.
 * User: Jouanin
 * Date: 03/03/11
 * Time: 12:00
 */

abstract class Neo4JNode(val node:Node) extends Neo4JWrapper
{
  require(node != null)
  
  def className : String

  if( !node.hasProperty(Neo4JNode.Prop_nodeClass))
    node.setProperty(Neo4JNode.Prop_nodeClass, className)

  def nodeClass =
  {
    node.getProperty(Neo4JNode.Prop_nodeClass).asInstanceOf[String]
  }
}

object Neo4JNode
{
  val Prop_nodeClass = "Neo4JNode.nodeClass"
}