package de.htwg.se.memory.user;
import java.io.Serializable;

public class User implements Serializable{
	private String name;
	private String nickname;
	private String type;
	private int points;

	public User(String name, String nickname, String type) {
		this.name = name;
		this.nickname = nickname;
		this.type = type;
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
