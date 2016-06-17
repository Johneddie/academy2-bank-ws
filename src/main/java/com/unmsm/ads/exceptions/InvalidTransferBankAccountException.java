package com.unmsm.ads.exceptions;

@SuppressWarnings("serial")
public class InvalidTransferBankAccountException extends Exception {
	public InvalidTransferBankAccountException() {
		super("Cannot perform the transfer, invalid data in bank accounts specifications");
	}
}