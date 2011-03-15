package org.escapek.ekcmdb.core.model

/**
 * Created by IntelliJ IDEA.
 * User: Jouanin
 * Date: 03/03/11
 * Time: 16:53
 */

object PropertyType extends Enumeration {
  type PropertyType = Value
  
  val Integer = Value
  val Double = Value
  val Boolean = Value
  val String = Value
  val Reference = Value
  val DateTime = Value
}