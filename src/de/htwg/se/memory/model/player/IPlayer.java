package de.htwg.se.memory.model.player;

import com.google.inject.ImplementedBy;

import de.htwg.se.memory.model.player.impl.User;

@ImplementedBy(User.class)
public interface IPlayer {
	String getName();
	void addPoint();
	int getPoints();
	void setName(String playerName);
}
