package de.htwg.se.memory.layout;

import java.util.LinkedList;

public class Layout {
	private final Field matrix[][];
	
	public Layout(int column) throws Exception {
		if(column % 2 == 0){
			matrix = new Field[column][column];
			fillMatrix();
		}
		else{
			throw(new IllegalArgumentException("only even numbers"));
		}
		
		
	}


		
	private final void fillMatrix(){
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
	
	public final Field getField(int row,int column) {
		return matrix[row][column];
	}
	
	public final void mix(){
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

	public final int getColumn(){
		return matrix.length;
	}
	
	 @Override
	public final String toString() {
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
