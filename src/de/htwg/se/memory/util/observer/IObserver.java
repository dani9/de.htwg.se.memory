package de.htwg.se.memory.util.observer;

public interface IObserver {
	
	public void update(Topic topic);
	public static enum Topic{
		GAME_INIT,
		NEW_GAME_STARTED,
		WAIT_FOR_CHOICE,
		CHOICE_WAS_MADE,
		NEXT_PLAYER,
		WAIT_FOR_NEXT_PLAYER,
		GAME_FINISHED
	};
	
}


