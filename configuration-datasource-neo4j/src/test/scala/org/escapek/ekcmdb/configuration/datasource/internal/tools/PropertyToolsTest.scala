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
package org.escapek.ekcmdb.configuration.datasource.internal.tools
import java.{util => ju}

import org.junit.Test
import org.junit.Assert._
import org.escapek.ekcmdb.configuration.datasource.internal.tools.PropertyTools._

class PropertyToolsTest {
	
	val testMap  = Map(
			"test.key1" -> "val1",
			"test.key2" -> "val2",
			"some.key3" -> "val3"
	)
	
	@Test def filterHashMapWithEmptyPrefix() : Unit = {
		assertEquals(filterHashMap("",testMap), testMap)
	}

	@Test def filterHashMapWithTestPrefix() : Unit = {
		val ret = filterHashMap("test.",testMap)
		assert(ret.size == 2)
	}

	@Test def filterHashMapWithUnknownPrefix() : Unit = {
		val ret = filterHashMap("unkown",testMap)
		assert(ret.isEmpty)
	}
	
	@Test def filterHashMapKeyNameCut() : Unit = {
		assert(filterHashMap("test.",testMap).forall { (kv) => kv._1.startsWith("key") } )
	}
	
	@Test def convDictionary() : Unit = {
		var dic = new ju.Hashtable[Any,Any]()
		dic.put("key","value")
		convertDictionary(dic)
	}
}