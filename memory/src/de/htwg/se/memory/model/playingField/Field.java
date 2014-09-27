package de.htwg.se.memory.model.playingField;



public class Field implements Comparable<Field>{
	private static String hiddenText = "$$";
	private String fieldId;
	private String pathToPicture;
	boolean isVisible = false;
	
	public Field(String fieldId) {
		this.fieldId = fieldId;
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
	
	@Override
	public String toString(){
		if(isVisible){
			return fieldId;
		}
		else{
			return hiddenText;
		}
	}



	@Override
	public int compareTo(Field o) {
		return fieldId.compareTo(o.fieldId);

	}


	

}
