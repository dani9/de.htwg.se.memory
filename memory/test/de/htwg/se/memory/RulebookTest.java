package de.htwg.se.memory;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class RulebookTest extends TestCase {

	Rulebook rulebook;
	@Before
	public void setUp() {

		rulebook = new de.htwg.se.memory.Rulebook();
	}
	
	@Test
	public void testSetUp(){
		assertEquals(rulebook, rulebook);
	}
}
