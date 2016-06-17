package com.unmsm.ads.banking.user_interface;

import java.math.BigDecimal;
import java.util.logging.Level;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.unmsm.ads.banking.application.abstractions.BankingApplicationServiceInterface;
import com.unmsm.ads.banking.application.dtos.BankAccountDto;

public class Main {

	private BankingApplicationServiceInterface bankingApplicationService = null;

	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
			new String[] { "applicationContext.xml", "applicationContext-hibernate.xml" });;

	public Main() {
		try {
			this.injectDependencies();
			this.performTransfer();
			System.out.println("Done!");
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		} finally {
		}
	}

	public static void main(String[] args) {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		new Main();
	}

	public void performTransfer() throws Exception {
		BankAccountDto originAccountDto = new BankAccountDto();
		originAccountDto.setNumber("123456789");
		BankAccountDto destinationAccountDto = new BankAccountDto();
		destinationAccountDto.setNumber("123456788");
		BigDecimal amount = new BigDecimal(40);
		bankingApplicationService.performTransfer(originAccountDto, destinationAccountDto, amount);
	}

	public void injectDependencies() throws Exception {
		ctx.getEnvironment().setActiveProfiles("main", "hibernate");
		ctx.refresh();
		bankingApplicationService = (BankingApplicationServiceInterface) ctx.getBean("bankingApplicationService");
	}
}