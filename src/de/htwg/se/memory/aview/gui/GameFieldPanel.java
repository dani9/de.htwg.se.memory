package de.htwg.se.memory.aview.gui;



import javax.swing.JPanel;

import de.htwg.se.memory.controller.IController;
import de.htwg.se.memory.util.observer.IObserver.Topic;

public class GameFieldPanel extends JPanel{


	private static final long serialVersionUID = 7050391421149574318L;
	private Grid grid;
	
	
	public GameFieldPanel(IController controller,Topic topic, int cardCount, int preferredSize) {
		
		grid = new Grid(controller, topic , cardCount,preferredSize);
		
		this.add(grid);
		
		
		this.setName("GameField");
		
	}


	public Grid getGrid() {
		return grid;
	}


	public void setGrid(Grid grid) {
		this.grid = grid;
	}
}
