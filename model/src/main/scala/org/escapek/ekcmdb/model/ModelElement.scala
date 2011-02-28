package org.escapek.ekcmdb.model

trait ModelElement extends RepositoryElement
{
	def name : String
	def displayName : String
	def description : String
}