package de.htwg.se.memory.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.swing.Icon;

import org.junit.Before;
import org.junit.Test;

public class IconContainerTest {

	@Before
	public void preparation() {

	}

	// de.kaba.qa.util
	@Test
	public void testIconContainer() {
		
		
		IconContainer container = IconContainer.getInstance();
		System.out.println(container);

		assertNotNull(container);

		assertTrue(container.getIcon("PIC"+IconContainer.ADD) instanceof Icon);
		assertTrue(container.getIcon("PIC"+IconContainer.ADD, 20,20) instanceof Icon);
		
		for (int i = 0; i < IconContainer.ADDITIONALICONS; i++) {
			
			assertTrue(container.getIcon("PIC"+i) instanceof Icon);
			
		}
		
		

	}
}
