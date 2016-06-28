package de.htwg.se.memory.model.playingField;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.memory.model.playingField.Field;
import junit.framework.TestCase;

public class FieldTest extends TestCase{
	
	Field field, field1, field2;
	String testobject;
	@Before
	public void setUp(){
		field = new Field("Hallo");
		field.setPathToPicture("test/dir");
		field1 = new Field("12");
		field1.setFieldId("12df");
		field2 = new Field("Hallo");
		testobject = new String("test");
		
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

	@Test
	public void testEquals(){
		assertEquals(field.compareTo(field2), 0);
		
		assertTrue(field.equals(field2));
		
		assertFalse(field.equals(field1));
		
		assertFalse(field.equals(testobject));
	}
	
	@Test
	public void testVisibleHidden(){
		field.setVisble();
		String stringVisible = field.toString();
				
		field.setHidden();
		String stringHidden= field.toString();
		assertNotEquals(stringHidden, stringVisible);
	}
	
	@Test
	public void testVisible(){
		field.setHidden();
		assertFalse(field.isVisible());
		field.setVisble();
		assertTrue(field.isVisible());
	}
	
	@Test
	public void testGuessed(){
		field.setGuessed(false);
		assertFalse(field.isGuessed());
		
		
		
		field.setGuessed(true);
		assertTrue(field.isGuessed());
		assertEquals(field.toString(), "");
	}
	
}
