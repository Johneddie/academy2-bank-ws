package com.unmsm.ads.exceptions;

@SuppressWarnings("serial")
public class CannotDepositException extends Exception {
	public CannotDepositException() {
		super("Cannot deposit in the account, it is locked");
	}
}