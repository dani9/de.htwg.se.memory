package de.htwg.se.memory.layout;

public class Layout {
	public Layout(int column) {
		matrix = new Field[column-1][column-1];
	}
	
	private Field matrix[][];
	
	public Field getFeld(int row,int column) {
		return matrix[row][column];
	}
	
	public void setFeld(int row ,int column, Field field) {
		this.matrix[row][column] = field;
	}
		
	


}
