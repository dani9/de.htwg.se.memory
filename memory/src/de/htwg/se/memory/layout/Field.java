package de.htwg.se.memory.layout;

public class Field {
	private String fieldId;
	private String pathToPicture;
	
	public Field(String fieldId) {
		setFieldId(fieldId);
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
}
