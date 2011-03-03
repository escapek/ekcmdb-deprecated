package org.escapek.ekcmdb.model

trait EKNode
{
	def id : Long
	def version : String
	def metaData : Map[String, MetaData]
}