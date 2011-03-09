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
class EKNodeSpec extends WordSpec with MustMatchers with BeforeAndAfterAll {
  var graphDB : GraphDatabaseService = _

  override def beforeAll(configMap: Map[String, Any])
  {
    graphDB = new EmbeddedGraphDatabase( "target/test/graphdb" )
  }

  override def afterAll(configMap: Map[String, Any])
  {
    graphDB.shutdown()
  }

  "A new EKNode" when {
    "created into the graph database" should {
      "have a Id initialized to a value" in {
        val tx = graphDB.beginTx
        val node = graphDB.createNode
        val ekNode = new EKNodeImpl(node)
        tx.success
        tx.finish
        assert(ekNode.id !=0)
      }

      "have an empty metadata list" in {
        val tx = graphDB.beginTx
        val node = graphDB.createNode
        val ekNode = new EKNodeImpl(node)
        tx.success
        tx.finish
        assert(ekNode.metaData == Map.empty)

      }
    }
  }

  "A new EKNode" should {
    "throw an IllegalArgumentException if no graph node is given in constructor" in {
      intercept[IllegalArgumentException] {
        new EKNodeImpl(null)
      }
    }
  }
}
