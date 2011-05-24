package org.escapek.ekcmdb.cmdb.repository
import org.escapek.ekcmdb.core.repository.EKNodeRepository
import org.escapek.ekcmdb.cmdb.domain.Domain

trait DomainRepository extends EKNodeRepository[Domain] {
  def create(domainName: String) : Domain
  def findByName(name: String) : Option[Domain]
}