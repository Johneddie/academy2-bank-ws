package com.unmsm.ads.banking.domain.entities;

import java.math.BigDecimal;

import com.unmsm.ads.exceptions.*;

public class BankAccount {
	private long id;
	private String number;
	private BigDecimal balance;
	private boolean isLocked;
	private Customer customer;
	
	public BankAccount() {		
	}

	public long getId() {
		return id;
	}

	public void setId(long pId) {
		this.id = pId;
	}
	
	public String getNumber() {
		return this.number;
	}

	public void setNumber(String pNumber) {
		this.number = pNumber;
	}

	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setBalance(BigDecimal pBalance) {
		this.balance = pBalance;
	}

	public boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(boolean pIsLocked) {
		this.isLocked = pIsLocked;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer pCustomer) {
		this.customer = pCustomer;
	}

	public void lock() {
		if (!this.isLocked) {
			this.isLocked = true;
		}
	}

	public void unLock() {
		if (this.isLocked) {
			this.isLocked = false;
		}
	}
	
	public boolean hasIdentity() {
		return !this.number.trim().equals("");
	}

	public void withdrawMoney(BigDecimal pAmount) throws Exception {
		this.validateWithdrawMoney(pAmount);
		this.balance = this.balance.subtract(pAmount);
	}

	public void depositMoney(BigDecimal pAmount) throws Exception {
		this.validateDepositMoney(pAmount);
		this.balance = this.balance.add(pAmount);
	}

	private void validateWithdrawMoney(BigDecimal pAmount) throws AmountLessOrEqualToZeroException, CannotWithdrawException {
		this.validateAmount(pAmount);
		if (!this.canBeWithdrawed(pAmount)) {
			throw new CannotWithdrawException();
		}
	}

	private void validateDepositMoney(BigDecimal pAmount) throws AmountLessOrEqualToZeroException, CannotDepositException {
		this.validateAmount(pAmount);
		if (this.isLocked) {
			throw new CannotDepositException();
		}
	}

	private void validateAmount(BigDecimal pAmount) throws AmountLessOrEqualToZeroException {
		if (pAmount.signum() <= 0) {
			throw new AmountLessOrEqualToZeroException();
		}
	}

	public boolean canBeWithdrawed(BigDecimal pAmount) {
		return !this.isLocked && (this.balance.compareTo(pAmount) >= 0);
	}
}