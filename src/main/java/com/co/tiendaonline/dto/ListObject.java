package com.co.tiendaonline.dto;

public class ListObject {
	
	private long value;
	private String label;
	
	public ListObject() {
		super();
	}

	public ListObject(long value, String label) {
		super();
		this.value = value;
		this.label = label;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
