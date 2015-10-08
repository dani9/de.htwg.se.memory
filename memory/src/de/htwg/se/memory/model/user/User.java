package de.htwg.se.memory.model.user;
import java.io.Serializable;

public class User implements Serializable{
	
	private static final long serialVersionUID = 7556473399662776644L;
	
	private String name;
	private String nickname;
	private String type;
	private int points;

	public User(String name, String nickname, String type) {
		this.name = name;
		this.nickname = nickname;
		this.type = type;
		this.points = 0;
	}

	public String getName() {
		return name;
	}

	public String getNickname() {
		return nickname;
	}

	public int getPoints() {
		return points;
	}

	public String getType() {
		return type;
	}

	public void setPoints(int points) {
		this.points = points;
	}


}
