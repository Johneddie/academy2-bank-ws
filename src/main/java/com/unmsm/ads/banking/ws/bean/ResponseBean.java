package com.unmsm.ads.banking.ws.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResponseBean {
	
	private String status;
	private String message;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
