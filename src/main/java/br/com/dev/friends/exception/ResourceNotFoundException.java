package br.com.dev.friends.exception;

public class ResourceNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8909405963425185571L;

	public ResourceNotFoundException(final String message) {
		super(message);
	}
}
