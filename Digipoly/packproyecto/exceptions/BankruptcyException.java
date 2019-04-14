package org.pmoo.packproyecto.exceptions;

public class BankruptcyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8419458881463601359L;

	public BankruptcyException() {
	}

	public BankruptcyException(String message) {
		super(message);
	}

	public BankruptcyException(Throwable cause) {
		super(cause);
	}

	public BankruptcyException(String message, Throwable cause) {
		super(message, cause);
	}

	public BankruptcyException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
