package org.escapek.ekcmdb.dao

import org.escapek.ekcmdb.model.ModelNode

/**
 * Created by IntelliJ IDEA.
 * User: Jouanin
 * Date: 07/03/11
 * Time: 17:36
 */

trait ModelNodeDao[T]
{
  def getByName(className : String) : Option[T]

}