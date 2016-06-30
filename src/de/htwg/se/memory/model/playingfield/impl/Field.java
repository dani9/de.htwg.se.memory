package de.htwg.se.memory.model.playingfield.impl;

import java.util.Random;

import org.apache.log4j.Logger;

import de.htwg.se.memory.model.playingfield.IField;

public class Field implements Comparable<IField>, IField{
	private static String hiddenText = "$$";
	private String fieldId;
	private String pathToPicture;
	private boolean isVisible = false;
	private boolean isGuessed = false;
	
	private int hashaddition;
	
	private static final Logger LOGGER = Logger.getLogger(Field.class);
	
	public Field(String fieldId) {
		this.fieldId = fieldId;
		Random rand = new Random(120912);
		this.pathToPicture = "";
		this.hashaddition = rand.nextInt();
	}

	
	@Override
	public boolean isGuessed() {
		return isGuessed;
	}

	@Override
	public void setGuessed(boolean isGuessed) {
		this.isGuessed = isGuessed;
	}

	
	@Override
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	
	@Override
	public String getFieldId() {
		return fieldId;
	}
	
	@Override
	public String getPathToPicture(){
		return pathToPicture;
	}
	
	@Override
	public void setPathToPicture(String pathToPicture){
		this.pathToPicture=pathToPicture;
	}
	
	@Override
	public void setVisble(){
		isVisible = true;
	}
	
	@Override
	public void setHidden(){
		isVisible = false;
	}
	
	@Override
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
	public int compareTo(IField o) {
		return fieldId.compareTo(o.getFieldId());

	}

	@Override
	public boolean equals(Object o){
		Field field;
		try {
		   field= (Field)o;
		}catch (Exception e) {
			LOGGER.error(e);
			return false;
		}
		return compareTo(field) == 0;
	}
	
	@Override
	public int hashCode(){
		return fieldId.hashCode() + pathToPicture.hashCode() + this.hashaddition;
	}

}
