package de.htwg.se.memory.model.playingfield;

public interface IPlayingField {

	IField getField(int row, int column);

	void mix();

	int getColumn();

	void hideAll();

	boolean isAllGeuessed();

}