package de.htwg.se.memory.layout;

public class Layout {
	public Layout(int column) {
		creatMatrix(column);
	}
	
	private Field matrix[][];
	
	
	public void creatMatrix(int column){
		matrix = new Field[column][column];
	}
	
	public Field getField(int row,int column) {
		return matrix[row][column];
	}
	
	public void setField(int row ,int column, Field field) {
		this.matrix[row][column] = field;
	}
		

}
