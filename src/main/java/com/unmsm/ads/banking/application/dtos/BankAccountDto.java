package com.unmsm.ads.banking.application.dtos;

import java.math.BigDecimal;

public class BankAccountDto {
	private long id;
	private String number;
	private BigDecimal balance;
	private boolean isLocked;
	private CustomerDto customerDto;
	
	public BankAccountDto() {
	}

	public long getId() {
		return id;
	}

	public void setId(long pId) {
		this.id = pId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String pNumber) {
		this.number = pNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal pBalance) {
		this.balance = pBalance;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setIsLocked(boolean pIsLocked) {
		this.isLocked = pIsLocked;
	}

	public CustomerDto getCustomerDto() {
		return customerDto;
	}

	public void setCustomerDto(CustomerDto pCustomerDto) {
		this.customerDto = pCustomerDto;
	}
}