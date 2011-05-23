package org.escapek.ekcmdb.core.repository.impl

import org.junit._
import org.neo4j.kernel.EmbeddedGraphDatabase
import org.neo4j.graphdb.{GraphDatabaseService, Node}
import org.escapek.ekcmdb.core.domain.EKNode
import org.escapek.ekcmdb.core.domain.impl.EKNodeImpl

class MetaDataDaoImplTest {

  class testNode(override val aNode:Node) extends EKNodeImpl(aNode) {
    override def nodeType = "testNode"
  }
  object testNodeDao extends EKNodeRepositoryImpl[testNode](graphDB) {
    override def createNewInstance() = { new testNode(graphDB.createNode) }
    override def load(node:Node) = {new testNode(node)}
  }
  
  var graphDB : GraphDatabaseService = _

  @Before
  def setUpDB() = { 
    graphDB = new EmbeddedGraphDatabase( "target/test/graphdb" )
  }
  
  @Test
  def testAddMetaData() = {
    val metaDataDao = new MetaDataRepositoryImpl(graphDB)
    
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