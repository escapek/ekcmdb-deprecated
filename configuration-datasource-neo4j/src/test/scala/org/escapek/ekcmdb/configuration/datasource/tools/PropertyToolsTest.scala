package org.escapek.ekcmdb.configuration.datasource.tools
import java.{util => ju}

import org.junit.Test
import org.junit.Assert._
import org.escapek.ekcmdb.configuration.datasource.tools.PropertyTools._

class PropertyToolsTest {
	
	val testMap  = Map(
			"test.key1" -> "val1",
			"test.key2" -> "val2",
			"some.key3" -> "val3"
	)
	
	@Test def filterHashMapWithEmptyPrefix() = {
		assertEquals(filterHashMap("",testMap), testMap)
	}

	@Test def filterHashMapWithTestPrefix() = {
		val ret = filterHashMap("test.",testMap)
		assert(ret.size == 2)
	}

	@Test def filterHashMapWithUnknownPrefix() = {
		val ret = filterHashMap("unkown",testMap)
		assert(ret.isEmpty)
	}
	
	@Test def filterHashMapKeyNameCut() = {
		assert(filterHashMap("test.",testMap).forall { (kv) => kv._1.startsWith("key") } )
	}
	
	@Test def convDictionary() = {
		var dic = new ju.Hashtable[Any,Any]()
		dic.put("key","value")
		convertDictionary(dic)
	}
}