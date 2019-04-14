package org.pmoo.packproyecto.exceptions;

public class DowngradeLimitException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2468649255831419925L;

	public DowngradeLimitException() {
	}

	public DowngradeLimitException(String message) {
		super(message);
	}

	public DowngradeLimitException(Throwable cause) {
		super(cause);
	}

	public DowngradeLimitException(String message, Throwable cause) {
		super(message, cause);
	}

	public DowngradeLimitException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
