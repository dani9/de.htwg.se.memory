package de.htwg.se.memory.gui;

import java.awt.HeadlessException;

import javax.swing.JFrame;

public class GUI extends JFrame {
	
	private static final long serialVersionUID = -4305625036085082377L;

	public GUI() {
		
		this.setTitle("GUI MEMORY LAYOUT");
		this.setName("main");
		this.setJMenuBar(new MenuBar());
		
		
		this.setResizable(true);
		this.setVisible(true);
		this.pack();
		
	}

	public static void main(String[] args) {
		new GUI();
	}

}
