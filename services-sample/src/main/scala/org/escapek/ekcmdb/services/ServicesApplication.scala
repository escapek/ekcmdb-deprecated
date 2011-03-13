package org.escapek.ekcmdb.services

import javax.ws.rs.core.Application
import collection.JavaConverters._
import java.{util => ju}

class ServicesApplication extends Application {

  override def getClasses : ju.Set[Class[_]] = {
    val result : Set[Class[_]] = Set(classOf[SchemasResource]) // ++ ...
    result.asJava
  }
}