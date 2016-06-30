package de.htwg.se.memory.model.player;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

	Player player1;
	User player2;

	@Before
	public void setUp() {
		player1 = new User("Test", "abc");
		player2 = new User("Test1", "abc1");
	}

	@Test
	public void testGetName() {
		assertEquals(player1.getName(), "Test");
	}

	@Test
	public void testAddPoint() {
		assertEquals(player1.getPoints(), 0);
		for (int i = 1; i < 20; i++) {
			player1.addPoint();
			assertEquals(player1.getPoints(), i);
		}

	}
	
	@Test 
	public void testGetNickname(){
		assertEquals(player2.getNickname(), "abc1");
	}
	
	@Test
	public void testSetPoints(){
		assertEquals(player2.getPoints(), 0);
		
		int newPoints = 431;
		player2.setPoints(newPoints);
		
		assertEquals(player2.getPoints(), newPoints);
	}

}
