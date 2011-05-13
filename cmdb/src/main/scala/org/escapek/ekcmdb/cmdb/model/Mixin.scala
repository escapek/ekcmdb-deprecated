package org.escapek.ekcmdb.cmdb.model

trait Mixin {
  def domain: Domain
  def isFinal: Boolean
  def superMixin: Option[Mixin]
  def properties : Set[Property]
}