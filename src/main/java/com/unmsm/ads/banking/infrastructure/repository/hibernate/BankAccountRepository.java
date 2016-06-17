package com.unmsm.ads.banking.infrastructure.repository.hibernate;

import org.hibernate.LockOptions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unmsm.ads.banking.domain.entities.BankAccount;
import com.unmsm.ads.banking.domain.repository.abstractions.BankAccountRepositoryInterface;
import com.unmsm.ads.banking.infrastructure.abstractions.UnitOfWorkInterface;

@Repository
public class BankAccountRepository implements BankAccountRepositoryInterface {
	@Autowired
	private UnitOfWorkInterface unitOfWork;

	public void setUnitOfWork(UnitOfWorkInterface unitOfWork) {
		this.unitOfWork = unitOfWork;
	}

	public BankAccount findByNumber(String pAccountNumber) throws Exception {
		Query<BankAccount> query = this.unitOfWork.getSession()
				.createQuery("FROM BankAccount b WHERE b.number = :number", BankAccount.class);
		query.setParameter("number", pAccountNumber);
		query.setLockOptions(LockOptions.UPGRADE);
		return query.getSingleResult();
	}
}