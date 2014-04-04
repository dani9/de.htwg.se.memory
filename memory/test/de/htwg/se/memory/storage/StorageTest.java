package de.htwg.se.memory.storage;

import de.htwg.se.memory.user.User;
import junit.framework.TestCase;
import org.junit.Before;

public class StorageTest extends TestCase {

	String filepath = "/home/dorn/";
	User user;
	Storage box;

	@Before
	public void setUp() throws Exception {
		user = new User("Daniel", "dani", "local");
		user.setPoints(10);
		box = new Storage(filepath);

	}

	public void testsaveUser() {

		try {
			Storage box1 = new Storage(filepath + "dani");
		} catch (Exception e) {}
		
		//fails
		User testuser = new User("", "", "");
		box.save(testuser);

		assertTrue(box.save(user));

	}

	public void testloadUser() {

		assertTrue(box.save(user));

		User user1 = box.load(user);

        //fails
		User user2 = new User("Test", "tt", "test");
		user2 = box.load(user2);

		assertTrue(user.getName().equals(user1.getName()));
		assertTrue(user.getNickname().equals(user1.getNickname()));
		assertTrue(user.getType().equals(user1.getType()));
		assertTrue(user1.getPoints() == user.getPoints());

	}

}
