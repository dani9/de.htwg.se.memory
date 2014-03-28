package de.htwg.se.memory.user;

public abstract class User {
	private String name;
	private String nickname;
	private Object type;
	private int points;

	public User(String name, String nickname, Object type) {
		this.name = name;
		this.nickname = nickname;
		this.type = type;
	}

	public Object getType() {
		return type;
	}

	public void setType(Object type) {
		this.type = type;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
