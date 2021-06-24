package com.datasearchperson.exception;

public class ImproperPersonInformation extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ImproperPersonInformation(String errorMessage) {
        super(errorMessage);
    }

}
