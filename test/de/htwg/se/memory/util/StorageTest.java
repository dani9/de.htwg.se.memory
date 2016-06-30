package de.htwg.se.memory.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class StorageTest {
	Storage store;

	@Before
	public void preparation() {

		store = new Storage();

	}

	@Test
	public void testSaveLoadSerializable() {
		String test = "test2";
		assertTrue(store.saveSerializable(test, "TestSave0", "default"));
		
		
		assertTrue(!store.saveSerializable(test, "", "default"));
		
		
		String tmp = (String) store.loadSerializable("TestSave0", "default");
		
		store.delete("TestSave0");

		assertEquals(test, tmp);
	}

}
