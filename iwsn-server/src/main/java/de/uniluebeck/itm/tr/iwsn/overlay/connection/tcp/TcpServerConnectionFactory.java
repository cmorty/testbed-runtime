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

package de.uniluebeck.itm.tr.iwsn.overlay.connection.tcp;

import com.google.common.eventbus.EventBus;
import de.uniluebeck.itm.tr.iwsn.overlay.connection.ConnectionInvalidAddressException;
import de.uniluebeck.itm.tr.iwsn.overlay.connection.ServerConnection;
import de.uniluebeck.itm.tr.iwsn.overlay.connection.ServerConnectionFactory;

/**
 * User: bimschas
 * Date: 14.02.2010
 * Time: 22:32:10
 */
public class TcpServerConnectionFactory implements ServerConnectionFactory {

	@Override
	public ServerConnection create(String address, EventBus eventBus) throws ConnectionInvalidAddressException {
		try {

			String[] split = address.split(":");
			// TODO search how it's possible to just bind to one interface
			String hostName = "0.0.0.0";
			int port = Integer.parseInt(split[1]);

			return new TcpServerConnection(hostName, port, eventBus);

		} catch (NumberFormatException e) {
			throw new ConnectionInvalidAddressException(address, e);
		}
	}

	@Override
	public String getType() {
		return TcpConstants.TYPE;
	}

}
