package org.spectrumhealth.ws.helloworld.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlType(namespace = "http://ws.priorityhealth.com/helloworld")
public class Contract implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String enterpriseId;
	private Date originalEffectiveDate;
	private Member member;

	public String getEnterpriseId()
	{
		return enterpriseId;
	}

	public void setEnterpriseId( String enterpriseId )
	{
		this.enterpriseId = enterpriseId;
	}

	@XmlSchemaType(name = "date")
	public Date getOriginalEffectiveDate()
	{
		return originalEffectiveDate;
	}

	public void setOriginalEffectiveDate( Date originalEffectiveDate )
	{
		this.originalEffectiveDate = originalEffectiveDate;
	}

	public Member getMember()
	{
		return member;
	}

	public void setMember( Member member )
	{
		this.member = member;
	}

}
