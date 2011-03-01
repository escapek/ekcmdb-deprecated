package org.escapek.ekcmdb.model.neo4j

import org.neo4j.kernel.EmbeddedGraphDatabase
import org.scalatest.WordSpec
import org.neo4j.graphdb.{GraphDatabaseService, Node}

/**
 * Created by IntelliJ IDEA.
 * User: Jouanin
 * Date: 01/03/11
 * Time: 17:03
 */
class RepositoryElementSpec extends WordSpec {
  class TestRepositoryElement(override val node:Node) extends RepositoryElementImpl(node)

  val graphDB : GraphDatabaseService = new EmbeddedGraphDatabase( "target/test/graphdb" )

  "A new repository element" must {
    "have a Id initialized to a value" in {
      val tx = graphDB.beginTx
      val node = graphDB.createNode
      val mElem = new TestRepositoryElement(node)
  		tx.success
	  	tx.finish
      mElem.id must not be null
    }
    
    "have an empty metadata list" in {
      val tx = graphDB.beginTx
      val node = graphDB.createNode
      val mElem = new TestRepositoryElement(node)
      tx.success
      tx.finish
      mElem.metaData must be empty

    }
  }
}
