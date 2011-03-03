package org.escapek.ekcmdb.model

trait Schema extends ModelNode
{
	def content : Set[CIClass]
}