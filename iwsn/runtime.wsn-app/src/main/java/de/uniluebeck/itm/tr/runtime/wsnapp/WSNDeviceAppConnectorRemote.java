package de.uniluebeck.itm.tr.runtime.wsnapp;

import de.uniluebeck.itm.gtr.common.AbstractListenable;
import de.uniluebeck.itm.gtr.common.SchedulerService;
import de.uniluebeck.itm.wsn.devicedrivers.generic.MessagePacket;


public class WSNDeviceAppConnectorRemote extends AbstractListenable<WSNDeviceAppConnector.NodeOutputListener>
		implements WSNDeviceAppConnector {

	private String nodeUrn;

	private String nodeType;

	private Integer nodeAPITimeout;

	private SchedulerService schedulerService;

	public WSNDeviceAppConnectorRemote(final String nodeUrn, final String nodeType, final Integer nodeAPITimeout,
									   final SchedulerService schedulerService) {
		this.nodeUrn = nodeUrn;
		this.nodeType = nodeType;
		this.nodeAPITimeout = nodeAPITimeout;
		this.schedulerService = schedulerService;
	}

	@Override
	public void enablePhysicalLink(final long nodeB, final Callback listener) {
		// TODO implement
	}

	@Override
	public void disablePhysicalLink(final long nodeB, final Callback listener) {
		// TODO implement
	}

	@Override
	public void enableNode(final Callback listener) {
		// TODO implement
	}

	@Override
	public void disableNode(final Callback listener) {
		// TODO implement
	}

	@Override
	public void destroyVirtualLink(final long targetNode, final Callback listener) {
		// TODO implement
	}

	@Override
	public void setVirtualLink(final long targetNode, final Callback listener) {
		// TODO implement
	}

	@Override
	public void sendMessage(final byte binaryType, final byte[] binaryMessage, final Callback listener) {
		// TODO implement
	}

	@Override
	public void resetNode(final Callback listener) {
		// TODO implement
	}

	@Override
	public void isNodeAlive(final Callback listener) {
		// TODO implement
	}

	@Override
	public void flashProgram(final WSNAppMessages.Program program, final FlashProgramCallback listener) {
		// TODO implement
	}

	private void notifyListener(MessagePacket p) {
		for (NodeOutputListener listener : listeners) {
			listener.receivedPacket(p);
		}
	}

	@Override
	public void start() throws Exception {
		// TODO implement
	}

	@Override
	public void stop() {
		// TODO implement
	}
}