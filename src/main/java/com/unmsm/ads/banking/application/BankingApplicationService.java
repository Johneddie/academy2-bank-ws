package com.unmsm.ads.banking.application;

import java.math.BigDecimal;

import com.unmsm.ads.banking.application.abstractions.BankingApplicationServiceInterface;
import com.unmsm.ads.banking.application.dtos.BankAccountDto;
import com.unmsm.ads.banking.domain.entities.BankAccount;
import com.unmsm.ads.banking.domain.services.abstractions.TransferDomainServiceInterface;
import com.unmsm.ads.banking.infrastructure.abstractions.UnitOfWorkInterface;

public class BankingApplicationService implements BankingApplicationServiceInterface {
	private UnitOfWorkInterface unitOfWork = null;
	private TransferDomainServiceInterface transferDomainService = null;

	public BankingApplicationService(UnitOfWorkInterface pUnitOfWork,
			TransferDomainServiceInterface pTransferDomainService) throws Exception {		
		this.unitOfWork = pUnitOfWork;	
		this.transferDomainService = pTransferDomainService;
		this.validateDependencies();
	}
	
	private void validateDependencies() {
		if (this.unitOfWork == null) {
			throw new IllegalArgumentException("unitOfWork is required");
		}		
		if (this.transferDomainService == null) {
			throw new IllegalArgumentException("transferDomainService is required");
		}
	}

	public void performTransfer(BankAccountDto pOriginBankAccountDto, BankAccountDto pDestinationBankAccountDto, BigDecimal pAmount) throws Exception {
		try {			
			this.unitOfWork.beginTransaction();
			BankAccount originAccount = this.unitOfWork.getBankAccountRepository().findByNumber(pOriginBankAccountDto.getNumber());
			BankAccount destinationAccount = this.unitOfWork.getBankAccountRepository().findByNumber(pDestinationBankAccountDto.getNumber());
			this.transferDomainService.performTransfer(originAccount, destinationAccount, pAmount);
			this.unitOfWork.save(originAccount);
			this.unitOfWork.save(destinationAccount);
			this.unitOfWork.commitTransaction();								
		} catch (Exception ex) {
			this.unitOfWork.rollbackTransaction();
			throw ex;
		}
	}
}