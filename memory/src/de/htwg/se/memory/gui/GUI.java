package de.htwg.se.memory.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.htwg.se.memory.controller.Controller;
import de.htwg.se.memory.model.user.User;

public class GUI extends JFrame {
	
	private static final long serialVersionUID = -4305625036085082377L;
	public static final int PANEL_START = 0;
	public static final int PANEL_PLAY = 1;
	public static final int PANEL_SCORE = 2;
	private CardLayout mainPanel;
	private JPanel mainCardPanel;
	
	public GUI(Controller controller) {
		
		
		
		
		this.setTitle("GUI MEM LAYOUT");
		
		controller.startGame(10, false);
		
		this.setName("main");
		
		
		JPanel frontPanel = new JPanel();
		frontPanel.setLayout(new BorderLayout());
		frontPanel.setBackground(Color.white);
		
		
		mainPanel = new CardLayout();
		mainCardPanel = new JPanel(mainPanel);
		
		/*TEST*/
		mainCardPanel.add(new GameFieldPanel(controller,controller.getPlayFieldSize()*controller.getPlayFieldSize(), 900), "");
		mainCardPanel.add(new ScoreBoard(), "test");
		
		User user_0 = new User("test0", "TestUser_0", "n");
		User user_1 = new User("test1", "TestUSer_1", "n");
		PanelInfo infopanel = new PanelInfo(user_0, user_1);
		
		
		
		
		frontPanel.add(mainCardPanel);
		frontPanel.add(infopanel, BorderLayout.SOUTH);
		//mainPanel.show(mainCardPanel, "test");
		
		
		this.add(frontPanel);
		
		this.setJMenuBar(new MenuBar());
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.validate();
		this.setVisible(true);
		this.pack();
		
	}

	public static void main(String[] args) {
		new GUI(new Controller());
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
