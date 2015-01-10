package de.htwg.se.memory.gui;



import javax.swing.JPanel;

public class GameFieldPanel extends JPanel{


	private static final long serialVersionUID = 7050391421149574318L;
	private Grid grid;
	
	
	public GameFieldPanel(int cardCount, int preferredSize) {
		
		int size = (preferredSize-(20*cardCount))/cardCount ;
		grid = new Grid(cardCount, cardCount,size);
		
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
