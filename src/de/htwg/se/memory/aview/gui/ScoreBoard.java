package de.htwg.se.memory.aview.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;


public class ScoreBoard extends JPanel{

	private static final long serialVersionUID = -3588532668650810124L;
	
	public ScoreBoard() {
		
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		this.add(new ScoreTable(),BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(500,500));
	
	}
	public void refresh(){
		
	}
	

}
