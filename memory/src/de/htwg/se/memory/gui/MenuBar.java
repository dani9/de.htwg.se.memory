package de.htwg.se.memory.gui;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import de.htwg.se.memory.util.IconContainer;

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 2096553262948206430L;
	private ButtonGroup radioGroup;

	public MenuBar() {

		IconContainer icons = IconContainer.getInstance();

		JMenu menuGame = new JMenu("Game");
		JMenuItem itemStart = new JMenuItem("play", icons.getIcon("PIC" + IconContainer.RIGHT));
		JMenuItem itemExit = new JMenuItem("exit", icons.getIcon("PIC" + IconContainer.EXIT));

		menuGame.add(itemStart);
		menuGame.addSeparator();
		menuGame.addSeparator();
		menuGame.add(itemExit);

		JMenu menuSettings = new JMenu("Settings");
		radioGroup = new ButtonGroup();

		addRadioOptionToMenu(menuSettings, 8);
		addRadioOptionToMenu(menuSettings, 16);
		addRadioOptionToMenu(menuSettings, 32);
		

		this.add(menuGame);
		this.add(menuSettings);

	}

	private void addRadioOptionToMenu(JMenu menuSettings, int option) {
		JRadioButtonMenuItem radioButton = new JRadioButtonMenuItem(option + "x" + option);
		radioButton.setName(option + "");
		radioGroup.add(radioButton);
		menuSettings.add(radioButton);
	}

	public int getRadioOption() {
		return Integer.parseInt(radioGroup.getSelection().getActionCommand());
	}

}
