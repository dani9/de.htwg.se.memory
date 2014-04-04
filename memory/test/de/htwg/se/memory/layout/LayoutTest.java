
package de.htwg.se.memory.layout;
import junit.framework.TestCase;
public class LayoutTest extends TestCase{
	
	Layout layout;
	Field field;
//	@Before
	public void setUp() throws Exception{
		layout = new Layout(6);
	}

	public void testGetColumn(){
		
		assertEquals(6, layout.getColumn());
	}
	
//	@Test
	public void testGetField(){
;
		assertEquals(layout.getField(4, 4), layout.getField(4, 4));
	}

	public void testMix(){
		System.out.println(layout);
		Layout tmp = layout;
		layout.mix();
		for(int x =0; x < layout.getColumn(); ++x){
			  for(int y =0; y < layout.getColumn(); ++y){
					assertNotSame(layout.toString(), tmp.toString());
			  	}
		  }
		
	}

}
