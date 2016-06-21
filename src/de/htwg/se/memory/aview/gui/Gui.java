package de.htwg.se.memory.aview.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.htwg.se.memory.controller.Controller;
import de.htwg.se.memory.model.player.User;
import de.htwg.se.memory.util.observer.IObserver;

public class Gui extends JFrame implements IObserver{

	private static final long serialVersionUID = -4305625036085082377L;
	public static final int PANEL_START = 0;
	public static final int PANEL_PLAY = 1;
	public static final int PANEL_SCORE = 2;
	private CardLayout mainPanel;
	private JPanel mainCardPanel;
	private int turn;
	private Controller controller;
	private PanelInfo panelInfo;

	public Gui(Controller controller) {

		this.controller = controller;

		this.setTitle("GUI MEM LAYOUT");

		this.setName("main");

		JPanel frontPanel = new JPanel();
		frontPanel.setLayout(new BorderLayout());
		frontPanel.setBackground(Color.white);

		mainPanel = new CardLayout();
		mainCardPanel = new JPanel(mainPanel);

		/* TEST */
		//mainCardPanel.add(new GameFieldPanel(this.controller, this.controller.getPlayFieldSize() * this.controller.getPlayFieldSize(), 900), "");
		mainCardPanel.add(new ScoreBoard(), "test");
		panelInfo = new PanelInfo(new User("not set", "not set", controller), new User("not set", "not set", controller));

		frontPanel.add(mainCardPanel);
		frontPanel.add(panelInfo, BorderLayout.SOUTH);
		// mainPanel.show(mainCardPanel, "test");

		this.add(frontPanel);

		this.setJMenuBar(new MenuBar());
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.validate();
		this.setVisible(true);
		this.pack();

	}

	public void refresh() {

		mainCardPanel.add(new GameFieldPanel(controller, controller.getPlayFieldSize() * controller.getPlayFieldSize(), 900), this.turn + "");
		showPanel(this.turn);
		panelInfo.refresh();

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
		// TODO Auto-generated method stub
		
	}

}
