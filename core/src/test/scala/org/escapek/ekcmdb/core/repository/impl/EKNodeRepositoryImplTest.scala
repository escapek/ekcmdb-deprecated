package org.escapek.ekcmdb.core.repository.impl

import org.junit._
import org.neo4j.kernel.EmbeddedGraphDatabase
import org.neo4j.graphdb.{GraphDatabaseService, Node}
import org.escapek.ekcmdb.core.domain.EKNode
import org.escapek.ekcmdb.core.domain.impl.EKNodeImpl

class EKNodeRepositoryImplTest {

  class testNode(override val baseNode:Node) extends EKNodeImpl(baseNode) {
    override def nodeType = "testNode"
    
    def someProperty = {
      baseNode.getProperty("someProperty").asInstanceOf[String]
    }
    def someProperty_=(value: String) {
      baseNode.setProperty("someProperty", value)
    }
    
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
  def testCreateEKNodeInst() = {
    val tx = graphDB.beginTx
    val tNode = testNodeDao.createNewInstance
    tNode.someProperty = "someValue"
    tx.success
    tx.finish
  }
  
  @Test
  def testCreateEKNodeInstAndCheckNode() = {
    
    //Create node
    var tx = graphDB.beginTx
    val tNode = testNodeDao.createNewInstance
    tNode.someProperty = "someValue"
      
    val id = tNode.baseNode.getId
    tx.success
    tx.finish
    
    //Check node
    tx = graphDB.beginTx
    val node = graphDB.getNodeById(id)
    Assert.assertEquals(node.getProperty(EKNodeImpl.Prop_nodeType), "testNode")
    Assert.assertEquals(node.getProperty("someProperty"), "someValue")
    tx.success
    tx.finish
  }

  @After
  def shutdown() = {
    graphDB.shutdown
  }

}