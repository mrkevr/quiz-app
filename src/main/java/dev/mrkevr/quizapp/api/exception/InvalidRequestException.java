package dev.mrkevr.quizapp.api.exception;

public class InvalidRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8166651246597171493L;

	public InvalidRequestException(String message) {
		super(message);
	}
}
