package org.escapek.ekcmdb.core.dao.impl

import org.junit._
import org.neo4j.kernel.EmbeddedGraphDatabase
import org.neo4j.graphdb.{GraphDatabaseService, Node}
import org.escapek.ekcmdb.core.model.EKNode
import org.escapek.ekcmdb.core.model.impl.EKNodeImpl

class EKNodeDaoImplTest {

  class testNode(override val aNode:Node) extends EKNodeImpl(aNode) {
    def nodeType = "testNode"
    
    def someProperty = {
      baseNode.getProperty("someProperty").asInstanceOf[String]
    }
    def someProperty_=(value: String) {
      baseNode.setProperty("someProperty", value)
    }
    
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