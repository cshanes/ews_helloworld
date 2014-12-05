package org.spectrumhealth.ws.helloworld.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

@XmlType(namespace = "http://ws.priorityhealth.com/helloworld")
public class Member implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String enterpriseId;

	public String getEnterpriseId()
	{
		return enterpriseId;
	}

	public void setEnterpriseId( String enterpriseId )
	{
		this.enterpriseId = enterpriseId;
	}

}
