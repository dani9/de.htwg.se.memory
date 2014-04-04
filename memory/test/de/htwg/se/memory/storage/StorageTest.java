package de.htwg.se.memory.storage;

import de.htwg.se.memory.user.User;
import junit.framework.TestCase;
import org.junit.Before;

public class StorageTest extends TestCase{

	String filepath = "/home/dorn/test.jr";
	User user;
	@Before
	public void setUp() throws Exception{
		user = new User("Daniel", "dani", "local");
		
	}
	
	public void testsaveUser(){
		
		assertTrue(Storage.saveUser(user, filepath));
		
		Storage.saveUser(user, "");
		
	}
	
	public void testloadUser(){
		
		user.setPoints(10);
		
		Storage stor = new Storage();
		assertTrue(Storage.saveUser(user, filepath));
	
		User user1 = Storage.loadUser(filepath);
		
		assertTrue(user.getName().equals(user1.getName()));
		assertTrue(user.getNickname().equals(user1.getNickname()));
		assertTrue(user.getType().equals(user1.getType()));
		assertTrue(user.getPoints() == user1.getPoints());
		
		Storage.saveUser(user, "");
		Storage.loadUser("");
		
	}
	
	
}
