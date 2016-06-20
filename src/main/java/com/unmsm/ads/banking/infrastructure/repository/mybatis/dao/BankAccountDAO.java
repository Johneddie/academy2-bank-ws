package com.unmsm.ads.banking.infrastructure.repository.mybatis.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.unmsm.ads.banking.domain.entities.BankAccount;
import com.unmsm.ads.banking.domain.repository.abstractions.BankAccountRepositoryInterface;
 
public class BankAccountDAO implements BankAccountRepositoryInterface{
 
    private SqlSessionFactory sqlSessionFactory;
 
    public BankAccountDAO(SqlSessionFactory sqlSessionFactory){
    	this.sqlSessionFactory = sqlSessionFactory;
    }
     
	@Override
	public BankAccount findByNumber(String pAccountNumber) throws Exception {
		BankAccount bankAccount = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
        	bankAccount = session.selectOne("BankAccount.findByNumber", pAccountNumber);
        } finally {
            session.close();
        }
        return bankAccount;
	}
	

  	public void update(BankAccount bankAccount, SqlSession session){
      session.update("BankAccount.update", bankAccount);
    }
	
	
}