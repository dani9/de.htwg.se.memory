package de.htwg.se.memory.aview.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.htwg.se.memory.controller.Controller;
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
	private PanelInfo panelInfo;
	private MenuBar menuBar;
	public Gui(Controller controller) {

		this.controller = controller;

		this.setTitle("GUI MEM LAYOUT");

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
		panelInfo = new PanelInfo();

		frontPanel.add(mainCardPanel);
		frontPanel.add(panelInfo, BorderLayout.SOUTH);
		// mainPanel.show(mainCardPanel, "test");
		this.turn = 0;

		this.add(frontPanel);
		this.setJMenuBar(menuBar);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.validate();
		this.setVisible(true);
		this.pack();

	}

	public void refresh(int panelToShow) {

		showPanel(panelToShow);
		panelInfo.refresh();
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

		switch (topic) {
		case CHOICE_WAS_MADE:
		case NEW_GAME_STARTED:
		case WAIT_FOR_CHOICE:
		case NEXT_PLAYER:
		case WAIT_FOR_NEXT_PLAYER:
			
			mainCardPanel.add(new GameFieldPanel(controller,topic,controller.getPlayFieldSize() * 2,500/controller.getPlayFieldSize()), this.turn + "");
			
			break;
		
		
		case GAME_FINISHED:

			System.out.println("GAME FINISHED");

		case GAME_INIT:

			mainCardPanel.add(new GameStartPanel(controller), this.turn + "");
			panelInfo.setUsers(controller.getPlayers()[0], controller.getPlayers()[1]);
			break;

		}
		this.refresh(this.turn++);
	}
}
