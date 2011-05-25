package org.escapek.ekcmdb.core.domain.impl

import org.junit._
import org.junit.Assert._
import org.neo4j.kernel.EmbeddedGraphDatabase
import org.neo4j.graphdb.{GraphDatabaseService, Node}
import org.escapek.ekcmdb.core.domain.EKNode
import org.escapek.ekcmdb.core.domain.MetaData

class MetaDataImplTest {

  var graphDB : GraphDatabaseService = _

  @Before
  def setUpDB() = { 
    graphDB = new EmbeddedGraphDatabase( "target/test/graphdb" )
  }

  @Test def testCreateMetadataImpl() = {
    val tx = graphDB.beginTx
    val n : Node = graphDB.createNode
    val m = new MetaDataImpl(n)
    assertEquals(n, m.baseNode)
    tx.success
    tx.finish
  }

  @After
  def shutdown() = {
    graphDB.shutdown
  }
}