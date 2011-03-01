package org.escapek.ekcmdb.model.neo4j

import org.escapek.ekcmdb.model.ModelElement
import org.neo4j.graphdb.Node

abstract class ModelElementImpl(override val node: Node) extends RepositoryElementImpl(node) with ModelElement
{
	def name = {
		node.getProperty(ModelElementImpl.Prop_Name).asInstanceOf[String]
	}
  
	def name_=(n:String) {
		node.setProperty(ModelElementImpl.Prop_Name , n)
	}

	def displayName = {
		node.getProperty(ModelElementImpl.Prop_DisplayName).asInstanceOf[String]
	}
	def displayName_=(n:String) {
		node.setProperty(ModelElementImpl.Prop_DisplayName , n)
	}

	def description = {
		node.getProperty(ModelElementImpl.Prop_Description).asInstanceOf[String]
	}
	def description_=(n:String) {
		node.setProperty(ModelElementImpl.Prop_Description , n)
	}
}

object ModelElementImpl
{
	val Prop_Name = "name"
	val Prop_DisplayName = "displayName"
	val Prop_Description = "description"
}