package com.unmsm.ads.banking.domain.repository.abstractions;

import com.unmsm.ads.banking.domain.entities.Customer;

public interface CustomerRepositoryInterface {
	public Customer get(long pCustomerId);
}