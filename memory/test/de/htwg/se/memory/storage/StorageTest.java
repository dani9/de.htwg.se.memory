package de.htwg.se.memory.storage;

import de.htwg.se.memory.user.User;
import junit.framework.TestCase;

public class StorageTest extends TestCase {

	String filepath = "/home/dorn/git/";
	User user;
	Storage box, box1;

	//@Before
	public void setUp() throws Exception {
		user = new User("Daniel", "dani", "local");
		user.setPoints(10);
		box = new Storage(filepath);

	}

	public void testsaveUser() {

		try {
			box1 = new Storage(filepath + "dani");
		} catch (Exception e) {
		}

		// fails
		User testuser = new User("", "", "");
		assertFalse(box.save(testuser));

		assertTrue(box.save(user));

	}

	public void testloadUser() {

		assertTrue(box.save(user));

		User user1 = box.load(user);

		// fails
		User user2 = new User("Test", "tt", "test");
		assertNull(box.load(user2));

		assertTrue(user.getName().equals(user1.getName()));
		assertTrue(user.getNickname().equals(user1.getNickname()));
		assertTrue(user.getType().equals(user1.getType()));
		assertEquals(user.getPoints(), user1.getPoints());

	}

}
