package com.unmsm.ads.banking.infrastructure.repository.hibernate;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unmsm.ads.banking.domain.entities.Customer;
import com.unmsm.ads.banking.domain.repository.abstractions.CustomerRepositoryInterface;
import com.unmsm.ads.banking.infrastructure.abstractions.UnitOfWorkInterface;

@Repository
public class CustomerRepository implements CustomerRepositoryInterface {
	private UnitOfWorkInterface unitOfWork;

	@Autowired
	public void setUnitOfWork(UnitOfWorkInterface unitOfWork) {
		this.unitOfWork = unitOfWork;
	}

	public Customer get(long pCustomerId) {
		Query<Customer> query = this.unitOfWork.getSession().createQuery("FROM Customer c WHERE c.id = :id",
				Customer.class);
		query.setParameter("id", pCustomerId);
		return query.getSingleResult();
	}
}