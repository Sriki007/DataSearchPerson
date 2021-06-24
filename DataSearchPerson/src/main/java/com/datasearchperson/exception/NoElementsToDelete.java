package com.datasearchperson.exception;

public class NoElementsToDelete extends Exception{

	private static final long serialVersionUID = 1L;
	
	public NoElementsToDelete(String errorMessage) {
        super(errorMessage);
    }

}
