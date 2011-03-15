package org.escapek.ekcmdb.core.model

/**
 * Created by IntelliJ IDEA.
 * User: Jouanin
 * Date: 04/03/11
 * Time: 11:45
 */

trait CIInstance extends EKNode
{
  def definitionClass : CIClass
  def properties : Set[InstanceProperty]
}