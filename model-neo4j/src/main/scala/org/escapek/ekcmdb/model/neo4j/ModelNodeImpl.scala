package org.escapek.ekcmdb.model.neo4j

import org.escapek.ekcmdb.model.ModelNode
import org.neo4j.graphdb.Node

abstract class ModelNodeImpl(override val node: Node) extends EKNodeImpl(node) with ModelNode
{
	def name = {
		node.getProperty(ModelNodeImpl.Prop_Name).asInstanceOf[String]
	}
  
	def name_=(n:String) {
		node.setProperty(ModelNodeImpl.Prop_Name , n)
	}

	def displayName = {
		node.getProperty(ModelNodeImpl.Prop_DisplayName).asInstanceOf[String]
	}
	def displayName_=(n:String) {
		node.setProperty(ModelNodeImpl.Prop_DisplayName , n)
	}

	def description = {
		node.getProperty(ModelNodeImpl.Prop_Description).asInstanceOf[String]
	}
	def description_=(n:String) {
		node.setProperty(ModelNodeImpl.Prop_Description , n)
	}
}

object ModelNodeImpl
{
	val Prop_Name = "name"
	val Prop_DisplayName = "displayName"
	val Prop_Description = "description"
}