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
		
	
	
	public abstract Object getType();

	public abstract void setType(Object type);

	public abstract int getPoints() ;

	public abstract void setPoints(int points);

	public abstract String getName() ;

	public abstract void setName(String name) ;

	public abstract String getNickname() ;

	public abstract void setNickname(String nickname);

}
