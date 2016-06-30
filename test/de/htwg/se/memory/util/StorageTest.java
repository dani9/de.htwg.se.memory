package de.htwg.se.memory.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class StorageTest {
	Storage store;

	@Before
	public void preparation() {
		try {
			store = new Storage();
		} catch (Exception e) {
		}

	}

	@Test
	public void testSaveLoadSerializable() {
		String test = "test2";
		assertTrue(store.saveSerializable(test, "TestSave0", "default"));
		String tmp = (String) store.loadSerializable("TestSave0", "default");

		assertEquals(test, tmp);
	}

}
