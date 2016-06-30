package de.htwg.se.memory.controller.impl;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.se.memory.controller.IController;
import de.htwg.se.memory.model.player.*;
import de.htwg.se.memory.model.playingfield.IField;
import de.htwg.se.memory.model.playingfield.IPlayingField;
import de.htwg.se.memory.model.playingfield.impl.PlayingField;
import de.htwg.se.memory.util.observer.IObserver.Topic;
import de.htwg.se.memory.util.observer.impl.Observable;



public class Controller extends Observable implements IController {

	private static class Choice {
		static int row, column;
		private Choice(){
			
		}
	}
	private static final class InstanceHolder {
		static final Controller CONTROLLER = new Controller();
		private InstanceHolder(){
			
		}
	}
	
	private final static Injector INJECTOR = Guice.createInjector();
	private IPlayingField playingField = null;

	IPlayer[] players = new IPlayer[2];

	int activePlayer = 0;
	int turn = 0;

	private Controller() {

	}
	
	public void setPlayingfield(IPlayingField playingField){
		this.playingField =playingField;
	}
	
	public static Controller getInstance(){
		return InstanceHolder.CONTROLLER;
	}

	@Override
	public void startGame(int fieldSize, String player1Name, String player2Name) {
		IPlayer player1 = INJECTOR.getInstance(IPlayer.class);
		player1.setName(player1Name);
		IPlayer player2 = INJECTOR.getInstance(IPlayer.class);
		player2.setName(player2Name);
		startGame(fieldSize, player1, player2);
	}

	@Override
	public void startGame(int fieldSize, IPlayer player1, IPlayer player2) {

		playingField = new PlayingField(fieldSize);
		playingField.mix();
		players[0] = player1;
		players[1] = player2;
		activePlayer = 0;
		turn = 0;

		notifyObservers(Topic.NEW_GAME_STARTED);

		getChoice();
	}

	@Override
	public String getActivePlayerName() {
		return players[activePlayer].getName();
	}
	
	@Override
	public String getInactivePlayerName() {
		return players[(activePlayer+1)%2].getName();
	}

	@Override
	public int getActivePlayerPoints() {
		return players[activePlayer].getPoints();
	}

	@Override
	public String getPlayerName(int playerNumber) {
		return players[playerNumber - 1].getName();
	}

	@Override
	public int getPlayerPoints(int playerNumber) {
		return players[playerNumber - 1].getPoints();
	}

	@Override
	public IPlayer[] getPlayers() {

		return players;
	}

	@Override
	public void nextPlayer() {
		turn = 0;
		++activePlayer;
		activePlayer %= 2;
		playingField.hideAll();
		notifyObservers(Topic.NEXT_PLAYER);

		getChoice();
	}

	@Override
	public int getPlayFieldSize() {

		return playingField.getColumn();
	}

	@Override
	public void getChoice() {
		waitForChoice();
	}

	@Override
	public int getTurn() {
		return turn;
	}

	@Override
	public IField getField(int row, int column) {
		return playingField.getField(row, column);
	}

	@Override
	public IPlayingField getPlayingField() {
		return playingField;
	}

	@Override
	public void waitForChoice() {
		notifyObservers(Topic.WAIT_FOR_CHOICE);
	}

	@Override
	public void setChoice(int row, int column) {

		IField field = getField(row, column);
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
