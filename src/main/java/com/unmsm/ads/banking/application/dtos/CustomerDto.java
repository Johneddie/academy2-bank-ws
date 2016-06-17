package com.unmsm.ads.banking.application.dtos;

import java.util.Set;

public class CustomerDto {
	private long id;
	private String firstName;
	private String lastName;
	private boolean isEnabled;
	private Set<BankAccountDto> bankAccountsDto;

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
	
	public Set<BankAccountDto> getBankAccountsDto() {
		return bankAccountsDto;
	}

	public void setBankAccountsDto(Set<BankAccountDto> pBankAccountsDto) {
		this.bankAccountsDto = pBankAccountsDto;
	}
}