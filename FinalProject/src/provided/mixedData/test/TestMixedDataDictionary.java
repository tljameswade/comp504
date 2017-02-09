package provided.mixedData.test;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;

import provided.mixedData.*;

/**
 * Tests for the mixed data dictionary
 * @author Stephen
 *
 */
public class TestMixedDataDictionary {

	/**
	 * mixed data dictionary tests
	 */
	@Test
	public void testMixedDataDictionary() {
		UUID uuid = UUID.randomUUID();
		MixedDataKey<Integer> intKey1 = new MixedDataKey<Integer>(uuid, "description A", Integer.class);
		MixedDataKey<String> strKey1 = new MixedDataKey<String>(uuid, "description A", String.class);  // differs only in class from intKey1
		MixedDataKey<Integer> intKey2 = new MixedDataKey<Integer>(UUID.randomUUID(), "description A", Integer.class); // differs only in UUID from intKey1
		MixedDataKey<Integer> intKey3 = new MixedDataKey<Integer>(uuid, "description B", Integer.class); // differs only in descr from intKey1
		MixedDataKey<Integer> intKey4 = new MixedDataKey<Integer>(uuid, "description A", Integer.class); // same as intKey1
		MixedDataKey<String> strKey2 = new MixedDataKey<String>(uuid, "description C", String.class);  // differs only in desc from strKey1

		
		
		
		IMixedDataDictionary dict = new MixedDataDictionary();
		
		// test is two keys are the same.
		assertTrue(intKey1.equals(intKey4));
		assertFalse(intKey1.equals(intKey2));
		assertFalse(intKey1.equals(intKey3));
		assertFalse(intKey1.equals(strKey1));
		
		// check for value in an empty dictionary
		assertFalse(dict.containsKey(intKey1));

		// put int in and retrieve it
		dict.put(intKey1, 42);
		int intResults = dict.get(intKey1);
		assertEquals("Integer key", 42, intResults );

		// check if a value is associated with a key that is equivalent to the key used to store the object.
		assertTrue(dict.containsKey(intKey4));

		// use different key instance to retrieve value
		intResults = dict.get(intKey4);
		assertEquals("Integer key", 42, intResults );

		// put int in and retrieve it with a different key
		dict.put(intKey2, 99);
		intResults = dict.get(intKey2);
		assertEquals("Integer key 2", 99, intResults );
		
		// put int in and retrieve it with a different key
		dict.put(intKey3, -1);
		intResults = dict.get(intKey3);
		assertEquals("Integer key 3", -1, intResults );

		// put int in and retrieve it with a different key
		dict.put(intKey4, 11223344);
		intResults = dict.get(intKey4);
		assertEquals("Integer key 4", 11223344, intResults );

		

		// put in a string and retrieve it
		dict.put(strKey1, "Howdy!");		
		String strResult = dict.get(strKey1);
		assertEquals("String key", "Howdy!", strResult );
		
		// put in a string and retrieve it
		dict.put(strKey2, "Yahoo!");		
		strResult = dict.get(strKey2);
		assertEquals("String key", "Yahoo!", strResult );		
		
		
	}


}
