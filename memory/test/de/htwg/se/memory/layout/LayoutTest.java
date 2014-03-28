
package de.htwg.se.memory.layout;
import junit.framework.TestCase;;
public class LayoutTest extends TestCase{
	
	Layout layout;
	Field field;
//	@Before
	public void setUp() throws Exception{
		layout = new Layout(5);
		field = new Field("FF");
	}
	
//	@Test
	public void testsetField(){
		layout.setField(4,4, field);
		assertEquals(field, layout.getField(4, 4));
	}

}
