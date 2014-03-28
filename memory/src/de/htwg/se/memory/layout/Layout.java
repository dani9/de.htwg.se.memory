package de.htwg.se.memory.layout;

public class Layout {
	public Layout(int columns) {
	makeLayout(columns);
	}	
	private Field matrix[][] = new Field[5][5];
	
	public void makeLayout(int columns){
	matrix[0][0] = new Field("Feld_0_0");
	matrix[1][0] = new Field("Feld_1_0");	
	}
	
	
	
	
public static void main(String[] args) {
	Layout test=new Layout(2);
	System.out.println(test.matrix);
}

}
