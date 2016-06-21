package de.htwg.se.memory.aview.tui;

import java.util.Scanner;

import de.htwg.se.memory.controller.Controller;
import de.htwg.se.memory.util.observer.IObserver;

public class Tui extends Thread implements IObserver {

	Controller controller;

	public Tui(Controller controller) {
		this.controller = controller;
		this.start();
	}

	Topic state;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// super.run();
		readInput();
	}

	@Override
	public void update(Topic topic) {

		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		state = topic;
		// TODO Auto-generated method stub

		switch (state) {
		case CHOICE_WAS_MADE:
			printActivePlayerStats();
			printPlayingField();
			break;
		case NEW_GAME_STARTED:
			printActivePlayerStats();
			printPlayingField();
			break;
		case NEXT_PLAYER:
			printActivePlayerStats();
			printPlayingField();
			break;

		case WAIT_FOR_CHOICE:
			printActivePlayerStats();
			printPlayingField();
			System.out.println("chose row an collum z.B.\"3 2\" ");
			break;

		case GAME_FINISHED:

			System.out.println("GAME FINISHED");
			printPlayerStats(1);
			printPlayerStats(2);

		case GAME_INIT:
			System.out.println("Enter two usernames and a field size %s %s %i");

			break;

		default:
			break;
		}

	}

	private void printActivePlayerStats() {
		System.out.printf("%-10sPoints: %d\n", controller.getActivePlayerName(), controller.getActivePlayerPoints());
	}

	private void printPlayerStats(int playerNumber) {
		System.out.printf("%-10sPoints: %d\n", controller.getPlayerName(playerNumber),
				controller.getPlayerPoints(playerNumber));
	}

	private void printPlayingField() {
		int fieldSize = controller.getPlayFieldSize();
		System.out.printf("%-4s", "");
		for (int i = 0; i < fieldSize; ++i) {
			System.out.printf("%-4d", i);
		}
		System.out.println("");
		for (int i = 0; i < fieldSize; ++i) {
			System.out.printf("%-4d", i);
			for (int j = 0; j < fieldSize; ++j) {
				System.out.printf("%-4s", controller.getField(i, j));
			}

			System.out.println("");
		}
	}

	private void readInput() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		while (true) {
			String readed = scanner.next();

			switch (state) {
			case WAIT_FOR_CHOICE:
				int row = Integer.parseInt(readed);
				int column = Integer.parseInt(scanner.next());

				controller.setChoice(row, column);
				break;

			case CHOICE_WAS_MADE:

				break;

			case NEW_GAME_STARTED:

				break;

			case GAME_FINISHED:

			case GAME_INIT:
				String player1Name = readed;
				String player2Name = scanner.next();
				int fieldSize = Integer.parseInt(scanner.next());

				controller.startGame(fieldSize, player1Name, player2Name);
				break;
			default:
				break;
			}
		}
		// scanner.close();
	}
}
