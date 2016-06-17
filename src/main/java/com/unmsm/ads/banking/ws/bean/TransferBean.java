package com.unmsm.ads.banking.ws.bean;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TransferBean {
	
	private String originAccount;
	private String destinationAccount;
	private BigDecimal amount;
	
	public String getOriginAccount() {
		return originAccount;
	}
	public void setOriginAccount(String originAccount) {
		this.originAccount = originAccount;
	}
	public String getDestinationAccount() {
		return destinationAccount;
	}
	public void setDestinationAccount(String destinationAccount) {
		this.destinationAccount = destinationAccount;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}
