package de.htwg.se.model.ai;
import static org.junit.Assert.assertEquals;
import de.htwg.se.memory.model.player.Ai;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.memory.controller.Controller;

import de.htwg.se.memory.model.playingField.Field;
import junit.framework.TestCase;
public class AiTest {
	
	Ai ai;
	@Before
	public void setUp(){
		Controller controller = new Controller();
		ai = new Ai(controller, 1);
	}
	
	@Test
	public void testSetDifficult(){
		int difficult = 2;
		ai.setDifficultLevel(difficult);
		assertEquals(ai.getDifficultLevel(), difficult);
		
	}
	
	@Test
	public void testRemember(){
		ai.setDifficultLevel(3);
		Field field = new Field("12");
		ai.remember(field ,2, 3);
		ai.setDifficultLevel(2);
		Field field1 = new Field("13");
		ai.remember(field1 ,2, 3);
		ai.setDifficultLevel(1);
		Field field2 = new Field("14");
		ai.remember(field2 ,2, 3);
		ai.remember(field1 ,2, 3);
		ai.remember(field2 ,3, 3);
		ai.remember(field2 ,3, 4);
		
		Field field3 = new Field("15");
		Field field4 = new Field("16");
		ai.remember(field3 ,1, 1);
		ai.remember(field4 ,1, 2);

	}
	
	
}
