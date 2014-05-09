package de.htwg.se.memory.ai;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class TestAi extends TestCase {

	Ai ai;
	@Before
	public void setUp() {

		ai = new de.htwg.se.memory.ai.Ai();
	}
	
	@Test
	public void testSetUp(){
		assertEquals(ai, ai);
	}
}
