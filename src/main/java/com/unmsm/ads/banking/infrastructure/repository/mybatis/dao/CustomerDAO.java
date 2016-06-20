package com.unmsm.ads.banking.infrastructure.repository.mybatis.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.unmsm.ads.banking.domain.entities.Customer;
import com.unmsm.ads.banking.domain.repository.abstractions.CustomerRepositoryInterface;

public class CustomerDAO implements CustomerRepositoryInterface{
 
    private SqlSessionFactory sqlSessionFactory;
 
    public CustomerDAO(SqlSessionFactory sqlSessionFactory){
    	this.sqlSessionFactory = sqlSessionFactory;
    }
 
	@Override
	public Customer get(long pCustomerId) {
		Customer customer = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
        	customer = session.selectOne("Customer.get", pCustomerId);
        } finally {
            session.close();
        }
        return customer;
	}
	
  	public void update(Customer customer, SqlSession session){
        session.update("Customer.update", customer);
    }
}