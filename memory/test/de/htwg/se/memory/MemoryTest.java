package de.htwg.se.memory;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class MemoryTest extends TestCase {

	Memory memory;
	@Before
	public void setUp() {

		memory = new de.htwg.se.memory.Memory();
	}
	
	@Test
	public void testSetUp(){
		assertEquals(memory, memory);
	}
}
