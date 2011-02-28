package org.escapek.ekcmdb.model.neo4j

import org.escapek.ekcmdb.model.ModelElement
import org.neo4j.graphdb.Node

abstract class ModelElementImpl(override val node: Node) extends RepositoryElementImpl(node) with ModelElement
{
	def name = {
		node.getProperty(ModelElementImpl.Key_Name).asInstanceOf[String]
	}
	def name_=(n:String) {
		node.setProperty(ModelElementImpl.Key_Name , n)
	}

	def displayName = {
		node.getProperty(ModelElementImpl.Key_DisplayName).asInstanceOf[String]
	}
	def displayNname_=(n:String) {
		node.setProperty(ModelElementImpl.Key_DisplayName , n)
	}

	def description = {
		node.getProperty(ModelElementImpl.Key_Description).asInstanceOf[String]
	}
	def description_=(n:String) {
		node.setProperty(ModelElementImpl.Key_Description , n)
	}
}

object ModelElementImpl
{
	val Key_Name = "name"	
	val Key_DisplayName = "displayName"	
	val Key_Description = "description"	
}