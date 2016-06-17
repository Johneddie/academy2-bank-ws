package com.unmsm.ads.banking.infrastructure.abstractions;

import org.hibernate.Session;

import com.unmsm.ads.banking.domain.repository.abstractions.BankAccountRepositoryInterface;
import com.unmsm.ads.banking.domain.repository.abstractions.CustomerRepositoryInterface;

public interface UnitOfWorkInterface {
	public Session getSession();
	public CustomerRepositoryInterface getCustomerRepository();
	public BankAccountRepositoryInterface getBankAccountRepository();
	public <T> void save(T entity);
	public void beginTransaction();
	public void commitTransaction();
	public void rollbackTransaction();
}