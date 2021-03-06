package com.unmsm.ads.banking.domain.services;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.unmsm.ads.banking.domain.entities.BankAccount;
import com.unmsm.ads.banking.domain.services.abstractions.TransferDomainServiceInterface;
import com.unmsm.ads.exceptions.AmountLessOrEqualToZeroException;
import com.unmsm.ads.exceptions.CannotDepositException;
import com.unmsm.ads.exceptions.CannotWithdrawException;
import com.unmsm.ads.exceptions.InvalidTransferBankAccountException;
import com.unmsm.ads.exceptions.SameTransferBankAccountException;

@ContextConfiguration({ "classpath:test-spring-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TransferDomainServiceTest {

	@Autowired
	private TransferDomainServiceInterface transferDomainService;

	@Before
	public void setUp() {

	}

	private BankAccount createAccount(String number, BigDecimal balance) {
		BankAccount bankAccount = new BankAccount();
		bankAccount.setBalance(balance);
		bankAccount.setNumber(number);
		return bankAccount;
	}

	@Test
	public void performTransferSuccess() throws Exception {
		BankAccount originBankAccount = createAccount("123", new BigDecimal(100));
		BankAccount destinationBankAccount = createAccount("456", new BigDecimal(10));
		transferDomainService.performTransfer(originBankAccount, destinationBankAccount, new BigDecimal(10));
		assertEquals("90", originBankAccount.getBalance().toPlainString());
		assertEquals("20", destinationBankAccount.getBalance().toPlainString());
	}

	@Test(expected = SameTransferBankAccountException.class)
	public void performTransferErrorSameAccount() throws Exception {
		BankAccount originBankAccount = createAccount("123", new BigDecimal(100));
		BankAccount destinationBankAccount = createAccount("123", new BigDecimal(10));
		transferDomainService.performTransfer(originBankAccount, destinationBankAccount, new BigDecimal(10));

	}

	@Test(expected = InvalidTransferBankAccountException.class)
	public void performTransferErrorEmptyAccount() throws Exception {
		BankAccount originBankAccount = null;
		BankAccount destinationBankAccount = null;
		transferDomainService.performTransfer(originBankAccount, destinationBankAccount, new BigDecimal(10));

	}

	@Test(expected = CannotDepositException.class)
	public void performTransferErrorLockedDestinationAccount() throws Exception {
		BankAccount originBankAccount = createAccount("123", new BigDecimal(100));
		BankAccount destinationBankAccount = createAccount("456", new BigDecimal(10));
		destinationBankAccount.lock();
		transferDomainService.performTransfer(originBankAccount, destinationBankAccount, new BigDecimal(10));
	}

	@Test(expected = CannotWithdrawException.class)
	public void performTransferErrorNoMoneyOriginAccount() throws Exception {
		BankAccount originBankAccount = createAccount("123", new BigDecimal(5));
		BankAccount destinationBankAccount = createAccount("456", new BigDecimal(10));
		transferDomainService.performTransfer(originBankAccount, destinationBankAccount, new BigDecimal(10));
	}

	@Test(expected = AmountLessOrEqualToZeroException.class)
	public void performTransferErrorNegativeTransference() throws Exception {
		BankAccount originBankAccount = createAccount("123", new BigDecimal(5));
		BankAccount destinationBankAccount = createAccount("456", new BigDecimal(10));
		transferDomainService.performTransfer(originBankAccount, destinationBankAccount, new BigDecimal(-10));
	}
}
