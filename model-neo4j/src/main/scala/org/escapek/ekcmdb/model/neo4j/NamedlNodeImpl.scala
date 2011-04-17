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

import org.escapek.ekcmdb.core.model.NamedNode
import org.neo4j.graphdb.Node

trait NamedlNodeImpl extends NamedNode
{
  def node:Node

	def name = {
		node.getProperty(NamedlNodeImpl.Prop_Name).asInstanceOf[String]
	}
  
	def name_=(n:String) {
		node.setProperty(NamedlNodeImpl.Prop_Name , n)
	}

	def displayName = {
		node.getProperty(NamedlNodeImpl.Prop_DisplayName).asInstanceOf[String]
	}
	def displayName_=(n:String) {
		node.setProperty(NamedlNodeImpl.Prop_DisplayName , n)
	}

	def description = {
		node.getProperty(NamedlNodeImpl.Prop_Description).asInstanceOf[String]
	}
	def description_=(n:String) {
		node.setProperty(NamedlNodeImpl.Prop_Description , n)
	}
}

object NamedlNodeImpl
{
  val className = "NamedNode"
	val Prop_Name = className + ".name"
	val Prop_DisplayName = className + ".displayName"
	val Prop_Description = className + ".description"
}