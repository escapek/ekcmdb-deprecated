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
package org.escapek.ekcmdb.cmdb.domain.impl

import org.escapek.ekcmdb.core.domain.impl.EKNodeImpl
import org.escapek.ekcmdb.cmdb.domain.{ Domain, CIClass }
import org.neo4j.graphdb.{ Node, Direction }
import scala.collection.JavaConversions._
import org.neo4j.graphdb.DynamicRelationshipType.withName
import org.escapek.ekcmdb.cmdb.repository.impl.Relationships

class DomainImpl(override val aNode: Node) extends EKNodeImpl(aNode) with NamedNodeImpl with Domain {
  override def nodeType = "Domain"
  
  def content = {
    val iterator = 
      baseNode.getRelationships(Relationships.Rel_DomainContainsCIClass, Direction.OUTGOING).iterator
    Set.empty[CIClass] ++ iterator.map( r => new CIClassImpl(r.getEndNode()) )
  }
}


object DomainImpl {
}