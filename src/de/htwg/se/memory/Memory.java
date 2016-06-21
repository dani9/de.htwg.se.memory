package de.htwg.se.memory;

import de.htwg.se.memory.aview.gui.Gui;
import de.htwg.se.memory.aview.tui.Tui;
import de.htwg.se.memory.controller.Controller;
import de.htwg.se.memory.util.observer.IObserver.Topic;

final class Memory {
 
	public static void main(String[] args) {
		Controller controller = new Controller();
		Tui tui = new Tui(controller);
		Gui gui = new Gui(controller);
		controller.addObserver(tui);
		controller.addObserver(gui);
		controller.notifyObservers(Topic.GAME_INIT);
	}

}
