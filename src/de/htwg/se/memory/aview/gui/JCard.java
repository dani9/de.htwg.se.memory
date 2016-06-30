package de.htwg.se.memory.aview.gui;
import javax.swing.JButton;

import de.htwg.se.memory.model.playingfield.Field;

public class JCard extends JButton {
	
	
	private static final long serialVersionUID = -585620824813026570L;
	private Field field;
	private int row, column;
	
	
	public JCard(Field id, int row, int column) {
		this.field = id;
		this.row = row;
		this.column = column;
		
		
	}
	
	
	public int getRow() {
		return row;
	}


	public int getColumn() {
		return column;
	}


	public Field getField() {
		return field;
	}
	

}
