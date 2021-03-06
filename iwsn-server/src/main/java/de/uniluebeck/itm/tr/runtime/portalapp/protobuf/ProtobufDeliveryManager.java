package de.uniluebeck.itm.tr.runtime.portalapp.protobuf;

import com.google.common.collect.Lists;
import com.google.protobuf.ByteString;
import de.uniluebeck.itm.tr.iwsn.common.DeliveryManager;
import eu.wisebed.api.common.Message;
import eu.wisebed.api.controller.RequestStatus;
import eu.wisebed.api.controller.Status;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.ChannelGroupFuture;
import org.jboss.netty.channel.group.ChannelGroupFutureListener;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;


public class ProtobufDeliveryManager extends DeliveryManager {

	private static final Logger log = LoggerFactory.getLogger(ProtobufDeliveryManager.class);

	private static final DatatypeFactory datatypeFactory;

	static {
		try {
			datatypeFactory = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			throw new RuntimeException(e);
		}
	}

	private final ChannelGroup channels = new DefaultChannelGroup();

	private volatile int currentMessageDeliveryQueueSize = 0;

	private volatile long lastBackendMessage = System.currentTimeMillis();

	private ChannelGroupFutureListener messageDeliveryListener = new ChannelGroupFutureListener() {
		@Override
		public void operationComplete(final ChannelGroupFuture future) throws Exception {
			currentMessageDeliveryQueueSize--;
		}
	};

	public ProtobufDeliveryManager(final @Nullable Integer maximumDeliveryQueueSize) {
		super(maximumDeliveryQueueSize);
	}

	@Override
	public void experimentEnded() {
		if (channels.size() > 0) {
			channels.close();
		}
		super.experimentEnded();
	}

	@Override
	public void receive(final List<Message> messages) {

		if (channels.size() > 0) {

			for (final Message message : messages) {

				final long BACKEND_MESSAGE_INTERVAL = 1000;

				// only send message to client if delivery queue is smaller than maximum
				if (currentMessageDeliveryQueueSize < maximumDeliveryQueueSize) {

					// count the messages that still have to be delivered
					currentMessageDeliveryQueueSize++;

					// write message to clients
					// messageDeliveryListener will decrease delivery queue size counter
					channels.write(convert(message)).addListener(messageDeliveryListener);

				}

				// inform the user of dropped messages every BACKEND_MESSAGE_INTERVAL milliseconds
				else if (System.currentTimeMillis() - lastBackendMessage > BACKEND_MESSAGE_INTERVAL) {

					log.warn("Dropped one or more messages. Informing protobuf controllers.");

					WisebedMessages.Message.Backend.Builder backendBuilder =
							WisebedMessages.Message.Backend.newBuilder()
									.setText("Your experiment is generating too many messages to be delivered. "
											+ "Therefore the backend drops messages. "
											+ "Please make sure the message rate is lowered."
									);

					WisebedMessages.Message backendMessage = WisebedMessages.Message.newBuilder()
							.setType(WisebedMessages.Message.Type.BACKEND)
							.setBackend(backendBuilder)
							.build();

					channels.write(backendMessage).awaitUninterruptibly();
					lastBackendMessage = System.currentTimeMillis();

				}
			}
		}

		super.receive(messages);

	}

	@Override
	public void receive(final Message... messages) {
		receive(Lists.newArrayList(messages));
	}

	@Override
	public void receiveFailureStatusMessages(final List<String> nodeUrns, final String requestId, final Exception e,
											 final int statusValue) {

		if (channels.size() > 0) {

			RequestStatus requestStatus = new RequestStatus();
			requestStatus.setRequestId(requestId);

			for (String nodeId : nodeUrns) {
				Status status = new Status();
				status.setNodeId(nodeId);
				status.setValue(statusValue);
				status.setMsg(e.getMessage());
				requestStatus.getStatus().add(status);
			}

			channels.write(convert(requestStatus));

		}

		super.receiveFailureStatusMessages(nodeUrns, requestId, e, statusValue);	// TODO implement
	}

	@Override
	public void receiveNotification(final List<String> notifications) {

		if (channels.size() > 0) {
			for (String notification : notifications) {
				channels.write(convert(notification));
			}
		}

		super.receiveNotification(notifications);
	}

	@Override
	public void receiveNotification(final String... notifications) {
		receiveNotification(Lists.newArrayList(notifications));
	}

	@Override
	public void receiveStatus(final List<RequestStatus> statuses) {

		if (channels.size() > 0) {
			for (RequestStatus status : statuses) {
				channels.write(convert(status));
			}
		}

		super.receiveStatus(statuses);
	}

	@Override
	public void receiveStatus(final RequestStatus... statuses) {
		receiveStatus(Lists.newArrayList(statuses));
	}

	@Override
	public void receiveUnknownNodeUrnRequestStatus(final Set<String> nodeUrns, final String msg,
												   final String requestId) {
		if (channels.size() > 0) {

			WisebedMessages.RequestStatus.Builder requestStatusBuilder = WisebedMessages.RequestStatus.newBuilder()
					.setRequestId(requestId);

			for (String nodeUrn : nodeUrns) {
				WisebedMessages.RequestStatus.Status.Builder statusBuilder =
						WisebedMessages.RequestStatus.Status.newBuilder()
								.setNodeUrn(nodeUrn)
								.setMessage(msg)
								.setValue(-1);
				requestStatusBuilder.addStatus(statusBuilder);
			}

			WisebedMessages.Envelope envelope = WisebedMessages.Envelope.newBuilder()
					.setBodyType(WisebedMessages.Envelope.BodyType.REQUEST_STATUS)
					.setRequestStatus(requestStatusBuilder)
					.build();

			channels.write(envelope);
		}
		super.receiveUnknownNodeUrnRequestStatus(nodeUrns, msg, requestId);
	}

	public void addChannel(Channel channel) {
		channels.add(channel);
	}

	public void removeChannel(Channel channel) {
		channels.remove(channel);
	}

	private WisebedMessages.Envelope convert(final String notification) {

		WisebedMessages.Message.Backend.Builder backendBuilder = WisebedMessages.Message.Backend.newBuilder()
				.setText(notification);

		WisebedMessages.Message.Builder backendMessageBuilder = WisebedMessages.Message.newBuilder()
				.setType(WisebedMessages.Message.Type.BACKEND)
				.setTimestamp(datatypeFactory.newXMLGregorianCalendar(new GregorianCalendar()).toXMLFormat())
				.setBackend(backendBuilder);

		return WisebedMessages.Envelope.newBuilder()
				.setBodyType(WisebedMessages.Envelope.BodyType.MESSAGE)
				.setMessage(backendMessageBuilder)
				.build();
	}

	private WisebedMessages.Envelope convert(Message message) {

		WisebedMessages.Message.Builder messageBuilder = WisebedMessages.Message.newBuilder()
				.setTimestamp(message.getTimestamp().toXMLFormat());

		WisebedMessages.Message.NodeBinary.Builder nodeBinaryBuilder = WisebedMessages.Message.NodeBinary.newBuilder()
				.setSourceNodeUrn(message.getSourceNodeId())
				.setData(ByteString.copyFrom(message.getBinaryData()));

		messageBuilder.setNodeBinary(nodeBinaryBuilder);
		messageBuilder.setType(WisebedMessages.Message.Type.NODE_BINARY);

		return WisebedMessages.Envelope.newBuilder()
				.setBodyType(WisebedMessages.Envelope.BodyType.MESSAGE)
				.setMessage(messageBuilder)
				.build();
	}

	private WisebedMessages.Envelope convert(RequestStatus requestStatus) {

		WisebedMessages.RequestStatus.Builder requestStatusBuilder = WisebedMessages.RequestStatus.newBuilder()
				.setRequestId(requestStatus.getRequestId());

		for (Status status : requestStatus.getStatus()) {
			requestStatusBuilder.addStatus(WisebedMessages.RequestStatus.Status.newBuilder()
					.setValue(status.getValue())
					.setMessage(status.getMsg())
					.setNodeUrn(status.getNodeId())
			);
		}

		return WisebedMessages.Envelope.newBuilder()
				.setBodyType(WisebedMessages.Envelope.BodyType.REQUEST_STATUS)
				.setRequestStatus(requestStatusBuilder)
				.build();
	}
}
