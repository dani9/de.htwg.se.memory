package de.htwg.se.memory.model.player.impl;
import java.io.Serializable;

import de.htwg.se.memory.model.player.IPlayer;


public class User implements Serializable, IPlayer{
	
	private static final long serialVersionUID = 7556473399662776644L;
	
	private String name = "";
	private int points = 0;
	
	public User() {
	
	}
	

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	
	@Override
	public void addPoint() {
		++points;
		
	}

	@Override
	public void setName(String playerName) {
		this.name = playerName;
		
	}

}
