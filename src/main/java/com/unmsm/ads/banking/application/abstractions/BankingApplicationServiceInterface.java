package com.unmsm.ads.banking.application.abstractions;

import java.math.BigDecimal;

import com.unmsm.ads.banking.application.dtos.BankAccountDto;

public interface BankingApplicationServiceInterface {
	public void performTransfer(BankAccountDto pOriginBankAccountDto, BankAccountDto pDestinationBankAccountDto, BigDecimal pAmount) throws Exception;
}