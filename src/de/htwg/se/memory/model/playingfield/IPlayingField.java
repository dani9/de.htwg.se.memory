package de.htwg.se.memory.model.playingfield;

public interface IPlayingField {

	IField getField(int row, int column);

	void mix();

	int getColumn();

	String toString();

	void hideAll();

	boolean isAllGeuessed();

}