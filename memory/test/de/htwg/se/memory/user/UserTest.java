package de.htwg.se.memory.user;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
public class UserTest  extends TestCase{

		String test = "test";
		User user;
		@Before
		public void setUp() throws Exception{
			user = new User("Daniel", "dani", "local");
		}
		@Test
		public void testgetName(){
			
			assertTrue(user.getName().getClass().equals(test.getClass()));
		}
		@Test
		public void testgetNickname(){
			
			assertTrue(user.getNickname().getClass().equals(test.getClass()));
		}
		@Test
		public void testgetType(){
			
			assertTrue(user.getType().getClass().equals(test.getClass()));
		}
		
		@Test
		public void testgetPoints(){
			
			user.setPoints(1);
			assertEquals(user.getPoints() ,1);
		}
		@Test
		public void testsetPoints(){
			user.setPoints(5);
			assertEquals(user.getPoints() ,5);
		}

	

}
