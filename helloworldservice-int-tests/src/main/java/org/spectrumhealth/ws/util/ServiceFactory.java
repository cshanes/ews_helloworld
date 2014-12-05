package org.spectrumhealth.ws.util;

import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.ws.security.SecurityConstants;

public class ServiceFactory
{

	public static Contract120PortType getService()
	{
		Contract120PortType service = new Contract120().getContract120HttpSoap11Endpoint();

		addInterceptors(service);
		addSecurityProperties(service);

		return service;
	}

	private static void addSecurityProperties( Object service )
	{
		Map<String, Object> ctx = ((BindingProvider) service).getRequestContext();
		ctx.put(SecurityConstants.CALLBACK_HANDLER, new ClientCallbackHandler());
		ctx.put(SecurityConstants.ENCRYPT_PROPERTIES, "client.properties");
		ctx.put(SecurityConstants.ENCRYPT_USERNAME, "phservice");
		ctx.put(SecurityConstants.SIGNATURE_PROPERTIES, "client.properties");
		ctx.put(SecurityConstants.SIGNATURE_USERNAME,
				"mystique.internal.priority-health.com (phin.internal.priority-health.com)");

	}

	private static void addInterceptors( Object service )
	{
		Client client = ClientProxy.getClient(service);
		client.getInInterceptors().add(new LoggingInInterceptor());
		client.getOutInterceptors().add(new LoggingOutInterceptor());
	}
}
