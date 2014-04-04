package de.htwg.se.memory.layout;
import junit.framework.TestCase;

public class FieldTest extends TestCase{
	
	Field field;
//	@Before
	public void setUp() throws Exception{
		field = new Field("Hallo");
		field.setPathToPicture("test/dir");
	}
	
	public void testsetFieldId(){
		
		assertEquals("Hallo", field.getFieldId());
		
	}
	public void testPathToPicture(){
		assertEquals("test/dir" ,field.getPathToPicture());
	}

}
