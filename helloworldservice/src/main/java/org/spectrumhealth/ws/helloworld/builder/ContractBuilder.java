package org.spectrumhealth.ws.helloworld.builder;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import org.spectrumhealth.ws.helloworld.data.ContractData;
import org.spectrumhealth.ws.helloworld.model.Member;

import org.spectrumhealth.ws.helloworld.data.ContractData;
import org.spectrumhealth.ws.helloworld.data.ContractDatas;
import org.spectrumhealth.ws.helloworld.model.Contract;
import org.spectrumhealth.ws.helloworld.model.Member;

public class ContractBuilder
{
	public List<Contract> buildContracts( ContractDatas sampleDatas )
	{
		List<Contract> list = Lists.newArrayList();
		
		for (ContractData currContractData : sampleDatas.asList())
		{
			list.add(toContract(currContractData));
		}
		
		return list;
	}

	private Contract toContract( ContractData currContractData )
	{
		Contract contract = new Contract();
		
		contract.setOriginalEffectiveDate(new Date());
		contract.setEnterpriseId(currContractData.getMemeCk() + "");
		contract.setMember(toMember(currContractData));
		
		return contract;
	}

	private Member toMember( ContractData currContractData )
	{
		Member member = new Member();
		member.setEnterpriseId(currContractData.getMemeCk() + "");
		return member;
	}
}
