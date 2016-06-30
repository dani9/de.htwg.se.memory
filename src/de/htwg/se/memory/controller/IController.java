package de.htwg.se.memory.controller;

import de.htwg.se.memory.model.player.IPlayer;
import de.htwg.se.memory.model.playingfield.IField;
import de.htwg.se.memory.model.playingfield.IPlayingField;
import de.htwg.se.memory.util.observer.IObservable;

public interface IController extends IObservable {

	void startGame(int fieldSize, String player1Name, String player2Name);

	void startGame(int fieldSize, IPlayer player1, IPlayer player2);

	String getActivePlayerName();

	int getActivePlayerPoints();

	String getPlayerName(int playerNumber);

	int getPlayerPoints(int playerNumber);

	IPlayer[] getPlayers();

	void nextPlayer();

	int getPlayFieldSize();

	void getChoice();

	int getTurn();

	IField getField(int row, int column);

	IPlayingField getPlayingField();

	void waitForChoice();

	void setChoice(int row, int column);

	String getInactivePlayerName();

}