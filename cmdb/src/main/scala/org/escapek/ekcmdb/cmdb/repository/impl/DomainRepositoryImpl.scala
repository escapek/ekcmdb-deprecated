package org.escapek.ekcmdb.cmdb.repository.impl
import org.neo4j.graphdb.GraphDatabaseService
import org.escapek.ekcmdb.cmdb.domain.Domain
import org.escapek.ekcmdb.cmdb.repository.DomainRepository
import org.escapek.ekcmdb.core.repository.impl.EKNodeRepositoryImpl
import org.neo4j.graphdb.Node
import org.escapek.ekcmdb.cmdb.domain.impl.DomainImpl

class DomainRepositoryImpl(override val db: GraphDatabaseService) extends EKNodeRepositoryImpl[Domain](db) with DomainRepository {
  val rootNode = db.getReferenceNode
  override def load(node: Node): Domain = { new DomainImpl(node) } 
  
  override def createNewInstance() : Domain = {
    new DomainImpl(db.createNode)
  }

  def create(domainName: String) : Domain = {
    val domain = createNewInstance
    domain.name = domainName
    domain
  }
  
    def findByName(name: String) : Option[Domain] = {
      None
    }
}