package org.spectrumhealth.ws.helloworld.service;

import org.apache.log4j.Logger;

import org.spectrumhealth.ws.helloworld.controller.ContractController;
import org.spectrumhealth.ws.helloworld.dao.JdbcContractDao;
import org.spectrumhealth.ws.helloworld.model.Contract;

import org.spectrumhealth.ws.helloworld.controller.ContractController;
import org.spectrumhealth.ws.helloworld.dao.JdbcContractDao;
import org.spectrumhealth.ws.helloworld.model.Contract;
import org.spectrumhealth.ws.helloworld.service.ContractService;
import org.spectrumhealth.ws.helloworld.service.ContractServiceDriver;
import com.priorityhealth.db.datasource.PhDataSource;

/**
 * This class is useful for testing purposes.  You have the ability to run the main method
 * and exercise the same operations that are going to be made available to the webservice, except
 * you don't have to deploy it.
 * 
 * Instead you run this just as you would a batch application calling the operations / methods that you want 
 * to invoke with the values that you want to invoke it with.
 * @author jbossdev
 *
 */
public class ContractServiceDriver
{
	
	private static final Logger logger = Logger.getLogger(ContractServiceDriver.class);

	public static void main( String[] args )
	{
		ContractServiceDriver sampleServiceDriver = new ContractServiceDriver();
		sampleServiceDriver.getSingleContract();
	}

	private void getSingleContract()
	{
		ContractService contractService = getContractService();
		String sampleId = "123456789-00";
		Contract contract = contractService.getContractByContractExtId(sampleId);
		System.out.println("contract: " + contract);
	}

	private ContractService getContractService()
	{
		ContractService sampleService = new ContractService();
		ContractController sampleController = new ContractController();

		JdbcContractDao sampleDao = new JdbcContractDao();
		sampleDao.setDataSource(new PhDataSource("phdb", "ews_user"));
		sampleController.setContractDao(sampleDao);
		sampleService.setContractController(sampleController);
		return sampleService;
	}
	
	
}
