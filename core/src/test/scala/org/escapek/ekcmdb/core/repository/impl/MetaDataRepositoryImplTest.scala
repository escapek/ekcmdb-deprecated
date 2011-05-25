package org.escapek.ekcmdb.core.repository.impl

import org.junit._
import org.junit.Assert._
import org.neo4j.kernel.EmbeddedGraphDatabase
import org.neo4j.graphdb.{GraphDatabaseService, Node}
import org.escapek.ekcmdb.core.domain.EKNode
import org.escapek.ekcmdb.core.domain.MetaData
import org.escapek.ekcmdb.core.domain.impl.EKNodeImpl

class MetaDataDaoImplTest {

  class testNode(override val baseNode:Node) extends EKNodeImpl(baseNode) {
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
    val meta: MetaData = metaDataDao.addMetaData(tNode, "testKey", "value").get
    
    assertEquals("testKey", meta.key)
    meta.value match {
      case Some(s:String) => assertEquals("value", s)
      case _ => fail
    }
    tx.success
    tx.finish
  }
  
  @Test
  def testAddAndGetMetaData() = {
    val metaDataDao = new MetaDataRepositoryImpl(graphDB)
    
    //Create a node with some metadata
    val tx = graphDB.beginTx
    val tNode = testNodeDao.createNewInstance
    metaDataDao.addMetaData(tNode, "testKey", "value")
    tx.success
    tx.finish

    //Check if metadata is found
    val tx2 = graphDB.beginTx
    metaDataDao.findMetaData(tNode, "testKey") match {
      case Some(m:MetaData) => {
        assertEquals("testKey", m.key)
        assertEquals("value", m.value.get)
      }
      case _ => fail
    }
    tx2.success
    tx2.finish
  }
  
  @Test
  def testAddAndFindAllMetaData() = {
    val metaDataDao = new MetaDataRepositoryImpl(graphDB)
    
    //Create a node with some metadata
    val tx = graphDB.beginTx
    val tNode = testNodeDao.createNewInstance
    metaDataDao.addMetaData(tNode, "a", "1")
    metaDataDao.addMetaData(tNode, "b", "2")
    tx.success
    tx.finish

    //Check that the same number of metadata are found
    val tx2 = graphDB.beginTx
    assertEquals(2, metaDataDao.findMetaData(tNode).size)
    tx2.success
    tx2.finish
  }
  
  @After
  def shutdown() = {
    graphDB.shutdown
  }
}