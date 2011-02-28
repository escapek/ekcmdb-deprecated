package org.escapek.ekcmdb.model.neo4j

import org.junit.runner.RunWith
import org.neo4j.kernel.EmbeddedGraphDatabase
import org.neo4j.graphdb.GraphDatabaseService
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

@RunWith(classOf[JUnitRunner])
class SchemaImplTest extends FunSuite {

	test("Managed element creation")
	{
    val graphDB : GraphDatabaseService = new EmbeddedGraphDatabase( "target/test/graphdb" )

		val tx = graphDB.beginTx
		
		val firstNode = graphDB.createNode
		
		val mElem = new SchemaImpl(firstNode)
		
		tx.success
		tx.finish
		println (mElem.id)
		graphDB.shutdown()
	}
}