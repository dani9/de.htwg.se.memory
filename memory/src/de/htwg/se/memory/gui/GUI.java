package de.htwg.se.memory.gui;

import javax.swing.JFrame;

import de.htwg.se.memory.util.IconContainer;

public class GUI extends JFrame {
	
	private static final long serialVersionUID = -4305625036085082377L;

	public GUI() {
		
		this.setTitle("GUI MEMORY LAYOUT");
		this.setName("main");
		
		Grid grid = new Grid(5, 5,100);
		
		this.setJMenuBar(new MenuBar());
		this.add(grid);
		
		grid.setIcon("BTN0", "PIC"+(IconContainer.ACE+IconContainer.SPADE));
		
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.validate();
		this.setVisible(true);
		this.pack();
		
	}

	public static void main(String[] args) {
		new GUI();
	}

}
