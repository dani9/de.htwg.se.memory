package de.htwg.se.memory.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;


public class ScoreBoard extends JPanel{

	private static final long serialVersionUID = -3588532668650810124L;
	
	public ScoreBoard() {
		
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		this.add(new ScoreTable(),BorderLayout.CENTER);
	
	}
	public void refresh(){
		
	}
	

}
