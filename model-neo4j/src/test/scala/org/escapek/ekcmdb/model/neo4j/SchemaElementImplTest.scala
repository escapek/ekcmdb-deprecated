package org.escapek.ekcmdb.model.neo4j

import org.junit.Test
import org.neo4j.kernel.EmbeddedGraphDatabase
import org.neo4j.graphdb.GraphDatabaseService

class ManagedElementImplTest {

	@Test def testCreateManagedElementImpl()
	{
		val graphDB : GraphDatabaseService = new EmbeddedGraphDatabase( "target/test/graphdb" )
		
		val tx = graphDB.beginTx
		
		val firstNode = graphDB.createNode
		
		val mElem = new SchemaImpl(firstNode)
		
		tx.success
		tx.finish
		println (mElem.id)
		graphDB.shutdown();
	}
}