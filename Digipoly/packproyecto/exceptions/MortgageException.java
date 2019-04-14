package org.pmoo.packproyecto.exceptions;

public class MortgageException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8625959074115923428L;

	public MortgageException() {
	}

	public MortgageException(String arg0) {
		super(arg0);
	}

	public MortgageException(Throwable arg0) {
		super(arg0);
	}

	public MortgageException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public MortgageException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
