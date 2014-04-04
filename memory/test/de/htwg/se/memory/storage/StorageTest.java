package de.htwg.se.memory.storage;

import de.htwg.se.memory.user.User;
import junit.framework.TestCase;
import org.junit.Before;

public class StorageTest extends TestCase{

	String filepath = "/home/dorn/";
	User user;
	@Before
	public void setUp() throws Exception{
		user = new User("Daniel", "dani", "local");
	}
	
	public void testsaveUser(){
		
		Storage.saveUser(user, filepath);
		assertTrue(Storage.saveUser(user, filepath));
		
	}
	
	public void testloadUser(){
		
		Storage.saveUser(user, filepath);
		assertTrue(Storage.saveUser(user, filepath));
		
		User user1 = Storage.loadUser(filepath);
		
		
		
		
		
		
	}
	
	
}
