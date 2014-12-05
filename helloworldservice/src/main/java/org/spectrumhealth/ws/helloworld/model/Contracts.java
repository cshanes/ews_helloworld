package org.spectrumhealth.ws.helloworld.model;

import java.util.Collection;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import org.spectrumhealth.ws.helloworld.model.Contract;
import com.priorityhealth.ws.enterprise.base.util.BaseCollection;

public class Contracts extends BaseCollection<Contract>
{
	public Contracts( Collection<Contract> helloworlds )
	{
		this.list = helloworlds;
	}

	public ImmutableMap<String, Collection<Contract>> groupByEnterpriseId()
	{
		return groupBy(getEnterpriseId());
	}

	/* ============================================================ */

	private Function<Contract, String> getEnterpriseId()
	{
		return new Function<Contract, String>()
		{

			@Override
			public String apply( Contract input )
			{
				return input.getMember().getEnterpriseId();
			}
		};
	}
}
