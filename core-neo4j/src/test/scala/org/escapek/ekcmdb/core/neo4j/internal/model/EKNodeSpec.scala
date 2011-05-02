/**
 * Copyright (C) 2011 njouanin - http://www.escapek.org/ - <EscapeK> 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.escapek.ekcmdb.core.neo4j.internal.model

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
