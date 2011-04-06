package org.escapek.ekcmdb.configuration.datasource.tools

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
}