package de.htwg.se.memory.storage;

import de.htwg.se.memory.user.User;
import junit.framework.TestCase;
import org.junit.Before;

public class StorageTest extends TestCase{

	String filepath1 = "/home/dorn/hjkhsdkf/";
	String filepath = "/home/dorn/";
	User user;
	Storage box,box1;
	@Before
	public void setUp() throws Exception{
		user = new User("Daniel", "dani", "local");
		user.setPoints(10);
		box = new Storage(filepath);
		//box1 = new Storage(filepath1);
		
	}
	
	public void testsaveUser(){
		
		assertTrue(box.save(user));
		
	}
	
	
	public void testloadUser(){
	
		assertTrue(box.save(user));
	
		User user1 = box.load(user);
		
		User user2 = new User("Test", "tt", "test");
		user2 = box.load(user2);
		
		
		assertTrue(user.getName().equals(user1.getName()));
		assertTrue(user.getNickname().equals(user1.getNickname()));
		assertTrue(user.getType().equals(user1.getType()));
		//assertTrue(user.getPoints() == user1.getPoints());
		
//		box.save();
//		box.load();
//		
	}
	
	
}
