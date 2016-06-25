package com.unmsm.ads.banking.ws.rest;
 
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.unmsm.ads.banking.application.BankingApplicationService;
import com.unmsm.ads.banking.application.dtos.BankAccountDto;
import com.unmsm.ads.banking.ws.bean.ResponseBean;
import com.unmsm.ads.banking.ws.bean.TransferBean;

import static com.unmsm.ads.banking.ws.util.Constantes.*;

//comentario1 

@Path("/api/bank/service")
public class BankingRestService {
	private BankingApplicationService bankingApplicationService;
	
	@PUT
	@Path("performTransfer")
	@Produces({"application/json"})  
	public Response performTransfer(TransferBean transferBean) {
		ResponseBean response = new ResponseBean(); 
		
		try{
			BankAccountDto originAccountDto = new BankAccountDto();
			originAccountDto.setNumber(transferBean.getOriginAccount());
			BankAccountDto destinationAccountDto = new BankAccountDto();
			destinationAccountDto.setNumber(transferBean.getDestinationAccount());
			
			bankingApplicationService.performTransfer(originAccountDto, destinationAccountDto, transferBean.getAmount());
			
			response.setStatus(COD_RESPONSE_SUCCESS);
			response.setMessage(DES_RESPONSE_SUCCESS);
			
		}catch(Exception e){
			response.setStatus(COD_RESPONSE_ERROR);
			response.setMessage(e.getMessage());
			e.printStackTrace();
		}
 
		return Response.status(200).entity(response).build();
 
	}
	
	@GET
	@Path("/{param}")
	public Response getPing(@PathParam("param") String msg) {
		String output = "John say2 : " + msg;
		return Response.status(200).entity(output).build();
	}
	
	public BankingApplicationService getBankingApplicationService() {
		return bankingApplicationService;
	}
	public void setBankingApplicationService(BankingApplicationService bankingApplicationService) {
		this.bankingApplicationService = bankingApplicationService;
	}
	
}