package com.unmsm.ads.banking.domain.services.abstractions;

import java.math.BigDecimal;

import com.unmsm.ads.banking.domain.entities.BankAccount;

public interface TransferDomainServiceInterface {
	void performTransfer(BankAccount pOriginAccount, BankAccount pDestinationAccount, BigDecimal pAmount) throws Exception;
}