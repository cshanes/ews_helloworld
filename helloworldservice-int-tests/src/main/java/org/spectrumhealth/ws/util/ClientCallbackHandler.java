package org.spectrumhealth.ws.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

public class ClientCallbackHandler implements CallbackHandler
{
	private Map<String, String> passwords = new HashMap<String, String>();

	public ClientCallbackHandler()
	{
		String password = "qwerty";
		passwords.put("phService", password);
		passwords.put("mystique.internal.priority-health.com (phin.internal.priority-health.com)", password);
	}

	public ClientCallbackHandler( Map<String, String> config )
	{
		this.passwords.putAll(config);
	}

	public void handle( Callback[] callbacks ) throws IOException, UnsupportedCallbackException
	{
		for (int i = 0; i < callbacks.length; i++)
		{
			WSPasswordCallback pc = (WSPasswordCallback) callbacks[i];

			String pass = passwords.get(pc.getIdentifier());
			if (pass != null)
			{
				pc.setPassword(pass);
				return;
			}
		}
	}

}
