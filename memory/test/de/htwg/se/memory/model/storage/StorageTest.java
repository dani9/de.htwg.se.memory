package de.htwg.se.memory.model.storage;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.memory.model.storage.Storage;
import de.htwg.se.memory.model.user.User;
import junit.framework.TestCase;

public class StorageTest extends TestCase {
	String userdir = System.getProperty("user.dir")+System.getProperty("file.separator");
	String filepath = userdir ;
	User user;
	Storage box, box1;

	@Before
	public void setUp() throws Exception {
		user = new User("Daniel", "dani", "local");
		user.setPoints(10);
		box = new Storage(filepath);
	}
	@Test
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
	@Test
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
