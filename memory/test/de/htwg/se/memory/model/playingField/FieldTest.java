package de.htwg.se.memory.model.playingField;
import org.junit.Before;
import org.junit.Test;

import de.htwg.se.memory.model.playingField.Field;
import junit.framework.TestCase;

public class FieldTest extends TestCase{
	
	Field field, field1;
	@Before
	public void setUp() throws Exception{
		field = new Field("Hallo");
		field.setPathToPicture("test/dir");
		field1 = new Field("12");
		field1.setFieldId("12df");
	}

	@Test
	public void testKonstruktor(){
		
		assertEquals("Hallo", field.getFieldId());
		
	}
	
	@Test
	public void testsetFieldId(){
		
		assertEquals("12df", field1.getFieldId());
		
	}

	@Test
	public void testPathToPicture(){
		assertEquals("test/dir" ,field.getPathToPicture());
	}

}