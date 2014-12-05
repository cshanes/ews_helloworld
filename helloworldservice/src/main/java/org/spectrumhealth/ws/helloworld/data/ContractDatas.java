package org.spectrumhealth.ws.helloworld.data;

import java.util.Collection;
import java.util.HashSet;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.priorityhealth.ws.enterprise.base.util.BaseCollection;

public class ContractDatas extends BaseCollection<ContractData>
{

	public ContractDatas( Collection<ContractData> helloworldDataList )
	{
		this.list = helloworldDataList;
	}

	public ImmutableMap<ContractDataId, Collection<ContractData>> groupByContractId()
	{
		return groupBy(getContractIdFunction());
	}

	public Collection<Integer> findAllUniqueSbsbCks()
	{
		return new HashSet<Integer>(transform(getSbsbCkFunction()));
	}

	public Collection<Integer> findAllUniqueMemeCks()
	{
		return new HashSet<Integer>(transform(getMemeCkFunction()));
	}

	public Collection<String> findAllUniquePdpdIds()
	{
		return new HashSet<String>(transform(getPdpdIdFunction()));
	}

	private Function<ContractData, String> getPdpdIdFunction()
	{
		return new Function<ContractData, String>()
		{
			public String apply( ContractData helloworldData )
			{
				return helloworldData.getPdpdId();
			}
		};
	}

	/* ============================================================ */
	private Function<ContractData, Integer> getMemeCkFunction()
	{
		return new Function<ContractData, Integer>()
		{
			public Integer apply( ContractData helloworldData )
			{
				return helloworldData.getMemeCk();
			}
		};
	}

	private Function<ContractData, Integer> getSbsbCkFunction()
	{
		return new Function<ContractData, Integer>()
		{
			public Integer apply( ContractData helloworldData )
			{
				return helloworldData.getSbsbCk();
			}
		};
	}

	private Function<ContractData, ContractDataId> getContractIdFunction()
	{
		return new Function<ContractData, ContractDataId>()
		{
			public ContractDataId apply( ContractData helloworldData )
			{
				return new ContractDataId(helloworldData.getSbsbId(), helloworldData.getMemeSfx());
			}
		};
	}

}
