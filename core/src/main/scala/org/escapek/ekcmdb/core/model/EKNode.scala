package org.escapek.ekcmdb.core.model

trait EKNode
{
	def id : Long
	def version : String
	def metaData : Map[String, MetaData]
}