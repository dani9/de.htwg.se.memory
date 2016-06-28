package de.htwg.se.memory.model.playingfield;

import java.util.Random;

import org.apache.log4j.Logger;

public class Field implements Comparable<Field>{
	private static String hiddenText = "$$";
	private String fieldId;
	private String pathToPicture;
	private boolean isVisible = false;
	private boolean isGuessed = false;
	
	private int hashaddition;

	private static final Logger LOGGER = Logger.getLogger("de.htwg.se.memory.model.playingfield");
	
	public Field(String fieldId) {
		this.fieldId = fieldId;
		Random rand = new Random(120912);
		this.pathToPicture = "";
		this.hashaddition = rand.nextInt();
	}

	
	public boolean isGuessed() {
		return isGuessed;
	}

	public void setGuessed(boolean isGuessed) {
		this.isGuessed = isGuessed;
	}

	
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	
	public String getFieldId() {
		return fieldId;
	}
	
	public String getPathToPicture(){
		return pathToPicture;
	}
	
	public void setPathToPicture(String pathToPicture){
		this.pathToPicture=pathToPicture;
	}
	
	public void setVisble(){
		isVisible = true;
	}
	
	public void setHidden(){
		isVisible = false;
	}
	
	public boolean isVisible() {
		return isVisible;
	}
	
	@Override
	public String toString(){
		if(isVisible){
			return fieldId;
		}
		else{
			if(isGuessed){
				return "";
			}else{
				return hiddenText;
			}
		}
		
	}

	@Override
	public int compareTo(Field o) {
		return fieldId.compareTo(o.fieldId);

	}

	@Override
	public boolean equals(Object o){
		Field field;
		try {
		   field= (Field)o;
		}catch (Exception e) {
			return false;
		}
		return compareTo(field) == 0;
	}
	
	@Override
	public int hashCode(){
		return fieldId.hashCode() + pathToPicture.hashCode() + this.hashaddition;
	}

}
