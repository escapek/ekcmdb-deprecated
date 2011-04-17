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
package org.escapek.ekcmdb.model.neo4j

import org.junit.runner.RunWith
import org.neo4j.kernel.EmbeddedGraphDatabase
import org.neo4j.graphdb.GraphDatabaseService
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.MustMatchers
import org.scalatest.{WordSpec, BeforeAndAfterAll}

@RunWith(classOf[JUnitRunner])
class SchemaImplSpec extends WordSpec with MustMatchers with BeforeAndAfterAll
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

  "A Schema " must {
    "have a empty content when created" in
    {
      val tx = graphDB.beginTx
      val node = graphDB.createNode
      val schema = new SchemaImpl(node)
  		tx.success
	  	tx.finish
      assert(schema.content == Set.empty)
    }
  }
}