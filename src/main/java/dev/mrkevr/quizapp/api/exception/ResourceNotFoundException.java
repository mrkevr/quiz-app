package dev.mrkevr.quizapp.api.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2739534634038094915L;

	public ResourceNotFoundException() {
		super("Resource not found");
	}

	public ResourceNotFoundException(String id) {
		super("Resource not found with id " + id);
	}
}
