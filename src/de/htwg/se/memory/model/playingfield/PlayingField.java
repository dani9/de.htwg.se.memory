package de.htwg.se.memory.model.playingfield;

import java.util.LinkedList;
import java.util.List;

public class PlayingField {
	private Field matrix[][];
	
	public PlayingField(int column){
		if(column % 2 == 0){
			matrix = new Field[column][column];
			
		}
		else{
			matrix = new Field[column+1][column+1];
		}
		fillMatrix();
		
	}


		
	private void fillMatrix(){
		List<Field> list=new LinkedList<Field>();
			
		for (int i = 0; i < ((getColumn()*getColumn())/2); i++) {
			Field tmp= new Field(Integer.toString(i));
			Field tmp2= new Field(Integer.toString(i));
			list.add(tmp);
			list.add(tmp2);
		}
			
			
		for(int x =0; x < getColumn(); ++x){
			  for(int y =0; y < getColumn(); ++y){
					matrix[x][y] =list.get(0);
					list.remove(0);
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
	 
	 public void hideAll(){
		 for(int x =0; x < getColumn(); ++x){
			  for(int y =0; y < getColumn(); ++y){
		  
				  getField(x, y).setHidden();
			  	}
		 }
	 
	 }
	 
	 public boolean isAllGeuessed(){		 
		 for(int x =0; x < getColumn(); ++x){
			  for(int y =0; y < getColumn(); ++y){
				  
				  if(!getField(x, y).isGuessed()){
					  return false;
				  }
			  	}
		 }
		 
		 return true;
	 }
}
