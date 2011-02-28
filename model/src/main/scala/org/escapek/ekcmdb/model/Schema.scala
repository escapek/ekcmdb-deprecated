package org.escapek.ekcmdb.model

trait Schema extends ModelElement
{
	def content : Set[CIClass]
}