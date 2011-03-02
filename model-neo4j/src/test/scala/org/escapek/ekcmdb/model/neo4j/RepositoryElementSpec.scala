package org.escapek.ekcmdb.model.neo4j

import org.neo4j.kernel.EmbeddedGraphDatabase
import org.neo4j.graphdb.{GraphDatabaseService, Node}
import org.scalatest.matchers.MustMatchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfterAll, WordSpec}

/**
 * Created by IntelliJ IDEA.
 * User: Jouanin
 * Date: 01/03/11
 * Time: 17:03
 */
@RunWith(classOf[JUnitRunner])
class RepositoryElementSpec extends WordSpec with MustMatchers with BeforeAndAfterAll {
  var graphDB : GraphDatabaseService = _
  class TestRepositoryElement(override val node:Node) extends RepositoryElementImpl(node)

  override def beforeAll(configMap: Map[String, Any])
  {
    graphDB = new EmbeddedGraphDatabase( "target/test/graphdb" )
  }

  override def afterAll(configMap: Map[String, Any])
  {
    graphDB.shutdown()
  }

  "A new repository element" must {
    "have a Id initialized to a value" in {
      val tx = graphDB.beginTx
      val node = graphDB.createNode
      val mElem = new TestRepositoryElement(node)
  		tx.success
	  	tx.finish
      assert(mElem.id !=0)
    }
    
    "have an empty metadata list" in {
      val tx = graphDB.beginTx
      val node = graphDB.createNode
      val mElem = new TestRepositoryElement(node)
      tx.success
      tx.finish
      assert(mElem.metaData == Map.empty)

    }
  }
}
