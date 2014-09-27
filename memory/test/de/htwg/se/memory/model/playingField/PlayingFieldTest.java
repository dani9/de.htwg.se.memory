
package de.htwg.se.memory.model.playingField;
import org.junit.Before;
import org.junit.Test;

import de.htwg.se.memory.model.playingField.Field;
import de.htwg.se.memory.model.playingField.PlayingField;
import junit.framework.TestCase;
public class PlayingFieldTest extends TestCase{
	
	PlayingField layout,layout1;
	Field field;
	@Before
	public void setUp() throws Exception{
		layout = new PlayingField(8);
		layout1 = new PlayingField(5);
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
		for(int x =0; x < layout.getColumn(); ++x){
			  for(int y =0; y < layout.getColumn(); ++y){
					assertNotSame(layout.toString(), tmp.toString());
			  	}
		  }
		
	}

}
