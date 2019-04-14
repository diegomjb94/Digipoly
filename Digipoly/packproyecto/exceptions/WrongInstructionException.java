package org.pmoo.packproyecto.exceptions;

public class WrongInstructionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6447638695584707422L;

	public WrongInstructionException() {
	}

	public WrongInstructionException(String message) {
		super(message);
	}

	public WrongInstructionException(Throwable cause) {
		super(cause);
	}

	public WrongInstructionException(String message, Throwable cause) {
		super(message, cause);
	}

	public WrongInstructionException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
