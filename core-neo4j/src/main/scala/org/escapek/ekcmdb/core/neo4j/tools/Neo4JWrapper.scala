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

import org.neo4j.graphdb._

trait Neo4JWrapper {

	class NodeRelationshipMethods(node: Node)
	{
		def -->(relType: RelationshipType) = new OutgoingRelationshipBuilder(node, relType)

		// Create incoming relationship

		def <--(relType: RelationshipType) = new IncomingRelationshipBuilder(node, relType)
	}

	// Half-way through building an outgoing relationship
	class OutgoingRelationshipBuilder(fromNode: Node, relType: RelationshipType)
	{
	    def -->(toNode: Node) =
	    {
			fromNode.createRelationshipTo(toNode, relType)
			new NodeRelationshipMethods(toNode)
	    }
	}

	// Half-way through building an incoming relationship
	class IncomingRelationshipBuilder(toNode: Node, relType: RelationshipType)
	{
		def <--(fromNode: Node) =
		{
			fromNode.createRelationshipTo(toNode, relType)
			new NodeRelationshipMethods(fromNode)
		}
	}

	implicit def node2relationshipBuilder(node: Node) = new NodeRelationshipMethods(node)

	implicit def string2RelationshipType(relType: String) = DynamicRelationshipType.withName(relType)

	class RichPropertyContainer(propertyContainer: PropertyContainer)
	{
		def apply(property: String): Option[Any] = 
		{
			if (propertyContainer.hasProperty(property))
				Some(propertyContainer.getProperty(property)) 
			else 
				None
		}
		def update(property: String, value: Any): Unit = propertyContainer.setProperty(property, value)
	}

	implicit def propertyContainer2RichPropertyContainer(propertyContainer: PropertyContainer) = new RichPropertyContainer(propertyContainer)

	implicit def fn2StopEvaluator(e: TraversalPosition => Boolean) =
	    new StopEvaluator()
		{
	    	def isStopNode(traversalPosition: TraversalPosition) = e(traversalPosition)
	    }

	implicit def fn2ReturnableEvaluator(e: TraversalPosition => Boolean) =
		new ReturnableEvaluator()
		{
			def isReturnableNode(traversalPosition: TraversalPosition) = e(traversalPosition)
		}

	}