package org.escapek.ekcmdb.core.model

trait Schema extends NamedNode
{
	def content : Set[CIClass]
}