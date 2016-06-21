package de.htwg.se.memory.aview.gui;

import javax.swing.JTable;

public class ScoreTable extends JTable {

	private static final long serialVersionUID = 1728530253027401716L;

	public ScoreTable() {
		this.setDefaultRenderer(getClass(), new ScoreTableRenderer());
	}
	
	public void sort(){
		
	}
	
	public void add(){
		
	}
	
}
