package org.escapek.ekcmdb.model.neo4j

import org.junit.runner.RunWith
import org.neo4j.kernel.EmbeddedGraphDatabase
import org.neo4j.graphdb.GraphDatabaseService
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfterAll, FunSuite}

@RunWith(classOf[JUnitRunner])
class SchemaImplSpec extends FunSuite with BeforeAndAfterAll
{
  var graphDB : GraphDatabaseService = _

  override def beforeAll(configMap: Map[String, Any])
  {
    graphDB = new EmbeddedGraphDatabase( "target/test/graphdb" )
  }

  override def afterAll(configMap: Map[String, Any])
  {
    graphDB.shutdown()
  }

	test("Managed element creation")
	{
		val tx = graphDB.beginTx
		
		val firstNode = graphDB.createNode
		val mElem = new SchemaImpl(firstNode)
		
		tx.success
		tx.finish
	}
}