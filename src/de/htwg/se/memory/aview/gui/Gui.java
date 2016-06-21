package de.htwg.se.memory.aview.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import de.htwg.se.memory.controller.Controller;
import de.htwg.se.memory.model.player.Player;
import de.htwg.se.memory.util.observer.IObserver;

public class Gui extends JFrame implements IObserver {

	private static final long serialVersionUID = -4305625036085082377L;
	public static final int PANEL_START = 0;
	public static final int PANEL_PLAY = 1;
	public static final int PANEL_SCORE = 2;
	private CardLayout mainPanel;
	private JPanel mainCardPanel;
	private int turn;
	private Controller controller;
	private MenuBar menuBar;
	private JLabel statusLabel;
	public Gui(Controller controller) {

		this.controller = controller;

		this.setTitle("MEMORY");

		this.setName("main");

		menuBar = new MenuBar();
		JPanel frontPanel = new JPanel();
		frontPanel.setLayout(new BorderLayout());
		frontPanel.setBackground(Color.white);

		mainPanel = new CardLayout();
		mainCardPanel = new JPanel(mainPanel);

		/* TEST */
		// mainCardPanel.add(new GameFieldPanel(this.controller,
		// this.controller.getPlayFieldSize() *
		// this.controller.getPlayFieldSize(), 900), "");
		mainCardPanel.add(new ScoreBoard(), "test");

		frontPanel.add(mainCardPanel);
		this.setLayout(new BorderLayout());
		// mainPanel.show(mainCardPanel, "test");
		this.turn = 0;

		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		this.add(statusPanel, BorderLayout.SOUTH);
		statusPanel.setPreferredSize(new Dimension(this.getWidth(), 16));
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		statusLabel = new JLabel("status");
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		statusPanel.add(statusLabel);

		
		
		this.add(frontPanel, BorderLayout.CENTER);
		this.setJMenuBar(menuBar);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.validate();
		this.setVisible(true);
		this.pack();

	}

	public void refresh(int panelToShow) {

		showPanel(panelToShow);
		
		this.revalidate();
		this.repaint();

	}

	public void showPanel(int index) {
		mainPanel.show(mainCardPanel, index + "");
	}

	public void setPanel(JPanel panel, int index) {
		panel.setName(index + "");
		mainCardPanel.add(panel, index + "");
	}

	public JPanel getPanel(int index) {

		Component[] components = mainCardPanel.getComponents();
		for (Component component : components) {
			if ((index + "").equals(component.getName())) {
				return (JPanel) component;
			}
		}
		return null;
	}

	@Override
	public void update(Topic topic) {
		String status = "";
		switch (topic) {
		//case CHOICE_WAS_MADE:
		case NEW_GAME_STARTED:
		case WAIT_FOR_CHOICE:
		//case NEXT_PLAYER:
		case WAIT_FOR_NEXT_PLAYER:
			
			mainCardPanel.add(new GameFieldPanel(controller,topic,controller.getPlayFieldSize() *2  ,920), this.turn + "");
			Player[] players = controller.getPlayers();
			status = players[0].getName()+": "+players[0].getPoints() +
					" | " +players[1].getName()+ ": "+players[1].getPoints()+
					"          "+controller.getActivePlayerName()+"'s turn." ;
			break;
		
		
		case GAME_FINISHED:
			Player[] playerss = controller.getPlayers();
		
			if(playerss[0].getPoints() >= playerss[1].getPoints()){
				status = playerss[0].getName()+ " wins!";
			}else{
				status = playerss[1].getName()+ " wins!";
			}
			//mainCardPanel.add(new GameEndPanel(controller), this.turn + "");
		

		case GAME_INIT:

			mainCardPanel.add(new GameStartPanel(controller), this.turn + "");
			break;
		default:
			break;

		}
		
		statusLabel.setText((status+ "                                     "+topic).trim());
		
		this.refresh(this.turn++);
	}
}
