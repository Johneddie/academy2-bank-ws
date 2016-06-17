package com.unmsm.ads.banking.application.assemblers;

import java.util.HashSet;
import java.util.Set;

import com.unmsm.ads.banking.application.dtos.BankAccountDto;
import com.unmsm.ads.banking.application.dtos.CustomerDto;
import com.unmsm.ads.banking.domain.entities.BankAccount;
import com.unmsm.ads.banking.domain.entities.Customer;

public class CustomerAssembler {
	private BankAccountAssembler bankAccountAssembler;

	public void setBankAccountAssembler(BankAccountAssembler bankAccountAssembler) {
		this.bankAccountAssembler = bankAccountAssembler;
	}

	public CustomerDto toDto(Customer pCustomer) {
		if (pCustomer == null) {
			return null;
		}
		CustomerDto customerDto = new CustomerDto();
		customerDto.setId(pCustomer.getId());
		customerDto.setFirstName(pCustomer.getFirstName());
		customerDto.setLastName(pCustomer.getLastName());
		customerDto.setIsEnabled(pCustomer.getIsEnabled());
		customerDto.setBankAccountsDto(this.toListDto(pCustomer.getBankAccounts()));
		return customerDto;
	}

	public Set<BankAccountDto> toListDto(Set<BankAccount> pBankAccounts) {
		if (pBankAccounts == null) {
			return null;
		}
		Set<BankAccountDto> bankAccountsDto = new HashSet<BankAccountDto>();
		for (BankAccount bankAccount : pBankAccounts) {
			bankAccountsDto.add(this.bankAccountAssembler.toDto(bankAccount));
		}
		return bankAccountsDto;
	}

	public Customer toEntity(CustomerDto pCustomerDto) {
		if (pCustomerDto == null) {
			return null;
		}
		Customer customer = new Customer();
		customer.setId(pCustomerDto.getId());
		customer.setFirstName(pCustomerDto.getFirstName());
		customer.setLastName(pCustomerDto.getLastName());
		customer.setIsEnabled(pCustomerDto.getIsEnabled());
		customer.setBankAccounts(this.toListEntity(pCustomerDto.getBankAccountsDto()));
		return customer;
	}

	public Set<BankAccount> toListEntity(Set<BankAccountDto> pBankAccountsDto) {
		if (pBankAccountsDto == null) {
			return null;
		}
		Set<BankAccount> bankAccounts = new HashSet<BankAccount>();
		for (BankAccountDto bankAccountDto : pBankAccountsDto) {
			bankAccounts.add(this.bankAccountAssembler.toEntity(bankAccountDto));
		}
		return bankAccounts;
	}
}