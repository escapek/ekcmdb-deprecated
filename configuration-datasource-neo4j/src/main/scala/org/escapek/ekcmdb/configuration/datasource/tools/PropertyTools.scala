package org.escapek.ekcmdb.configuration.datasource.tools
import scala.collection.immutable.HashMap

import java.util.Dictionary
import scala.collection.JavaConverters._

object PropertyTools {
	/**
	 * filter a map by keeping only elements which keys starts with the given prefix.
	 * Prefix is also removed from keys in the new map.
	 */
	def filterHashMap(prefix : String, properties: Map[String,_]) : Map[String, _] = {
		properties.
			filterKeys { key => key.startsWith(prefix) }.
			map { kv => ( kv._1.replaceAll(prefix,""), kv._2) }
	}
	
	def convertDictionary(dict: Dictionary[_,_]) : Map[String, String] = {
				val nnn = dict.asScala		val test = nnn.map { kv => (kv._1.toString, kv._2.toString) }
	}
}