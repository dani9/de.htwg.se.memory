package de.htwg.se.memory.aview.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.htwg.se.memory.model.player.Player;

public class PanelInfo extends JPanel {

	private static final long serialVersionUID = -6662534236167851124L;

	private Player user_0, user_1;
	private JLabel info = new JLabel();

	public PanelInfo() {

		this.user_0 = null;
		this.user_1 = null;
		this.info.setFont(new Font(Font.SERIF, 0, 20));
		this.setLayout(new BorderLayout());
		
		
		
		this.add(info, BorderLayout.WEST);
		this.setVisible(true);
		this.setBackground(Color.WHITE);
		this.setName("PanelInfo");

	}
	public void setUsers(Player user_0, Player user_1){
		
		this.user_0 = user_0;
		this.user_1 = user_1;
		
	}
	
	public void refresh() {
		this.info.setText(statusText());
		super.repaint();
	}

	private String statusText() {

		if (this.user_0 == null || this.user_1 == null){
			return "";
		}
		int points_0 = this.user_0.getPoints();

		int points_1 = this.user_1.getPoints();

		String nickname_1 = this.user_1.getName();
		String nickname_0 = this.user_0.getName();
		String out = " "+nickname_0 + ": " + points_0 + "   " + nickname_1 + ": " + points_1;

		return out;
	}

}
