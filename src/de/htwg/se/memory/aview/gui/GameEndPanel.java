package de.htwg.se.memory.aview.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import de.htwg.se.memory.controller.IController;
import de.htwg.se.memory.model.player.Player;

public class GameEndPanel extends JPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6862456149947254053L;

	public GameEndPanel(IController controller) {

		Border space = BorderFactory.createEmptyBorder(50,50,50,50);
		this.setBackground(Color.white);
		this.setLayout(new BorderLayout());
		this.setBorder(space);
		
		Player[] players = controller.getPlayers();
		String out = "";
		if(players[0].getPoints() >= players[1].getPoints()){
			out = players[0].getName()+ " wins!";
		}else{
			out = players[1].getName()+ " wins!";
		}
		
		JLabel jLabel = new JLabel(out);
		this.add(jLabel, BorderLayout.CENTER);
	}
	

}
