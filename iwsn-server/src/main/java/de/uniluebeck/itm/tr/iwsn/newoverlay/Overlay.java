package de.uniluebeck.itm.tr.iwsn.newoverlay;

import com.google.common.eventbus.EventBus;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.TimeUnit;

public interface Overlay {

	EventBus getEventBus();

	ListenableFuture<Response> send(Request request, int timeout, TimeUnit timeUnit);

	ListenableFuture<ProgressResponse> send(ProgressRequest progressRequest, int timeout, TimeUnit timeUnit);

}
