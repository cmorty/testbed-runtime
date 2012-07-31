package de.uniluebeck.itm.tr.iwsn.newoverlay;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import de.uniluebeck.itm.tr.iwsn.NodeUrn;

public class DevicesAttachedEvent {

	private final ImmutableSet<NodeUrn> nodeUrns;

	public DevicesAttachedEvent(final ImmutableSet<NodeUrn> nodeUrns) {
		this.nodeUrns = nodeUrns;
	}

	public ImmutableSet<NodeUrn> getNodeUrns() {
		return nodeUrns;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("nodeUrns", nodeUrns)
				.toString();
	}
}
