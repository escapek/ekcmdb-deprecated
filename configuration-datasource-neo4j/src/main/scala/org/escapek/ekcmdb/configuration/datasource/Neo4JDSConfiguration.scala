package org.escapek.ekcmdb.configuration.datasource

import collection.JavaConverters._
import scala.collection.immutable.HashMap
import org.neo4j.kernel.HighlyAvailableGraphDatabase
import org.escapek.ekcmdb.core.tools.Logging
import java.util.Dictionary
import org.neo4j.kernel.{AbstractGraphDatabase, EmbeddedGraphDatabase}
import org.osgi.service.cm.ManagedService
class Neo4JDSConfiguration extends ManagedService with Logging {

	var dataSource : AbstractGraphDatabase = _
	
	val defaults = Map(
			Neo4JDSConfiguration.EnableHA -> "false",
			Neo4JDSConfiguration.storeDir -> defaultStoreDir)
	
	def updated(properties: Dictionary[_,_]): Unit = { 
		properties match {
			case null => logger.warn("Neo4j datasource configuration deleted !")
			case _ => configureDS(properties)
		}
	}
	
	/**
	 * Configure Neo4j datasource based on the properties given
	 */
	private def configureDS(properties: Dictionary[_,_]): Unit = { 
		if(properties != null )
		{
			//TODO : Shutdown previous datasource
		}
		
		//Get store location
		val storeId : String = properties.get(Neo4JDSConfiguration.storeDir) match {
			case s:String => s
			case _ =>  {
				logger.warn("Property {} is not set. Using default : {}", 
						Neo4JDSConfiguration.storeDir, defaults(Neo4JDSConfiguration.storeDir))
				defaults(Neo4JDSConfiguration.storeDir)
			}
		}
		require(!storeId.equals(""), "Neo4j store location is not set properly !")
		
		// Is High availability enabled ?
		val enableHA : String = properties.get(Neo4JDSConfiguration.EnableHA) match {
			case s:String => s
			case _ => {
				logger.warn("Property {} is not set. Using default : {}", 
						Neo4JDSConfiguration.EnableHA, defaults(Neo4JDSConfiguration.EnableHA))
				defaults(Neo4JDSConfiguration.EnableHA)
			}
		}
		
		val TrueRE = """(TRUE)|(true)|1""".r	
		dataSource = enableHA match {
			case TrueRE => new HighlyAvailableGraphDatabase(storeId, extractProperties(properties).asJava)
			case _ => new EmbeddedGraphDatabase(storeId)
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

	/**
	 * Extract Neo4J highly available database properties from bundle properties.
	 * Only properties begining with prefix <code>org.escapek.ekcmdb.datasource.neo4j</code> are kept
	 * Prefix is removed from keys in the new map.
	 */
	private def extractProperties(properties: Dictionary[_,_]) : Map[String, String] = {
		properties.asScala.
			filterKeys { case (key:String) => key.startsWith(Neo4JDSConfiguration.prefix + ".ha") }.
			map { kv => ( kv._1.asInstanceOf[String], kv._2.asInstanceOf[String]) }
		new HashMap[String,String]()
	}
}

object Neo4JDSConfiguration {
	val prefix = "org.escapek.ekcmdb.datasource.neo4j"
	val EnableHA = prefix + ".enable_ha"
	val storeDir = prefix + ".store_dir"
}