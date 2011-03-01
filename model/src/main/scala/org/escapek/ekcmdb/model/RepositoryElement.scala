package org.escapek.ekcmdb.model

trait RepositoryElement
{
	def id : Long
	def version : String
	def metaData : Map[String, MetaData]
}