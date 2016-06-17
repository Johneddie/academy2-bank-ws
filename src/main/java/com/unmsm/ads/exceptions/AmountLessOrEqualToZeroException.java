package com.unmsm.ads.exceptions;

@SuppressWarnings("serial")
public class AmountLessOrEqualToZeroException extends Exception {
	public AmountLessOrEqualToZeroException() {
		super("The amount cannot be less than or equal to zero");
	}
}