package de.htwg.se.memory.controller;

import de.htwg.se.memory.model.ai.Ai;
import de.htwg.se.memory.model.playingField.Field;
import de.htwg.se.memory.model.playingField.PlayingField;
import de.htwg.se.memory.util.observer.Observable;

public class Controller extends Observable{

	PlayingField playingField = null;
	Ai ai = null;
	
	public Controller(){
		
	}
	
	public void startGame(int fieldSize, boolean withAi){
		
		playingField = new PlayingField(fieldSize);
		ai = new Ai(this, "Hard");
	}
	
	public int getPlayFieldSize(){
		
		return playingField.getColumn();
	}
	
	public Field getField(int row, int column){
		Field field = playingField.getField(row, column);
		if(ai != null){
			ai.remember(field, row, column);
		}
		
		
		return field;
	}
	
	public int[] getAiChoice(){
		if(ai == null){
			return null;
		}
		return ai.getChoice();
	}
}
