package de.htwg.se.memory.layout;

import java.util.LinkedList;

public class Layout {
	public Layout(int column) {
		creatMatrix(column);
	}
	private Field matrix[][];
	
	
	public void creatMatrix(int column){
		matrix = new Field[column][column];
		fillMatrix();
		}
		
	private void fillMatrix(){
		LinkedList<Field> list=new LinkedList<Field>();
			
		for (int i = 0; i < ((matrix.length*matrix.length)/2); i++) {
			Field tmp= new Field(Integer.toString(i));
			list.add(tmp);
			list.add(tmp);
			System.out.println(list.size());
		}
			
			
		for(int x =0; x < matrix.length; ++x){
			  for(int y =0; y < matrix.length; ++y){
					matrix[x][y] =list.getFirst();
					list.removeFirst();
			  	}
		  }
		System.out.println(matrix.length);
	}
	
	public Field getField(int row,int column) {
		return matrix[row][column];
	}
	
	public void mix(){
				  int randX , randY;
				  Field tmp;
				  for(int x =0; x < matrix.length; ++x){
					  for(int y =0; y < matrix.length; ++y){
				  
						  randX = (int)(Math.floor(Math.random() * matrix.length));
						  randY = (int)(Math.floor(Math.random() * matrix.length));
						  tmp = matrix[x][y]; 
						  matrix[x][y] = matrix[randX][randY]; 
						  matrix[randX][randY] =tmp;
					  	}
				  }
		}
	
	 @Override
	public String toString() {
		StringBuilder matrixString = new StringBuilder();
		  for(int x =0; x < matrix.length; ++x){
			  for(int y =0; y < matrix.length; ++y){
		  
				  	matrixString.append(matrix[x][y].getFieldId());
				  	matrixString.append("\t");
			  	}
			  	if(x < matrix.length-1){
			  	matrixString.append("\n");
			  	}
		  	}
		  return matrixString.toString();
	 	}
}
