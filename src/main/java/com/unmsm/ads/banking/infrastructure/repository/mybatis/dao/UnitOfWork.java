package com.unmsm.ads.banking.infrastructure.repository.mybatis.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.hibernate.Session;

import com.unmsm.ads.banking.domain.entities.BankAccount;
import com.unmsm.ads.banking.domain.entities.Customer;
import com.unmsm.ads.banking.domain.repository.abstractions.BankAccountRepositoryInterface;
import com.unmsm.ads.banking.domain.repository.abstractions.CustomerRepositoryInterface;
import com.unmsm.ads.banking.infrastructure.abstractions.UnitOfWorkInterface;
import com.unmsm.ads.banking.infrastructure.repository.mybatis.MyBatisConnectionFactory;

public class UnitOfWork implements UnitOfWorkInterface {
	
	private SqlSessionFactory sessionFactory;
	private SqlSession session;
	private CustomerDAO customerDAO;
	private BankAccountDAO bankAccountDAO;

	public UnitOfWork() {
		this.sessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
		this.customerDAO = new CustomerDAO(sessionFactory);
		this.bankAccountDAO = new BankAccountDAO(sessionFactory);		
	}
	
	public SqlSession getSqlSession() {
		return this.session;
	}
	
	public Session getSession() {
		return null;
	}
	
	public CustomerRepositoryInterface getCustomerRepository() {
		return customerDAO;
	}

	public BankAccountRepositoryInterface getBankAccountRepository() {
		return bankAccountDAO;
	}
	
	public <T> void save(T entity) {
		if(entity.getClass().equals(BankAccount.class))
			bankAccountDAO.update((BankAccount)entity, session);
		else if (entity.getClass().equals(Customer.class))
			customerDAO.update((Customer)entity, session);
	}
	
	public void beginTransaction() {	
		session = sessionFactory.openSession();
	}
	
	public void commitTransaction() {		
        session.commit();
        session.close();
		
	}
	
	public void rollbackTransaction() {		
		session.rollback();
		session.close();
	}
}