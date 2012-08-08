/**********************************************************************************************************************
 * Copyright (c) 2010, Institute of Telematics, University of Luebeck                                                 *
 * All rights reserved.                                                                                               *
 *                                                                                                                    *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the   *
 * following conditions are met:                                                                                      *
 *                                                                                                                    *
 * - Redistributions of source code must retain the above copyright notice, this list of conditions and the following *
 *   disclaimer.                                                                                                      *
 * - Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the        *
 *   following disclaimer in the documentation and/or other materials provided with the distribution.                 *
 * - Neither the name of the University of Luebeck nor the names of its contributors may be used to endorse or promote*
 *   products derived from this software without specific prior written permission.                                   *
 *                                                                                                                    *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, *
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE      *
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,         *
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE *
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF    *
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY   *
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.                                *
 **********************************************************************************************************************/

package de.uniluebeck.itm.tr.snaa.shibboleth;

import com.google.inject.Injector;
import eu.wisebed.shibboauth.IShibbolethAuthenticator;
import eu.wisebed.shibboauth.SSAKSerialization;
import eu.wisebed.testbed.api.snaa.authorization.IUserAuthorization;
import eu.wisebed.api.common.SecretAuthenticationKey;
import eu.wisebed.api.common.UsernameUrnPrefixPair;
import eu.wisebed.api.snaa.*;
import eu.wisebed.api.snaa.IsValidResponse.ValidationResult;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.cookie.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.*;

import static de.uniluebeck.itm.tr.snaa.SNAAHelper.*;

@WebService(endpointInterface = "eu.wisebed.api.snaa.SNAA", portName = "SNAAPort",
		serviceName = "SNAAService", targetNamespace = "http://testbed.wisebed.eu/api/snaa/v1/")
public class ShibbolethSNAAImpl implements SNAA {

	private static final Logger log = LoggerFactory.getLogger(ShibbolethSNAAImpl.class);

	protected Set<String> urnPrefixes;

	protected String secretAuthenticationKeyUrl;

	protected IUserAuthorization authorization;

	private Injector injector;

	private ShibbolethProxy proxy;

	public ShibbolethSNAAImpl(Set<String> urnPrefixes, String secretAuthenticationKeyUrl,
							  IUserAuthorization authorization, Injector injector, ShibbolethProxy proxy) {
		this.urnPrefixes = new HashSet<String>(urnPrefixes);
		this.secretAuthenticationKeyUrl = secretAuthenticationKeyUrl;
		this.authorization = authorization;
		this.injector = injector;
		this.proxy = proxy;
	}

	@Override
	public List<SecretAuthenticationKey> authenticate(
			@WebParam(name = "authenticationData", targetNamespace = "") List<AuthenticationTriple> authenticationData)
			throws AuthenticationExceptionException, SNAAExceptionException {

		HashSet<SecretAuthenticationKey> keys = new HashSet<SecretAuthenticationKey>();
		log.debug("Starting for " + authenticationData.size() + " urns.");

		assertMinAuthenticationCount(authenticationData, 1);
		assertAllUrnPrefixesServed(urnPrefixes, authenticationData);

		for (AuthenticationTriple triple : authenticationData) {
			IShibbolethAuthenticator sa = injector.getInstance(IShibbolethAuthenticator.class);
			String urn = triple.getUrnPrefix();

			try {

				sa.setUsernameAtIdpDomain(triple.getUsername());
				sa.setPassword(triple.getPassword());
				sa.setUrl(secretAuthenticationKeyUrl);
				if (proxy != null) {
					sa.setProxy(proxy.getProxyHost(), proxy.getProxyPort());
				}

				try {
					sa.authenticate();
				} catch (ClientProtocolException e) {
					// catch this exception as it usually means that the shibboleth server had a problem
					// wait a short amount of time and try again
					Thread.sleep(1000);
					try {
						sa.authenticate();
					} catch (Exception e1) {
						throw createSNAAException("Authentication failed: the authentication system has problems "
										+ "contacting the Shibboleth server. Please try again later!");
					}
				}

				if (sa.isAuthenticated()) {
					SecretAuthenticationKey secretAuthKey = new SecretAuthenticationKey();
					secretAuthKey.setSecretAuthenticationKey(
							SSAKSerialization.serialize(sa.getCookieStore().getCookies())
					);
					secretAuthKey.setUrnPrefix(triple.getUrnPrefix());
					secretAuthKey.setUsername(triple.getUsername());
					keys.add(secretAuthKey);
				} else {
					throw createAuthenticationException("Authentication for urn[" + urn + "] and user["
							+ triple.getUsername() + " failed."
					);
				}

			} catch (Exception e) {
				throw createSNAAException("Authentication failed :" + e);
			}

		}

		log.debug("Done, returning " + keys.size() + " secret authentication key(s).");
		return new ArrayList<SecretAuthenticationKey>(keys);

	}

	@Override
	@Deprecated
	public AuthorizationResponse isAuthorized(
	        @WebParam(name = "usernames", targetNamespace = "")
	        List<UsernameUrnPrefixPair> usernames,
	        @WebParam(name = "action", targetNamespace = "")
	        Action action,
	        @WebParam(name = "nodeUrns", targetNamespace = "")
	        String nodeUrns)
	        throws SNAAExceptionException {

		AuthorizationResponse authorized = new AuthorizationResponse();
		authorized.setAuthorized(true);
		authorized.setMessage("ShibbolethSNAAImpl is used for authentication only and always return 'true'");
		authorized.setNodeUrn(nodeUrns);
		return authorized;

	}

	@Override
	public ValidationResult isValid(
	        @WebParam(name = "secretAuthenticationKey", targetNamespace = "")
	        SecretAuthenticationKey secretAuthenticationKey)
	        throws SNAAExceptionException {
		
		List<SecretAuthenticationKey> saks = new LinkedList<SecretAuthenticationKey>();
		saks.add(secretAuthenticationKey);
	
		// Check if we serve all URNs
		assertAllSAKUrnPrefixesServed(urnPrefixes, saks);
		
		// TODO Auto-generated method stub ShibbolethSNAAImpl#isValid(SecretAuthenticationKey)
		return null;
	}

}
