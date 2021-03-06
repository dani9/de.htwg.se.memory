package de.htwg.se.memory.aview.gui;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;

import de.htwg.se.memory.util.IconContainer;

public class MenuBar extends JMenuBar implements ActionListener {

	private static final long serialVersionUID = 2096553262948206430L;
	private JRadioButtonMenuItem[] jRadioButtonMenuItems;

	public MenuBar() {

		IconContainer icons = IconContainer.getInstance();

		JMenu menuGame = new JMenu("Game");

		JMenuItem itemExit = new JMenuItem("exit", icons.getIcon("PIC" + IconContainer.EXIT));
		itemExit.setName("exit");
		itemExit.addActionListener(this);

		menuGame.add(itemExit);

		JMenu menuSettings = createSettingsMenu();
		toggelRadioBoxTO(0);
		this.setName("menuBar");
		this.add(menuGame);
		this.add(menuSettings);

	}

	private JMenu createSettingsMenu() {
		JMenu menuSettings = new JMenu("Settings");

		jRadioButtonMenuItems = new JRadioButtonMenuItem[3];
		for (int i = 0; i < jRadioButtonMenuItems.length; i++) {
			addRadioOptionToMenu(menuSettings, i, (i + 1) * 4);
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

			Window window = SwingUtilities.getWindowAncestor(this);
			window.setVisible(false);

		} else if (comp instanceof JRadioButtonMenuItem) {
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

	public int getRadioSelectedValue() {

		for (JRadioButtonMenuItem jRadioButtonMenuItem : jRadioButtonMenuItems) {
			if (jRadioButtonMenuItem.isSelected()) {
				return Integer.parseInt(jRadioButtonMenuItem.getName());

			}
		}

		return 4;
	}

	private void toggelRadioBoxTO(int i) {
		setAllRadioBoxFalse();
		setRadioBoxTrue(i);
	}

	private void setRadioBoxTrue(int i) {
		jRadioButtonMenuItems[i].setSelected(true);
	}

	private void setAllRadioBoxFalse() {

		for (int i = 0; i < jRadioButtonMenuItems.length; i++) {
			jRadioButtonMenuItems[i].setSelected(false);
		}

	}

}
