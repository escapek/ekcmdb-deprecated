package org.escapek.ekcmdb.model

trait Schema extends NamedNode
{
	def content : Set[CIClass]
}