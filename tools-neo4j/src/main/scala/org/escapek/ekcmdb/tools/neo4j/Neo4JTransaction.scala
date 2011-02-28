package org.escapek.ekcmdb.tools.neo4j
import org.neo4j.graphdb.GraphDatabaseService

trait Neo4JTransaction {

	def execInNeo4j[T<:Any](operation: GraphDatabaseService => T)(implicit neo : GraphDatabaseService): T = 
	{
	    val tx = synchronized 
	    {
	      neo.beginTx
	    }
	    try
	    {
	      val ret = operation(neo)
	      tx.success
	      return ret
	    } 
	    finally 
	    {
	      tx.finish
	    }
	}

}