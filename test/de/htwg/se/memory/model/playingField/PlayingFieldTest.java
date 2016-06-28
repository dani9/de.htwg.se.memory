
package de.htwg.se.memory.model.playingField;
import org.junit.Before;
import org.junit.Test;

import de.htwg.se.memory.model.playingField.Field;
import de.htwg.se.memory.model.playingField.PlayingField;
import junit.framework.TestCase;
public class PlayingFieldTest extends TestCase{
	
	PlayingField layout,layout1, layout2;
	Field field;
	@Before
	public void setUp() throws Exception{
		layout = new PlayingField(8);
		layout1 = new PlayingField(5);
		layout2 = new PlayingField(2);
	}
	
	
	@Test
	public void testGetColumn(){
		
		assertEquals(8, layout.getColumn());
	}
	
	@Test
	public void testGetColumnWhenValueisNotAEvenNumber(){

		assertEquals(layout1.getColumn() % 2, 0);
	}
	
	@Test
	public void testGetField(){
;
		assertEquals(layout.getField(4, 4), layout.getField(4, 4));
	}
	
	@Test
	public void testMix(){
		PlayingField tmp = layout;
		layout.mix();

		assertNotSame(layout.toString(), tmp.toString());
			 
		  
		
	}
	
	@Test
	public void testHideAll(){
		layout.hideAll();
		for(int x =0; x < layout.getColumn(); ++x){
			  for(int y =0; y < layout.getColumn(); ++y){
					assertFalse(layout.getField(x, y).isVisible());
			  	}
		  }
		
	}
	
	@Test
	public void testisAllGeuessed(){
		assertFalse(layout2.isAllGeuessed());
		
		for (int i = 0; i < layout2.getColumn(); i++) {
			for (int j = 0; j < layout2.getColumn(); j++) {
			
				layout2.getField(i, j).setGuessed(true);
			}	
		}
		
		assertTrue(layout2.isAllGeuessed());
		
	}
	
	

}
