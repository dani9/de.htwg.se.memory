package de.htwg.se.memory.aview.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import de.htwg.se.memory.controller.Controller;

public class GameStartPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 4375644209073794290L;
	private Controller controller;
	private JTextField player0, player1;

	public GameStartPanel(Controller controller) {
		Border space = BorderFactory.createEmptyBorder();
		this.setBackground(Color.white);
		this.controller = controller;
		this.setName("GameStartPanel");
		this.player0 = new JTextField("Player0");
		this.player1 = new JTextField("Player1");

		player0.setPreferredSize(new Dimension(20, 100));
		player1.setPreferredSize(new Dimension(20, 100));

		JLabel label = new JLabel("Enter Name:");

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(this);
		btnStart.setName("start");

		JPanel editPanel = new JPanel(new GridLayout(1, 2, 20, 20));
		editPanel.add(this.player0);
		editPanel.add(this.player1);
		editPanel.setBackground(Color.white);

		JPanel centerPanel = new JPanel(new GridLayout(3, 1, 20, 20));

		this.setLayout(new BorderLayout());

		centerPanel.add(label);

		centerPanel.add(editPanel);

		centerPanel.add(btnStart);

		centerPanel.setBackground(Color.white);
		Border raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		centerPanel.setBorder(raisedetched);

		this.add(centerPanel, BorderLayout.CENTER);
		this.setBorder(space);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {

			if ("start".equals(((JButton) e.getSource()).getName())) {

				int fieldSize = getFieldSizeFromGuiObject(getGuiObject());
				controller.startGame(fieldSize, player0.getText(), player1.getText());

			}

		}
	}

	private int getFieldSizeFromGuiObject(Container parent) {
		int fieldSize = 4;
		if (parent instanceof Gui) {
			Gui gui = (Gui) parent;

			JMenuBar jMenuBar = gui.getJMenuBar();

			if (jMenuBar instanceof MenuBar) {
				MenuBar myMenuBar = (MenuBar) jMenuBar;
				fieldSize = myMenuBar.getRadioSelectedValue();
			}

		}
		return fieldSize;
	}

	private Container getGuiObject() {
		Container parent = this.getParent();
		while (parent.getParent() != null) {

			parent = parent.getParent();

		}
		return parent;
	}

}
