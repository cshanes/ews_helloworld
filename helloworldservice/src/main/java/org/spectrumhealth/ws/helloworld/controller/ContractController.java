package org.spectrumhealth.ws.helloworld.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import org.spectrumhealth.ws.helloworld.builder.ContractBuilder;
import org.spectrumhealth.ws.helloworld.dao.JdbcContractDao;
import org.spectrumhealth.ws.helloworld.data.ContractData;
import org.spectrumhealth.ws.helloworld.data.ContractDatas;
import org.spectrumhealth.ws.helloworld.model.Contract;
import com.priorityhealth.ws.enterprise.base.model.DateRange;
import com.priorityhealth.ws.enterprise.util.ContractIdUtility;

public class ContractController
{
	private static final Logger logger = Logger.getLogger(ContractController.class);

	@Inject
	private JdbcContractDao sampleDao;
	
	public Contract getContractByContractExtId( String sampleId )
	{
		DateRange dateRange = new DateRange();
		ContractIdUtility contractId = ContractIdUtility.formatAndParse(sampleId);
		List<ContractData> contractDatas = sampleDao.findContractByContractExtId(contractId, dateRange);
		
		ContractBuilder contractBuilder = new ContractBuilder();
		List<Contract> contracts = contractBuilder.buildContracts(new ContractDatas(contractDatas));
		return contracts.get(0);
	}
	
	public void setContractDao( JdbcContractDao sampleDao )
	{
		this.sampleDao = sampleDao;
	}

}
