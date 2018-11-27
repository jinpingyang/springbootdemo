package com.example.demo.exception;

public class AuthIgnoreException extends Exception {
	

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthIgnoreException(String message) {
        super(message);
    }

    public AuthIgnoreException(String message, Throwable cause) {
        super(message, cause);
    }

}
