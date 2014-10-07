package de.htwg.se.model.ai;
import org.junit.Before;
import org.junit.Test;

import de.htwg.se.memory.controller.Controller;
import de.htwg.se.memory.model.ai.Ai;
import junit.framework.TestCase;
public class AiTest {
	
	Ai ai;
	@Before
	public void setUp(){
		Controller controller = new Controller();
		ai = new Ai(controller, "hard");
	}
	
	@Test
	public void testSetDifficult(){
		
		
	}
	
}
