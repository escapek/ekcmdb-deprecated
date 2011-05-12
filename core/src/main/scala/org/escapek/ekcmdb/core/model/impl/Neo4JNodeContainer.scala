package org.escapek.ekcmdb.core.model.impl

import org.escapek.ekcmdb.core.tools.Neo4JWrapper
import org.neo4j.graphdb.Node

trait Neo4JNodeContainer extends Neo4JWrapper {
  def baseNode : Node
}