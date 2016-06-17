package com.unmsm.ads.banking.domain.repository.abstractions;

import com.unmsm.ads.banking.domain.entities.BankAccount;

public interface BankAccountRepositoryInterface {
	BankAccount findByNumber(String pAccountNumber) throws Exception;
}