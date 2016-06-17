package com.unmsm.ads.banking.application.assemblers;

import com.unmsm.ads.banking.application.dtos.BankAccountDto;
import com.unmsm.ads.banking.domain.entities.BankAccount;

public class BankAccountAssembler {
	private CustomerAssembler customerAssembler;

	public void setCustomerAssembler(CustomerAssembler customerAssembler) {
		this.customerAssembler = customerAssembler;
	}

	public BankAccountDto toDto(BankAccount pBankAccount) {
		if (pBankAccount == null) {
			return null;
		}
		BankAccountDto bankAccountDto = new BankAccountDto();
		bankAccountDto.setId(pBankAccount.getId());
		bankAccountDto.setNumber(pBankAccount.getNumber());
		bankAccountDto.setBalance(pBankAccount.getBalance());
		bankAccountDto.setIsLocked(pBankAccount.getIsLocked());
		bankAccountDto.setCustomerDto(this.customerAssembler.toDto(pBankAccount.getCustomer()));
		return bankAccountDto;
	}

	public BankAccount toEntity(BankAccountDto pBankAccountDto) {
		if (pBankAccountDto == null) {
			return null;
		}
		BankAccount bankAccount = new BankAccount();
		bankAccount.setId(bankAccount.getId());
		bankAccount.setNumber(bankAccount.getNumber());
		bankAccount.setBalance(bankAccount.getBalance());
		bankAccount.setIsLocked(bankAccount.getIsLocked());
		bankAccount.setCustomer(this.customerAssembler.toEntity(pBankAccountDto.getCustomerDto()));
		return bankAccount;
	}
}