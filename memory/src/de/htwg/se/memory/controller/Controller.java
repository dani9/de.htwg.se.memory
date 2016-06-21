package de.htwg.se.memory.controller;

import javax.swing.plaf.SliderUI;

import de.htwg.se.memory.model.player.*;
import de.htwg.se.memory.model.playingField.Field;
import de.htwg.se.memory.model.playingField.PlayingField;
import de.htwg.se.memory.util.observer.IObserver.Topic;
import de.htwg.se.memory.util.observer.Observable;


class Choice{
	static int row, column;
}

public class Controller extends Observable{

	PlayingField playingField = null;
	
	Player players[] = new Player[2];
	
	int activePlayer = 0;
	int turn = 0;

	
	public Controller(){
		notifyObservers(Topic.GAME_INIT);
	}
	
	public void startGame(int fieldSize, String player1Name, String player2Name){
		Player player1 = new User(player1Name, player1Name, this);
		Player player2 = new User(player2Name, player2Name, this);
		startGame(fieldSize, player1, player2);
	}
	
	public void startGame(int fieldSize, Player player1, Player player2){
		
		playingField = new PlayingField(fieldSize);
		players[0] = player1;
		players[1] = player2;
		activePlayer = 0;
		turn = 0;
		
		notifyObservers(Topic.NEW_GAME_STARTED);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getChoice();
	}
	
	public String getActivePlayerName(){
		return players[activePlayer].getName();
	}
	
	public int getActivePlayerPoints(){		
		return players[activePlayer].getPoints();
	}
	
	public String getPlayerName(int playerNumber){
		return players[playerNumber-1].getName();
	}
	
	public int getPlayerPoints(int playerNumber){
		return players[playerNumber-1].getPoints();
	}
	
	public void nextPlayer(){
		turn = 0;
		++activePlayer;
		activePlayer %= 2;
		playingField.hideAll();
		notifyObservers(Topic.NEXT_PLAYER);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getChoice();
	}
	
	public int getPlayFieldSize(){
		
		return playingField.getColumn();
	}
	
	public void getChoice(){
		players[activePlayer].getChoice();
	}
	
	public int getTurn(){
		return turn;
	}
	
	public Field getField(int row, int column){
		Field field = playingField.getField(row, column);	
		return field;
	}
	
	
	public void waitForChoice(){
		notifyObservers(Topic.WAIT_FOR_CHOICE);
	}
	
	public void setChoice(int row, int column){
		
		Field field = getField(row, column);
		if(field.isVisible() || field.isGuessed()){
			notifyObservers(Topic.WAIT_FOR_CHOICE);
			return;
		}
		field.setVisble();
		
		if(turn == 1){
			if(field.compareTo(getField(Choice.row, Choice.column)) == 0){
				field.setGuessed(true);
				getField(Choice.row, Choice.column).setGuessed(true);
				players[activePlayer].addPoint();
				
				if(playingField.isAllGeuessed() == true){
					notifyObservers(Topic.GAME_FINISHED);
					return;
				}
			}
		}
		
		
		
		Choice.row = row;
		Choice.column = column;
		notifyObservers(Topic.CHOICE_WAS_MADE);
		

		
		if(turn == 0){
			++turn;
			getChoice();
			
		}else{
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			nextPlayer();
		}
	}
}
