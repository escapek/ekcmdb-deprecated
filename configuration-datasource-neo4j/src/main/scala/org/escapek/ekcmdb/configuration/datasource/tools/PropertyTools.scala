package org.escapek.ekcmdb.configuration.datasource.tools
import java.util.Dictionary
import scala.collection.JavaConverters._

object PropertyTools {
	/**
	 * filter a map by keeping only elements which keys starts with the given prefix.
	 * Prefix is also removed from keys in the new map.
	 */
	def filterHashMap[T <: Any](prefix : String, properties: Map[String, T]) : Map[String, T] = {
		properties.
			filterKeys { key => key.startsWith(prefix) }.
			map { kv => ( kv._1.replaceAll(prefix,""), kv._2) }
	}
	
	/**	 * Convert a dictionary containing any type of key/value to a Map of String->String	 * May be not the best way to do it.	 */	def convertDictionary(dict: Dictionary[_ <: Any,_ <: Any]) : Map[String, String] = {
		collection.immutable.Map(			dict.asScala.map { kv => (kv._1.toString, kv._2.toString) }.toSeq: _*)	}
}