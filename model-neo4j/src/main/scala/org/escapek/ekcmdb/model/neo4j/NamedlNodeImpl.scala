package org.escapek.ekcmdb.model.neo4j

import org.escapek.ekcmdb.model.NamedNode
import org.neo4j.graphdb.Node

trait NamedlNodeImpl(override val node: Node) with NamedNode
{
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