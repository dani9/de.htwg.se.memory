package de.htwg.se.memory.model.player;
import java.io.Serializable;


public class User implements Serializable, Player{
	
	private static final long serialVersionUID = 7556473399662776644L;
	
	private String name;
	private String nickname;
	private int points;
	public User(String name, String nickname) {
		this.name = name;
		this.nickname = nickname;
	}

	@Override
	public String getName() {
		return name;
	}

	public String getNickname() {
		return nickname;
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

}
