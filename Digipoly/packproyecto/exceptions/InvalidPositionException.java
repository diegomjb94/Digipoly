package org.pmoo.packproyecto.exceptions;

public class InvalidPositionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7420102789087889335L;

	public InvalidPositionException() {
	}

	public InvalidPositionException(String arg0) {
		super(arg0);
	}

	public InvalidPositionException(Throwable arg0) {
		super(arg0);
	}

	public InvalidPositionException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidPositionException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
