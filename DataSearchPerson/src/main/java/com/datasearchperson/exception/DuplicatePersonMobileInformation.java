package com.datasearchperson.exception;

public class DuplicatePersonMobileInformation extends Exception{

	private static final long serialVersionUID = 1L;
	
	public DuplicatePersonMobileInformation(String errorMessage) {
        super(errorMessage);
    }

}
