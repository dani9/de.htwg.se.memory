package de.htwg.se.memory.controller;

import de.htwg.se.memory.model.player.*;
import de.htwg.se.memory.model.playingfield.Field;
import de.htwg.se.memory.model.playingfield.PlayingField;
import de.htwg.se.memory.util.observer.IObserver.Topic;
import de.htwg.se.memory.util.observer.Observable;

class Choice {
	static int row, column;
	
	private Choice() {

	}

	
}

public class Controller extends Observable {

	PlayingField playingField = null;

	Player[] players = new Player[2];

	int activePlayer = 0;
	int turn = 0;

	public Controller() {

	}

	public void startGame(int fieldSize, String player1Name, String player2Name) {
		Player player1 = new User(player1Name, player1Name);
		Player player2 = new User(player2Name, player2Name);
		startGame(fieldSize, player1, player2);
	}

	public void startGame(int fieldSize, Player player1, Player player2) {

		playingField = new PlayingField(fieldSize);
		playingField.mix();
		players[0] = player1;
		players[1] = player2;
		activePlayer = 0;
		turn = 0;

		notifyObservers(Topic.NEW_GAME_STARTED);

		getChoice();
	}

	public String getActivePlayerName() {
		return players[activePlayer].getName();
	}

	public int getActivePlayerPoints() {
		return players[activePlayer].getPoints();
	}

	public String getPlayerName(int playerNumber) {
		return players[playerNumber - 1].getName();
	}

	public int getPlayerPoints(int playerNumber) {
		return players[playerNumber - 1].getPoints();
	}

	public Player[] getPlayers() {

		return players;
	}

	public void nextPlayer() {
		turn = 0;
		++activePlayer;
		activePlayer %= 2;
		playingField.hideAll();
		notifyObservers(Topic.NEXT_PLAYER);

		getChoice();
	}

	public int getPlayFieldSize() {

		return playingField.getColumn();
	}

	public void getChoice() {
		waitForChoice();
	}

	public int getTurn() {
		return turn;
	}

	public Field getField(int row, int column) {
		return playingField.getField(row, column);
	}

	public PlayingField getPlayingField() {
		return playingField;
	}

	public void waitForChoice() {
		notifyObservers(Topic.WAIT_FOR_CHOICE);
	}

	public void setChoice(int row, int column) {

		Field field = getField(row, column);
		if (field.isVisible() || field.isGuessed()) {
			notifyObservers(Topic.WAIT_FOR_CHOICE);
			return;
		}
		field.setVisble();

		if (turn == 1 && field.compareTo(getField(Choice.row, Choice.column)) == 0) {
			field.setGuessed(true);
			getField(Choice.row, Choice.column).setGuessed(true);
			players[activePlayer].addPoint();

			if (playingField.isAllGeuessed()) {
				notifyObservers(Topic.GAME_FINISHED);
				return;
			} else {
				turn = 0;
				notifyObservers(Topic.WAIT_FOR_CHOICE);
				return;
			}
		}

		Choice.row = row;
		Choice.column = column;
		notifyObservers(Topic.CHOICE_WAS_MADE);

		if (turn == 0) {
			++turn;
			getChoice();

		} else {
			notifyObservers(Topic.WAIT_FOR_NEXT_PLAYER);

		}
	}
}
