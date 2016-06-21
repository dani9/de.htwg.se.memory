package de.htwg.se.memory.aview.gui;
import javax.swing.JButton;

import de.htwg.se.memory.model.playingField.Field;

public class JCard extends JButton {
	
	
	private static final long serialVersionUID = -585620824813026570L;
	private Field id;
	
	
	public JCard(Field id) {
		this.id = id;
	}
	
	public Field getField() {
		return id;
	}
	

}
