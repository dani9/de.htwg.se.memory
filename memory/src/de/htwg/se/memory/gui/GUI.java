package de.htwg.se.memory.gui;

import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame {
	
	private static final long serialVersionUID = -4305625036085082377L;
	public static final int PANEL_START = 0;
	public static final int PANEL_PLAY = 1;
	public static final int PANEL_SCORE = 2;
	private CardLayout mainPanel;
	private JPanel mainCardPanel;
	
	public GUI() {
		
		this.setTitle("GUI MEMORY LAYOUT");
		this.setName("main");
		
		
		
		mainPanel = new CardLayout();
		mainCardPanel = new JPanel(mainPanel);
		
		/*TEST*/
		mainCardPanel.add(new GameFieldPanel(1, 900), "test");
		mainPanel.show(mainCardPanel, "test");
		
		
		this.add(mainCardPanel);
		
		this.setJMenuBar(new MenuBar());
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.validate();
		this.setVisible(true);
		this.pack();
		
	}

	public static void main(String[] args) {
		new GUI();
	}
	
	public void showPanel(int index){
		mainPanel.show(mainCardPanel,index+"");
	}
	
	public void setPanel(JPanel panel, int index){
		panel.setName(index+"");
		mainCardPanel.add(panel, index+"");
	}
	
	public JPanel getPanel(int index){
		
		Component[] components = mainCardPanel.getComponents();
		for (Component component : components) {
			if((index+"").equals(component.getName())){
				return (JPanel) component;
			}
		}
		return null;
	}
	
	

}
