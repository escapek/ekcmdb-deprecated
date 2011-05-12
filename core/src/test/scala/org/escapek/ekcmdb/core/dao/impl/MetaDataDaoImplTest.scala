package org.escapek.ekcmdb.core.dao.impl

import org.junit._
import org.neo4j.kernel.EmbeddedGraphDatabase
import org.neo4j.graphdb.{GraphDatabaseService, Node}
import org.escapek.ekcmdb.core.model.EKNode
import org.escapek.ekcmdb.core.model.impl.EKNodeImpl

class MetaDataDaoImplTest {

  class testNode(override val aNode:Node) extends EKNodeImpl(aNode) {
    def nodeType = "testNode"
  }
  object testNodeDao extends EKNodeDaoImpl[testNode](graphDB)( (node : Node) => new testNode(node) ) {
    def createNewInstance() = { new testNode(graphDB.createNode) }
  }
  
  var graphDB : GraphDatabaseService = _

  @Before
  def setUpDB() = { 
    graphDB = new EmbeddedGraphDatabase( "target/test/graphdb" )
  }
  
  @Test
  def testAddMetaData() = {
    val metaDataDao = new MetaDataDaoImpl(graphDB)
    
    val tx = graphDB.beginTx
    val tNode = testNodeDao.createNewInstance
    metaDataDao.addMetaData(tNode, "testKey", "value")
    tx.success
    tx.finish
  }
  
  @After
  def shutdown() = {
    graphDB.shutdown
  }
}