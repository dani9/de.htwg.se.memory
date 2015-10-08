package de.htwg.se.memory.gui;



import javax.swing.JPanel;

import de.htwg.se.memory.controller.Controller;

public class GameFieldPanel extends JPanel{


	private static final long serialVersionUID = 7050391421149574318L;
	private Grid grid;
	
	
	public GameFieldPanel(Controller controller,int cardCount, int preferredSize) {
		
		int size = 5* preferredSize / cardCount ;
		grid = new Grid(controller,cardCount, cardCount,size);
		
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
