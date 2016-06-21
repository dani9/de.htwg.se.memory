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
		case NEXT_PLAYER:
		case NEW_GAME_STARTED:
			printActivePlayerStats();
			printPlayingField();
			break;
		case WAIT_FOR_NEXT_PLAYER:
			printActivePlayerStats();
			printPlayingField();
			System.out.println(controller.getActivePlayerName() + " press Enter to begin.");
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
			String readed = "";
			try {
				readed = scanner.nextLine();
			} catch (Exception e) {
				break;
			}


			switch (state) {
			case WAIT_FOR_CHOICE:
				if (readed.trim().split(" ").length == 2) {
					int row = Integer.parseInt(readed.trim().split(" ")[0]);
					int column = Integer.parseInt(readed.trim().split(" ")[1]);

					controller.setChoice(row, column);
				}
				break;

			case CHOICE_WAS_MADE:

				break;

			case NEW_GAME_STARTED:

				break;
			case WAIT_FOR_NEXT_PLAYER:
				controller.nextPlayer();
				break;
			case GAME_FINISHED:

			case GAME_INIT:

				if (readed.trim().split(" ").length == 3) {
					String player1Name = readed.trim().split(" ")[0];
					String player2Name = readed.trim().split(" ")[1];
					int fieldSize = Integer.parseInt(readed.trim().split(" ")[2]);

					controller.startGame(fieldSize, player1Name, player2Name);
				}
				break;
			default:
				break;
			}
		}
		// scanner.close();
	}

	
	
}
