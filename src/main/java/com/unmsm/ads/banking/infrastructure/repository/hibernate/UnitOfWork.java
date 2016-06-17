package com.unmsm.ads.banking.infrastructure.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unmsm.ads.banking.domain.repository.abstractions.BankAccountRepositoryInterface;
import com.unmsm.ads.banking.domain.repository.abstractions.CustomerRepositoryInterface;
import com.unmsm.ads.banking.infrastructure.abstractions.UnitOfWorkInterface;

@Service
public class UnitOfWork implements UnitOfWorkInterface {
	private Transaction transaction;
	private SessionFactory sessionFactory;
	private Session session;

	@Autowired
	private CustomerRepositoryInterface customerRepository;

	@Autowired
	private BankAccountRepositoryInterface bankAccountRepository;

	@Autowired
	public UnitOfWork(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.session = sessionFactory.openSession();
		// this.customerRepository = new CustomerRepository(this);
		// this.bankAccountRepository = new BankAccountRepository(this);
	}

	public Session getSession() {
		return this.session;
	}

	public CustomerRepositoryInterface getCustomerRepository() {
		return customerRepository;
	}

	public BankAccountRepositoryInterface getBankAccountRepository() {
		return bankAccountRepository;
	}

	public <T> void save(T entity) {
		this.session.save(entity);
	}

	public void beginTransaction() {
		this.transaction = session.beginTransaction();
	}

	public void commitTransaction() {
		this.transaction.commit();
	}

	public void rollbackTransaction() {
		this.transaction.rollback();
	}
}