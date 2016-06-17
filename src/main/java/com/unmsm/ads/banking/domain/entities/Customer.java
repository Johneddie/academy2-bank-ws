package com.unmsm.ads.banking.domain.entities;

import java.util.Set;

public class Customer {
	private long id;
	private String firstName;
	private String lastName;
	private boolean isEnabled;
	private Set<BankAccount> bankAccounts;
	
	public Customer() {		
	}

	public long getId() {
		return id;
	}

	public void setId(long pId) {
		this.id = pId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String pFirstName) {
		this.firstName = pFirstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String pLastName) {
		this.lastName = pLastName;
	}

	public boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(boolean pIsEnabled) {
		this.isEnabled = pIsEnabled;
	}

	public Set<BankAccount> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(Set<BankAccount> pBankAccounts) {
		this.bankAccounts = pBankAccounts;
	}

	public void disable() {
		if (this.isEnabled) {
			this.isEnabled = false;
		}
	}

	public void enable() {
		if (!this.isEnabled) {
			this.isEnabled = true;
		}
	}

	public String getFullName() {
		return String.format("%s, %s", this.lastName, this.firstName);
	}
}