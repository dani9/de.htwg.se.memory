package de.htwg.se.memory.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import de.htwg.se.memory.util.IconContainer;

public class GridActionListener implements ActionListener {

	int size;

	public GridActionListener(int size) {
		super();
		this.size = size;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		IconContainer icons = IconContainer.getInstance();

		System.out.println("CardPressed ID: "
				+ ((JButton) arg0.getSource()).getName());
		((JButton) arg0.getSource()).setIcon(icons
				.getIcon(("PIC" + (IconContainer.ACE + IconContainer.HEART)),
						size, size));
	}

}
