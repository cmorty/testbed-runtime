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

package de.uniluebeck.itm.tr.iwsn.overlay.messaging;

import com.google.protobuf.ByteString;
import com.google.protobuf.UninitializedMessageException;
import de.uniluebeck.itm.tr.iwsn.overlay.connection.Connection;
import de.uniluebeck.itm.tr.iwsn.overlay.connection.ServerConnection;
import de.uniluebeck.itm.tr.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Random;

public class MessageTools {

	protected static final Logger log = LoggerFactory.getLogger(MessageTools.class);

	public static void sendMessage(Messages.Msg msg, OutputStream out) throws Exception {
		msg.writeDelimitedTo(out);
		out.flush();
	}

	public interface MessageCallback {

		public void receivedMessage(ServerConnection serverConnection, Connection conn, Messages.Msg msg);

	}

	public static class MessageReader implements Runnable {

		private static final Logger log = LoggerFactory.getLogger(MessageReader.class);

		private MessageCallback messageCallback;

		private ServerConnection serverConnection;

		private Connection connection;

		public MessageReader(ServerConnection serverConnection, Connection connection,
							 MessageCallback messageCallback) {
			this.serverConnection = serverConnection;
			this.connection = connection;
			this.messageCallback = messageCallback;
		}

		public void run() {
			log.trace("Starting MessageReader for remote host {} on server connection {}", connection, serverConnection
			);
			readAndPostMessages();
		}

		private void readAndPostMessages() {

			try {

				InputStream in = connection.getInputStream();

				while (!Thread.interrupted()) {

					Messages.Msg.Builder builder = Messages.Msg.newBuilder();
					builder.mergeDelimitedFrom(in);
					try {

						Messages.Msg msg = builder.build();
						messageCallback.receivedMessage(serverConnection, connection, msg);

					} catch (UninitializedMessageException e) {

						log.debug(
								"Received uninitialized message. That usually happens when the connection was broken."
						);
						connection.disconnect();
						return;
					}

				}

			} catch (final IOException error) {
				log.trace("Error on rx (Retry in 1s): " + error);
			}

		}

	}

	public static Serializable getPayloadSerializable(Messages.Msg msg) {
		return getSerializable(msg.getPayload().toByteArray());
	}

	public static Serializable getSerializable(byte[] messagePayload) {
		Serializable inObject;
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(messagePayload);
			ObjectInputStream inputStream = new ObjectInputStream(in);
			inObject = (Serializable) inputStream.readObject();
			inputStream.close();
			in.close();
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
		return inObject;
	}

	private static Messages.Msg.Builder getBuilder(String from, String to, String msgType, Serializable payload,
												   int priority) {

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			ObjectOutputStream outputStream = new ObjectOutputStream(out);
			outputStream.writeObject(payload);
			outputStream.close();

			out.close();

		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}

		return Messages.Msg.newBuilder()
				.setFrom(from)
				.setTo(to)
				.setMsgType(msgType)
				.setPayload(ByteString.copyFrom(out.toByteArray()))
				.setPriority(priority);

	}

	private static Messages.Msg.Builder getBuilder(String from, String to, String msgType, byte[] payload, int priority) {

		return Messages.Msg.newBuilder()
				.setFrom(from)
				.setTo(to)
				.setMsgType(msgType)
				.setPayload(ByteString.copyFrom(payload))
				.setPriority(priority);
	}

	public static Messages.Msg buildMessage(String from, String to, String app, byte[] payload, int priority) {
		return getBuilder(from, to, app, payload, priority).build();
	}

	public static Messages.Msg buildMessage(String from, String to, String app, Serializable payload, int priority) {
		return getBuilder(from, to, app, payload, priority).build();
	}

	private static final Random random = new Random();

	public static Messages.Msg buildReliableTransportMessage(String from, String to, String app, byte[] payload,
															 int priority) {
		return getBuilder(from, to, app, payload, priority)
				.setReplyWith(from + ":" + random.nextLong())
				.build();
	}

	public static Messages.Msg buildReliableTransportMessage(String from, String to, String app, Serializable payload,
															 int priority) {
		return getBuilder(from, to, app, payload, priority)
				.setReplyWith(from + ":" + random.nextLong())
				.build();
	}

	public static Messages.Msg buildReply(Messages.Msg msg, String msgType, Serializable payload) {

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			ObjectOutputStream outputStream = new ObjectOutputStream(out);
			outputStream.writeObject(payload);
			outputStream.close();

			out.close();

		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}

		return buildReply(msg, msgType, out.toByteArray());

	}

	private static Messages.Msg.Builder buildReplyBuilder(Messages.Msg msg, String msgType, byte[] payload) {
		// TODO sensible valid until time

		final Messages.Msg.Builder builder = Messages.Msg.newBuilder()
				.setFrom(msg.getTo())
				.setTo(msg.getFrom())
				.setPayload(ByteString.copyFrom(payload))
				.setMsgType(msgType)
				.setPriority(msg.getPriority());

		if (msg.hasReplyWith()) {
			builder.setReplyTo(msg.getReplyWith());
		}

		return builder;
	}

	public static Messages.Msg buildReply(Messages.Msg msg, String msgType, byte[] payload) {
		return buildReplyBuilder(msg, msgType, payload).build();
	}

	public static String toString(final Messages.Msg msg) {
		final StringBuilder builder = new StringBuilder();
		builder.append("{from=\"");
		builder.append(msg.getFrom());
		builder.append("\", to=\"");
		builder.append(msg.getTo());
		builder.append("\", type=\"");
		builder.append(msg.getMsgType());
		builder.append("\", priority=");
		builder.append(msg.getPriority());
		builder.append(", payload=\"");
		builder.append(StringUtils.toHexString(msg.getPayload().toByteArray()));
		builder.append("\", replyTo=\"");
		builder.append(msg.getReplyTo());
		builder.append("\", replyWith=\"");
		builder.append(msg.getReplyWith());
		builder.append("\"}");
		return builder.toString();
	}

}

