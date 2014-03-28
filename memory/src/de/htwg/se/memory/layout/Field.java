package de.htwg.se.memory.layout;

public class Field {
	String fieldId;
	
	public Field(String fieldId) {
		setFieldId(fieldId);
	}

	private void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	
	public String getFieldId() {
		return fieldId;
	}
}
