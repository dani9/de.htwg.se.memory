package de.htwg.se.memory.layout;
import junit.framework.TestCase;

public class FieldTest extends TestCase{
	
	Field field;
//	@Before
	public void setUp() throws Exception{
		field = new Field("Hallo");
	}
	
	public void testsetField(){
		
		assertEquals("Hallo", field.getFieldId());
	}

}
