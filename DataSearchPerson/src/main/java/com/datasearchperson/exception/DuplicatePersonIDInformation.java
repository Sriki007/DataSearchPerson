package com.datasearchperson.exception;

public class DuplicatePersonIDInformation extends Exception{

	private static final long serialVersionUID = 1L;
	
	public DuplicatePersonIDInformation(String errorMessage) {
        super(errorMessage);
    }

}
