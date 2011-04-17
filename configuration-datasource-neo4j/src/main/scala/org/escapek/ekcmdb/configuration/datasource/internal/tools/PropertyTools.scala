/** * Copyright (C) 2011 njouanin - http://www.escapek.org/ - <EscapeK>  * * Licensed under the Apache License, Version 2.0 (the "License"); * you may not use this file except in compliance with the License. * You may obtain a copy of the License at * *        http://www.apache.org/licenses/LICENSE-2.0 * * Unless required by applicable law or agreed to in writing, software * distributed under the License is distributed on an "AS IS" BASIS, * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. * See the License for the specific language governing permissions and * limitations under the License. */package org.escapek.ekcmdb.configuration.datasource.internal.tools
import java.util.Dictionary
import scala.collection.JavaConverters._
class PropertyTools {	}
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