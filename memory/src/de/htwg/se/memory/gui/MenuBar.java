package de.htwg.se.memory.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

import de.htwg.se.memory.util.IconContainer;

public class MenuBar extends JMenuBar implements ActionListener {

	private static final long serialVersionUID = 2096553262948206430L;
	private JRadioButtonMenuItem[] jRadioButtonMenuItems;

	public MenuBar() {

		IconContainer icons = IconContainer.getInstance();

		JMenu menuGame = new JMenu("Game");
		JMenuItem itemStart = new JMenuItem("play", icons.getIcon("PIC" + IconContainer.USER));
		itemStart.setName("start");
		itemStart.addActionListener(this);

		JMenuItem itemExit = new JMenuItem("exit", icons.getIcon("PIC" + IconContainer.EXIT));
		itemExit.setName("exit");
		itemExit.addActionListener(this);

		menuGame.add(itemStart);
		menuGame.addSeparator();
		menuGame.addSeparator();
		menuGame.add(itemExit);

		JMenu menuSettings = createSettingsMenu();
		toggelRadioBoxTO(0);

		this.add(menuGame);
		this.add(menuSettings);

	}

	private JMenu createSettingsMenu() {
		JMenu menuSettings = new JMenu("Settings");

		jRadioButtonMenuItems = new JRadioButtonMenuItem[3];
		for (int i = 0; i < jRadioButtonMenuItems.length; i++) {
			addRadioOptionToMenu(menuSettings, i, 4);
		}
		return menuSettings;
	}

	private void addRadioOptionToMenu(JMenu menuSettings, int radioButtonNr, int option) {
		jRadioButtonMenuItems[radioButtonNr] = new JRadioButtonMenuItem(option + "x" + option);
		jRadioButtonMenuItems[radioButtonNr].setName(option + "");
		jRadioButtonMenuItems[radioButtonNr].addActionListener(this);
		menuSettings.add(jRadioButtonMenuItems[radioButtonNr]);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JComponent comp = (JComponent) e.getSource();

		String compName = comp.getName();
		if ("exit".equals(compName)) {
			System.exit(0);

		} else if ("start".equals(compName)) {

			// TODO
			System.out.println("START");

		} else if (comp instanceof JRadioButton) {
			toggelRadioBoxEvent(comp);

		}

	}

	private void toggelRadioBoxEvent(JComponent component) {

		for (int i = 0; i < jRadioButtonMenuItems.length; i++) {
			if (component.equals(jRadioButtonMenuItems[i])) {
				toggelRadioBoxTO(i);
			}
		}

	}

	private void toggelRadioBoxTO(int i) {
		setAllRadioBoxFalse();
		setRadioBoxTrue(i);
	}

	private void setRadioBoxTrue(int i) {
		jRadioButtonMenuItems[i].setSelected(true);
	}

	private void setAllRadioBoxFalse() {
		jRadioButtonMenuItems[0].setSelected(false);
		jRadioButtonMenuItems[1].setSelected(false);
		jRadioButtonMenuItems[2].setSelected(false);
	}

}
