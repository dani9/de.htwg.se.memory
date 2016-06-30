package de.htwg.se.memory.aview.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.htwg.se.memory.model.player.Player;

public class PanelInfo extends JPanel {

	private static final long serialVersionUID = -6662534236167851124L;

	private Player userOne, userTwo;
	private JLabel info = new JLabel();

	public PanelInfo() {

		this.userOne = null;
		this.userTwo = null;
		this.info.setFont(new Font(Font.SERIF, 0, 20));
		this.setLayout(new BorderLayout());
		
		
		
		this.add(info, BorderLayout.WEST);
		this.setVisible(true);
		this.setBackground(Color.WHITE);
		this.setName("PanelInfo");

	}
	public void setUsers(Player userOne, Player userTwo){
		
		this.userOne = userOne;
		this.userTwo = userTwo;
		
	}
	
	public void refresh() {
		this.info.setText(statusText());
		super.repaint();
	}

	private String statusText() {

		if (this.userOne == null || this.userTwo == null){
			return "";
		}
		int pointsOne = this.userOne.getPoints();

		int pointsTwo = this.userTwo.getPoints();

		String nicknameTwo = this.userTwo.getName();
		String nicknameOne = this.userOne.getName(); 

		return " "+nicknameOne + ": " + pointsOne + "   " + nicknameTwo + ": " + pointsTwo;
	}

}
