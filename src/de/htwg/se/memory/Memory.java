package de.htwg.se.memory;

import de.htwg.se.memory.aview.gui.Gui;
import de.htwg.se.memory.aview.tui.Tui;
import de.htwg.se.memory.controller.Controller;
import de.htwg.se.memory.util.observer.IObserver.Topic;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
final class Memory {
	private static final Logger LOGGER = Logger.getLogger(Memory.class);
	
	private Memory() {
		
	}
 
	public static void main(String[] args) {
		
		PropertyConfigurator.configure("log4j.properties");
		
		Tui  tui = new Tui();
		Gui gui = new Gui();
		
		if(tui == null || gui == null){
			LOGGER.fatal("gui or tui = null");
		}
		Controller.getInstance().notifyObservers(Topic.GAME_INIT);
	}

}
