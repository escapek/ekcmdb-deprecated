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
package org.escapek.ekcmdb.core.neo4j.tools
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