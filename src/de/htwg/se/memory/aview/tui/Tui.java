package de.htwg.se.memory.aview.tui;

import java.util.Scanner;
import de.htwg.se.memory.controller.Controller;
import de.htwg.se.memory.util.observer.IObserver;

import org.apache.log4j.Logger;
public class Tui extends Thread implements IObserver {

	private static final Logger LOGGER = Logger.getLogger("de.htwg.se.memory.aview.tui");
	
	private Controller controller;
	
	private Topic state;
	public Tui() {
		this.controller = Controller.getInstance();
		this.controller.addObserver(this);
		this.start();
	}

	@Override
	public void run() {
		readInput();
	}

	@Override
	public void update(Topic topic) {

		LOGGER.info(String.format("%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n"));
		state = topic;

		switch (state) {

		case CHOICE_WAS_MADE:
		case NEXT_PLAYER:
		case NEW_GAME_STARTED:
			updateNewGameStartet();
			break;
		case WAIT_FOR_NEXT_PLAYER:
			updateWaitForNextPlayer();
			break;

		case WAIT_FOR_CHOICE:
			updateWaitForChoice();
			break;

		case GAME_FINISHED:

			updateGameFinished();
			break;

		case GAME_INIT:
			LOGGER.info("Enter two usernames and a field size %s %s %i");

			break;

		default:
			break;
		}

	}

	private void updateGameFinished() {
		LOGGER.info("GAME FINISHED");
		printPlayerStats(1);
		printPlayerStats(2);
		LOGGER.info("Enter two usernames and a field size %s %s %i");
	}

	private void updateWaitForChoice() {
		updateNewGameStartet();
		LOGGER.info("chose row an collum z.B.\"3 2\" ");
	}

	private void updateWaitForNextPlayer() {
		updateNewGameStartet();
		LOGGER.info(controller.getActivePlayerName() + " press Enter to begin.");
	}

	private void updateNewGameStartet() {
		printActivePlayerStats();
		printPlayingField();
	}

	private void printActivePlayerStats() {
		String output = "";
		output += String.format("%-10sPoints: %d", controller.getActivePlayerName(), controller.getActivePlayerPoints());
		LOGGER.info(output);
	}

	private void printPlayerStats(int playerNumber) {
		String output = "";
		
		output += String.format("%-10sPoints: %d", controller.getPlayerName(playerNumber),
				controller.getPlayerPoints(playerNumber));
		
		LOGGER.info(output);
	}

	private void printPlayingField() {
		int fieldSize = controller.getPlayFieldSize();
		String output = String.format("%n");
		output += String.format("%-4s", "");
		for (int i = 0; i < fieldSize; ++i) {
			output += String.format("%-4d", i);
		}
		output += String.format("%n");
		for (int i = 0; i < fieldSize; ++i) {
			output += String.format("%-4d", i);
			for (int j = 0; j < fieldSize; ++j) {
				output += String.format("%-4s", controller.getField(i, j));
			}

			output += String.format("%n");
		}
		LOGGER.info(output);
	}

	private void readInput() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		while (true) {
			String readed = "";
			
			readed = scanner.nextLine();
			


			switch (state) {
			case WAIT_FOR_CHOICE:
				readWaitForChoice(readed);
				break;

			case CHOICE_WAS_MADE:

				break;

			case NEW_GAME_STARTED:

				break;
			case WAIT_FOR_NEXT_PLAYER:
				controller.nextPlayer();
				break;
			case GAME_FINISHED:
				readGameInit(readed);
				break;
			case GAME_INIT:
				readGameInit(readed);
				break;
			default:
				break;
			}
		}
	}

	private void readGameInit(String readed) {
		if (readed.trim().split(" ").length == 3) {
			String player1Name = readed.trim().split(" ")[0];
			String player2Name = readed.trim().split(" ")[1];
			int fieldSize = Integer.parseInt(readed.trim().split(" ")[2]);

			controller.startGame(fieldSize, player1Name, player2Name);
		}
	}

	private void readWaitForChoice(String readed) {
		if (readed.trim().split(" ").length == 2) {
			int row = Integer.parseInt(readed.trim().split(" ")[0]);
			int column = Integer.parseInt(readed.trim().split(" ")[1]);

			controller.setChoice(row, column);
		}
	}

	
	
}
