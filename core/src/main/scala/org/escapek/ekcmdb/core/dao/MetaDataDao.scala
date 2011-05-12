package org.escapek.ekcmdb.core.dao

import org.escapek.ekcmdb.core.model.EKNode
import org.escapek.ekcmdb.core.model.MetaData

abstract class MetaDataDao {
  def getMetaData(ekNode: EKNode): Set[MetaData]
  def getMetaData(ekNode: EKNode, name: String): Option[MetaData]
  def addMetaData(ekNode: EKNode, name: String, value: Any): Option[MetaData]
}