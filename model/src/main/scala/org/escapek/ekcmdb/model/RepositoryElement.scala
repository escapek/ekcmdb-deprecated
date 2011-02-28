package org.escapek.ekcmdb.model

trait RepositoryElement
{
	def id : Long
	def version : Long
	def metaData : Map[String, MetaData]
}