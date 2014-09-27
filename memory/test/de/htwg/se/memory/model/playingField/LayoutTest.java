
package de.htwg.se.memory.model.playingField;
import org.junit.Before;
import org.junit.Test;

import de.htwg.se.memory.model.playingField.Field;
import de.htwg.se.memory.model.playingField.Layout;
import junit.framework.TestCase;
public class LayoutTest extends TestCase{
	
	Layout layout,layout1;
	Field field;
	@Before
	public void setUp() throws Exception{
		layout = new Layout(8);

	}
	
	@Test
	public void testIlligalCollum() throws Exception{
		try {
			layout1 = new Layout(7);
		} catch (IllegalArgumentException e) {
			// TODO: haimport org.junit.Before;ndle exception
		}
	}
	
	@Test
	public void testGetColumn(){
		
		assertEquals(8, layout.getColumn());
	}
	
	@Test
	public void testGetField(){
;
		assertEquals(layout.getField(4, 4), layout.getField(4, 4));
	}
	
	@Test
	public void testMix(){
		Layout tmp = layout;
		layout.mix();
		for(int x =0; x < layout.getColumn(); ++x){
			  for(int y =0; y < layout.getColumn(); ++y){
					assertNotSame(layout.toString(), tmp.toString());
			  	}
		  }
		
	}

}