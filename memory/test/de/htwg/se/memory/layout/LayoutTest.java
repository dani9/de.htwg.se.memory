
package de.htwg.se.memory.layout;
import junit.framework.TestCase;;
public class LayoutTest extends TestCase{
	
	Layout layout;
	Field field;
//	@Before
	public void setUp() throws Exception{
		layout = new Layout(6);
		field = new Field("FF");
	}
	
//	@Test
	public void testsetField(){
//		layout.setField(4,4, field);
		assertEquals(layout.getField(4, 4), layout.getField(4, 4));
		layout.getField(4, 4).setFieldId("Haus");
		assertEquals("Haus", layout.getField(4, 4).getFieldId());
		System.out.print(layout + "\n"+ "\n");
		layout.mix();
		System.out.print(layout);
	}


}
