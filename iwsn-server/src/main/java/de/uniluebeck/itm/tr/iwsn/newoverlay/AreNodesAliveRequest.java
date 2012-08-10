package de.uniluebeck.itm.tr.iwsn.newoverlay;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import de.uniluebeck.itm.tr.iwsn.NodeUrn;

public class AreNodesAliveRequest extends Request {

	@Inject
	AreNodesAliveRequest(final RequestIdProvider requestIdProvider,
						 @Assisted final ImmutableSet<NodeUrn> nodeUrns) {

		super(requestIdProvider, nodeUrns);
	}

	public ImmutableSet<NodeUrn> getNodeUrns() {
		return nodeUrns;
	}
}
