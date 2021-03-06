package main.utils;

import java.io.Serializable;

public class SaveData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String data;
	
	public SaveData(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String toString() {
		return data;
	}
	
}
