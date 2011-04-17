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

import org.escapek.ekcmdb.tools.neo4j.Neo4JWrapper
import org.neo4j.graphdb.{Direction, Node}
import org.escapek.ekcmdb.core.model.{PropertyType, Property}

/**
 * Created by IntelliJ IDEA.
 * User: Jouanin
 * Date: 01/03/11
 * Time: 15:20
 */

class PropertyImpl(override val node:Node) extends EKNodeImpl(node) with Property with NamedlNodeImpl
{
  override def className = PropertyImpl.className

  def overrides = {
    if(node.hasRelationship(RepositoryRelationships.Rel_PropertyOverrides, Direction.OUTGOING))
      Some(new PropertyImpl(
        node.getSingleRelationship(RepositoryRelationships.Rel_PropertyOverrides, Direction.OUTGOING).getEndNode))
    else
      None
  }

  def defaultValue = {
    node(PropertyImpl.Prop_defaultValue).asInstanceOf[String]
  }

  def defaultValue_=(value:String) = {
    node.setProperty(PropertyImpl.Prop_defaultValue, value)
  }

  def restrictions = {
    node(PropertyImpl.Prop_restrictions).asInstanceOf[String]
  }

  def restrictions_=(value:String) = {
    node.setProperty(PropertyImpl.Prop_restrictions, value)
  }

  def cardinality = {
    node(PropertyImpl.Prop_cardinality).asInstanceOf[String]
  }

  def cardinality_=(card:String) = {
    node.setProperty(PropertyImpl.Prop_cardinality, card)
  }

  def referencedClass = {
    if(node.hasRelationship(RepositoryRelationships.Rel_PropertyReferencesClass, Direction.OUTGOING))
      Some(new CIClassImpl(
        node.getSingleRelationship(RepositoryRelationships.Rel_PropertyReferencesClass, Direction.OUTGOING).getEndNode))
    else
      None
  }

  def propertyType = {
    PropertyType.withName(node(PropertyImpl.Prop_propertyType).asInstanceOf[String])
  }

  def propertyType_=(t:PropertyType.PropertyType) = {
    node.setProperty(PropertyImpl.Prop_propertyType, t.toString)
  }

}

object PropertyImpl
{
  val className = "Property"
  val Prop_defaultValue = className + "." + "defaultValue"
  val Prop_restrictions = className + "." + "restrictions"
  val Prop_cardinality = className + "." + "cardinality"
  val Prop_propertyType = className + "." + "propertyType"
}