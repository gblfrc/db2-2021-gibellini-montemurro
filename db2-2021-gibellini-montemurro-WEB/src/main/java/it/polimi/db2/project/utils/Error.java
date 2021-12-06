package it.polimi.db2.project.utils;

public class Error {
	
	int code;
	String message;
	
	public Error(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String getMessage() {
		return this.message;
	}

}
