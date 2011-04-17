package org.escapek.ekcmdb.configuration.datasource.internal.tools
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
	
	/**
		collection.immutable.Map(
}