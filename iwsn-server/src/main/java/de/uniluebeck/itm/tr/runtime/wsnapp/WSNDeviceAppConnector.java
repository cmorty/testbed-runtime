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

package de.uniluebeck.itm.tr.runtime.wsnapp;

import com.google.common.collect.Multimap;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.Service;
import de.uniluebeck.itm.tr.util.Listenable;
import de.uniluebeck.itm.tr.util.ProgressListenableFuture;
import de.uniluebeck.itm.tr.util.Tuple;

import javax.annotation.Nullable;
import java.util.List;

public interface WSNDeviceAppConnector extends Listenable<WSNDeviceAppConnector.NodeOutputListener>, Service {

	public static class Response {

		public final byte code;

		@Nullable
		public final byte[] payload;

		public Response(final byte code, @Nullable final byte[] responsePayload) {
			this.code = code;
			this.payload = responsePayload;
		}
	}

	public static interface NodeOutputListener {

		void receivedPacket(byte[] bytes);

		void receiveNotification(String notification);

	}

	ListenableFuture<Response> destroyVirtualLink(long targetNode);

	ListenableFuture<Response> disableNode();

	ListenableFuture<Response> disablePhysicalLink(long nodeB);

	ListenableFuture<Response> enableNode();

	ListenableFuture<Response> enablePhysicalLink(long targetNode);

	ProgressListenableFuture<Response> flashProgram(byte[] image);

	ListenableFuture<Response> isNodeAlive();

	ListenableFuture<Response> isNodeConnected();

	ListenableFuture<Response> resetNode();

	ListenableFuture<Response> sendMessage(byte[] binaryMessage);

	ListenableFuture<Response> setVirtualLink(long targetNode);

	ListenableFuture<Response> setDefaultChannelPipeline();

	ListenableFuture<Response> setChannelPipeline(List<Tuple<String, Multimap<String, String>>> configurations);
}
