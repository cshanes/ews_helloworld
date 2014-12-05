package org.spectrumhealth.ws.helloworld.service;

import java.util.Collection;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElementWrapper;

import org.spectrumhealth.ws.helloworld.controller.ContractController;
import org.spectrumhealth.ws.helloworld.model.Contract;

@WebService(targetNamespace = "http://ws.priorityhealth.com/helloworld")
public class ContractService
{
	@Inject
	ContractController helloworldController;

	public @WebResult(name = "helloworld")
	Contract getContractByContractExtId( @WebParam(name = "helloworldExtId") String helloworldId )
	{
		return helloworldController.getContractByContractExtId(helloworldId);
	}

	@WebMethod(exclude = true)
	public void setContractController( ContractController helloworldController )
	{
		this.helloworldController = helloworldController;
	}

}
