package org.escapek.ekcmdb.core.dao

import org.escapek.ekcmdb.core.model.EKNode

/**
 * Created by IntelliJ IDEA.
 * User: Jouanin
 * Date: 07/03/11
 * Time: 16:48
 */

trait EKNodeDao[T <: EKNode]
{
  def getById(id:Long) : Option[T]
}