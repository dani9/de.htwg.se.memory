package de.htwg.se.memory.model.playingfield;

public interface IField {

	boolean isGuessed();

	void setGuessed(boolean isGuessed);

	void setFieldId(String fieldId);

	String getFieldId();

	String getPathToPicture();

	void setPathToPicture(String pathToPicture);

	void setVisble();

	void setHidden();

	boolean isVisible();

	int compareTo(IField o);

}