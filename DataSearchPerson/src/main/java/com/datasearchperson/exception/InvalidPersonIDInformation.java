package com.datasearchperson.exception;

public class InvalidPersonIDInformation extends Exception{

	private static final long serialVersionUID = 1L;
	
	public InvalidPersonIDInformation(String errorMessage) {
        super(errorMessage);
    }

}
