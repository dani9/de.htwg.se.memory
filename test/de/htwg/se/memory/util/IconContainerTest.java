package de.htwg.se.memory.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.swing.Icon;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

public class IconContainerTest {

	@Before
	public void preparation() {
		PropertyConfigurator.configure("log4j.properties");
		LogManager.getRootLogger().setLevel(Level.OFF);
		new Storage().delete(IconContainer.PICTUREPATH + "icons.if");
	}

	// de.kaba.qa.util
	@Test
	public void testIconContainer() {
		
		
		IconContainer container = IconContainer.getInstance();

		assertNotNull(container);

		assertTrue(container.getIcon("PIC"+IconContainer.ADD) instanceof Icon);
		
		
		
		IconContainer container_load = IconContainer.getInstance();
		
		
		assertTrue(container_load.getIcon("PIC"+IconContainer.ADD, 20,20) instanceof Icon);
		
		for (int i = 0; i < IconContainer.ADDITIONALICONS; i++) {
			
			assertTrue(container_load.getIcon("PIC"+i) instanceof Icon);
			
		}
		
		

	}
}
