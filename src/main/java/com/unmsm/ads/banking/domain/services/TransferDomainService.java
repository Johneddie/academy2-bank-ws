package com.unmsm.ads.banking.domain.services;

import java.math.BigDecimal;

import com.unmsm.ads.banking.domain.entities.BankAccount;
import com.unmsm.ads.banking.domain.services.abstractions.TransferDomainServiceInterface;
import com.unmsm.ads.exceptions.InvalidTransferBankAccountException;
import com.unmsm.ads.exceptions.SameTransferBankAccountException;

public class TransferDomainService implements TransferDomainServiceInterface {

	public TransferDomainService() {
	}

	public void performTransfer(BankAccount pOriginAccount, BankAccount pDestinationAccount, BigDecimal pAmount)
			throws Exception {
		this.validateData(pOriginAccount, pDestinationAccount, pAmount);
		pOriginAccount.withdrawMoney(pAmount);
		pDestinationAccount.depositMoney(pAmount);
	}

	private void validateData(BankAccount pOriginAccount, BankAccount pDestinationAccount, BigDecimal pAmount)
			throws InvalidTransferBankAccountException, SameTransferBankAccountException {
		if (pOriginAccount == null || pDestinationAccount == null) {
			throw new InvalidTransferBankAccountException();
		}
		if (pOriginAccount.getNumber().equals(pDestinationAccount.getNumber())) {
			throw new SameTransferBankAccountException();
		}
	}
}