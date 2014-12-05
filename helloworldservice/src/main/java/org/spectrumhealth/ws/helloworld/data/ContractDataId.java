package org.spectrumhealth.ws.helloworld.data;

public class ContractDataId
{

	private String sbsbId;
	private Integer memeSfx;

	public ContractDataId( String sbsbId, Integer memeSfx )
	{
		this.sbsbId = sbsbId;
		this.memeSfx = memeSfx;
	}

	public String getSbsbId()
	{
		return sbsbId;
	}

	public Integer getMemeSfx()
	{
		return memeSfx;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memeSfx == null) ? 0 : memeSfx.hashCode());
		result = prime * result + ((sbsbId == null) ? 0 : sbsbId.hashCode());
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContractDataId other = (ContractDataId) obj;
		if (memeSfx == null)
		{
			if (other.memeSfx != null)
				return false;
		}
		else if (!memeSfx.equals(other.memeSfx))
			return false;
		if (sbsbId == null)
		{
			if (other.sbsbId != null)
				return false;
		}
		else if (!sbsbId.equals(other.sbsbId))
			return false;
		return true;
	}

}
