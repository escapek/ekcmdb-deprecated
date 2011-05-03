/**
 * Copyright (C) 2011 njouanin - http://www.escapek.org/ - <EscapeK> 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.escapek.ekcmdb.core.neo4j

import org.osgi.service.event.Event
import org.osgi.service.event.EventAdmin
import org.escapek.ekcmdb.core.neo4j.internal.tools.PropertyTools
import collection.JavaConverters._
import scala.collection.immutable.HashMap
import org.escapek.ekcmdb.core.tools.Logging
import java.util.Dictionary
import org.neo4j.kernel.{AbstractGraphDatabase, EmbeddedGraphDatabase}
import org.osgi.framework.BundleContext
import org.osgi.service.cm.ManagedService

class GraphDBConfigurationService extends ManagedService with Logging
{
  
	@scala.reflect.BeanProperty var context : BundleContext = _

	@scala.reflect.BeanProperty var eventAdmin : EventAdmin = _

	var dataSource : AbstractGraphDatabase = _
	
	val defaults = Map(
			GraphDBConfigurationService.EnableHA -> "false",
			GraphDBConfigurationService.storeDir -> defaultStoreDir)
	
	def updated(properties: Dictionary[_ <: Any,_ <: Any]): Unit = { 
		properties match
		{
			case null => {
				closeGraphDb(dataSource)
				unregisterGraphDB(dataSource)
				dataSource = null
}
			case _ => { 
			  dataSource = openGraphDB(properties)
			  registerGraphDB(dataSource)
			}
		}
	}
	
	/**
	 * Open or creates a new Neo4j graph database using the properties given
	 */
	private def openGraphDB(dict: Dictionary[_ <: Any,_ <: Any]): AbstractGraphDatabase = {
		if(dict == null )
		{
			//TODO : Shutdown previous datasource
		}
		val properties = PropertyTools.convertDictionary(dict)
		
		//Get store location
		val storeDir = getStoreDir(properties)
		require(!storeDir.equals(""), "Neo4j store location is not set properly !")
		
		// Is High availability enabled ?
		val enableHA = getHAEnabled(properties)
		
		val TrueRE = """(TRUE)|(true)|1""".r	
		val db = enableHA match {
			//HA mode enabled
			case TrueRE => {
				val neo4jProperties = PropertyTools.filterHashMap(GraphDBConfigurationService.pid, properties).asJava
				try {
					//new HighlyAvailableGraphDatabase(storeId, properties.asJava)
					logger.warn("Neo4J HA graph database is currently not supported.")
					new EmbeddedGraphDatabase(storeDir)
				}
				catch {
					case ncdfe : NoClassDefFoundError => {
						logger.error("Neo4J HA bundle not found, switching back to embedded mode.", ncdfe)
						new EmbeddedGraphDatabase(storeDir)
					}
				}
			}
			//HA mode disabled (default)
			case _ => new EmbeddedGraphDatabase(storeDir)
		}
		logger.info("Graph database successufully initialized.")
		db
	}

	/**
	 * 
	 * Shutdown a graph database
	 */
	private def closeGraphDb(db: AbstractGraphDatabase): Unit = {
	  db.shutdown
	}

	/**
	 * Send SHUTDOWN_IN_PROGRESS event and unregister graph database from OSGi services
	 */
	private def unregisterGraphDB(db: AbstractGraphDatabase) = {
	    import java.util.HashMap
		if(eventAdmin != null) {
		  val event = new Event(GraphDBConfigurationService.eventTopic + "SHUTDOWN_IN_PROGRESS", new HashMap())
		  eventAdmin.sendEvent(event)
		}
	}

	/**
	 * register graph database as an OSGi service and sent a OPENED event admin
	 */
	private def registerGraphDB(db: AbstractGraphDatabase) = {
	    import java.util.HashMap

		context.registerService(classOf[AbstractGraphDatabase].getName, db, null)
		if(eventAdmin != null) {
		  val event = new Event(GraphDBConfigurationService.eventTopic + "OPENED", new HashMap())
		  eventAdmin.postEvent(event)
		}
		logger.info("Graph database service registered.")
	}
	
	private def getStoreDir(properties : Map[String, String]) : String = {
		properties.get(GraphDBConfigurationService.storeDir) match {
			case Some(s) => s
			case _ =>  {
				logger.warn("Property {} is not set. Using default : {}", 
						GraphDBConfigurationService.storeDir, defaults(GraphDBConfigurationService.storeDir))
				defaults(GraphDBConfigurationService.storeDir)
			}
		}
	}
	
	private def getHAEnabled(properties : Map[String, String]) : String = {
		properties.get(GraphDBConfigurationService.EnableHA) match {
			case Some(s) => s
			case _ => {
				logger.warn("Property {} is not set. Using default : {}", 
						GraphDBConfigurationService.EnableHA, defaults(GraphDBConfigurationService.EnableHA))
				defaults(GraphDBConfigurationService.EnableHA)
			}
		}
	}
	
	/**
	 * Get default store directory from system property
	 */
	private def defaultStoreDir() :String = {
		val prop = System.getProperty(GraphDBConfigurationService.storeDir, "")
		if(prop == null)
			logger.warn("System property " + GraphDBConfigurationService.storeDir + " is not set")
		prop
	}
}

object GraphDBConfigurationService {
	val pid = "org.escapek.ekcmdb.core.neo4j"
	val EnableHA = "ha.enable"
	val storeDir = "store.dir"
	val eventTopic = "org/escapek/ekcmdb/core/neo4j/graphDB/"
}