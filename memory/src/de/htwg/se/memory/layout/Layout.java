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
			
		for (int i = 0; i < ((getColumn()*getColumn())/2); i++) {
			Field tmp= new Field(Integer.toString(i));
			list.add(tmp);
			list.add(tmp);
		}
			
			
		for(int x =0; x < getColumn(); ++x){
			  for(int y =0; y < getColumn(); ++y){
					matrix[x][y] =list.getFirst();
					list.removeFirst();
			  	}
		  }
	}
	
	public Field getField(int row,int column) {
		return matrix[row][column];
	}
	
	public void mix(){
				  int randX , randY;
				  Field tmp;
				  for(int x =0; x < getColumn(); ++x){
					  for(int y =0; y < getColumn(); ++y){
				  
						  randX = (int)(Math.floor(Math.random() * getColumn()));
						  randY = (int)(Math.floor(Math.random() * getColumn()));
						  tmp = matrix[x][y]; 
						  matrix[x][y] = matrix[randX][randY]; 
						  matrix[randX][randY] =tmp;
					  	}
				  }
		}

	public int getColumn(){
		return matrix.length;
	}
	
	 @Override
	public String toString() {
		StringBuilder matrixString = new StringBuilder();
		  for(int x =0; x < getColumn(); ++x){
			  for(int y =0; y < getColumn(); ++y){
		  
				  	matrixString.append(matrix[x][y].getFieldId());
				  	matrixString.append("\t");
			  	}
			  	if(x < (getColumn()-1)){
			  	matrixString.append("\n");
			  	}
		  	}
		  return matrixString.toString();
	 	}
}
