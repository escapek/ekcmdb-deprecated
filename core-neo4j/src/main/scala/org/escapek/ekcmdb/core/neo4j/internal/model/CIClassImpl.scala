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

import org.neo4j.graphdb._
import org.escapek.ekcmdb.core.neo4j.tools.Neo4JWrapper
import scala.collection.JavaConversions._
import org.escapek.ekcmdb.core.model.{EKNode, Property, CIClass, Schema}


class CIClassImpl(override val node:Node) extends EKNodeImpl(node) with CIClass with NamedlNodeImpl
{
  override def className = CIClassImpl.className

	def schema : Schema = {
		new SchemaImpl(
				node.getSingleRelationship(RepositoryRelationships.Rel_ClassBelongsToSchema, Direction.OUTGOING).getEndNode)
	}

  def isAbstract : Boolean = {
    node(CIClassImpl.Prop_isAbstract).asInstanceOf[Boolean]
  }

  def isAbstract_=(b:Boolean) {
    node.setProperty(CIClassImpl.Prop_isAbstract, b)
  }

	def isFinal : Boolean = {
    node(CIClassImpl.Prop_isFinal).asInstanceOf[Boolean]
  }

  def isFinal_=(b:Boolean) {
    node.setProperty(CIClassImpl.Prop_isFinal, b)
  }

  def isAssociation : Boolean = {
    node(CIClassImpl.Prop_isAssociation).asInstanceOf[Boolean]
  }

  def isAssociation_=(b:Boolean) {
    node.setProperty(CIClassImpl.Prop_isAssociation, b)
  }

	def baseClass : Option[CIClass] = {
    if(node.hasRelationship(RepositoryRelationships.Rel_ClassHasParentClass, Direction.OUTGOING))
      Some(new CIClassImpl(
        node.getSingleRelationship(RepositoryRelationships.Rel_ClassHasParentClass, Direction.OUTGOING).getEndNode))
    else
      None
  }

  // TODO : Think about it : should the relation created inside the POJO or outside in the domain classes
  /*
  def baseClass_=(ciClass:CIClass) = {
    node.createRelationshipTo(ciClass, RepositoryRelationships.Rel_ClassHasParentClass)
  }*/

	def properties : Set[Property] = {
    val iterator =
      node.getRelationships(RepositoryRelationships.Rel_ClassHasProperties,Direction.OUTGOING).iterator
    Set.empty[Property] ++ iterator.map( c => new PropertyImpl(c.getEndNode()))
  }
}

object CIClassImpl
{
  val className = "CIClass"
  val Prop_isAbstract = className + ".isAbstract"
  val Prop_isFinal = className + ".isFinal"
  val Prop_isAssociation = className + ".isAssociation"
}