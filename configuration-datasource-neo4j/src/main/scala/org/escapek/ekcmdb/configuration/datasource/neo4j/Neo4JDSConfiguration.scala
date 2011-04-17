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
package org.escapek.ekcmdb.configuration.datasource.neo4j

import org.escapek.ekcmdb.configuration.datasource.internal.tools.PropertyTools
import collection.JavaConverters._
import scala.collection.immutable.HashMap
import org.escapek.ekcmdb.core.tools.Logging
import java.util.Dictionary
import org.neo4j.kernel.{AbstractGraphDatabase, EmbeddedGraphDatabase, HighlyAvailableGraphDatabase}
import org.osgi.service.cm.ManagedService
class Neo4JDSConfiguration extends ManagedService with Logging {

	var dataSource : AbstractGraphDatabase = _
	
	val defaults = Map(
			Neo4JDSConfiguration.EnableHA -> "false",
			Neo4JDSConfiguration.storeDir -> defaultStoreDir)
	
	def updated(properties: Dictionary[_ <: Any,_ <: Any]): Unit = { 
		properties match {
			case null => logger.warn("Neo4j datasource configuration deleted !")
			case _ => configureDS(properties)
		}
	}
	
	/**
	 * Configure Neo4j datasource based on the properties given
	 */
	private def configureDS(dict: Dictionary[_ <: Any,_ <: Any]): Unit = {
		if(dict == null )
		{
			//TODO : Shutdown previous datasource
		}
		val properties = PropertyTools.convertDictionary(dict)
		
		//Get store location
		val storeId = getStoreId(properties)
		require(!storeId.equals(""), "Neo4j store location is not set properly !")
		
		// Is High availability enabled ?
		val enableHA = getHAEnabled(properties)
		
		val TrueRE = """(TRUE)|(true)|1""".r	
		dataSource = enableHA match {
			//HA mode enabled
			case TrueRE => {
				val neo4jProperties = PropertyTools.filterHashMap(Neo4JDSConfiguration.prefix, properties).asJava
				try {
					new HighlyAvailableGraphDatabase(storeId, neo4jProperties)
				}
				catch {
					case ncdfe : NoClassDefFoundError => {
						logger.error("Neo4J HA bundle not found, switching to embedded mode.", ncdfe)
						new EmbeddedGraphDatabase(storeId)
					}
				}
			}
			//HA mode disabled (default)
			case _ => new EmbeddedGraphDatabase(storeId)
		}
	}

	private def getStoreId(properties : Map[String, String]) : String = {
		properties.get(Neo4JDSConfiguration.storeDir) match {
			case Some(s) => s
			case _ =>  {
				logger.warn("Property {} is not set. Using default : {}", 
						Neo4JDSConfiguration.storeDir, defaults(Neo4JDSConfiguration.storeDir))
				defaults(Neo4JDSConfiguration.storeDir)
			}
		}
	}
	
	private def getHAEnabled(properties : Map[String, String]) : String = {
		properties.get(Neo4JDSConfiguration.EnableHA) match {
			case Some(s) => s
			case _ => {
				logger.warn("Property {} is not set. Using default : {}", 
						Neo4JDSConfiguration.EnableHA, defaults(Neo4JDSConfiguration.EnableHA))
				defaults(Neo4JDSConfiguration.EnableHA)
			}
		}
	}
	
	/**
	 * Get default store directory from system property
	 */
	private def defaultStoreDir() :String = {
		val prop = System.getProperty(Neo4JDSConfiguration.storeDir, "")
		if(prop == null)
			logger.warn("System property " + Neo4JDSConfiguration.storeDir + " is not set")
		prop
	}
}

object Neo4JDSConfiguration {
	val prefix = "org.escapek.ekcmdb.datasource.neo4j"
	val EnableHA = prefix + ".enable_ha"
	val storeDir = prefix + ".store_dir"
}