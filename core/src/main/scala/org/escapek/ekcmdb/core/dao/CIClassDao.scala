package org.escapek.ekcmdb.core.dao

import org.escapek.ekcmdb.core.model.{Schema, CIClass}

/**
 * Created by IntelliJ IDEA.
 * User: Jouanin
 * Date: 04/03/11
 * Time: 15:48
 */

trait CIClassDao extends EKNodeDao[CIClass] with NameNodeDao[CIClass]
{
  def getByName(schema : Schema, className: String) : Option[CIClass]
  def findByName(className : String) : Set[CIClass]

}