package org.spectrumhealth.ws.helloworld.model.constants;

public enum ContractType
{

	ALL("ALL"), DENTAL("D"), MEDICAL("M"), Rx("P"), VISION("V");

	private String val;

	ContractType( String val )
	{
		this.val = val;
	}

	public String getValue()
	{
		return this.val;
	}
}
