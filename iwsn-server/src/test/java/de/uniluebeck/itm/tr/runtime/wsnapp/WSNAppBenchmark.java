package de.uniluebeck.itm.tr.runtime.wsnapp;

import com.google.common.collect.ImmutableSet;
import com.google.common.eventbus.Subscribe;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import com.google.inject.Guice;
import com.google.inject.Injector;
import de.uniluebeck.itm.tr.iwsn.NodeUrn;
import de.uniluebeck.itm.tr.iwsn.newoverlay.*;
import de.uniluebeck.itm.tr.iwsn.overlay.TestbedRuntime;
import de.uniluebeck.itm.tr.iwsn.overlay.TestbedRuntimeModule;
import de.uniluebeck.itm.tr.iwsn.overlay.naming.NamingEntry;
import de.uniluebeck.itm.tr.iwsn.overlay.naming.NamingInterface;
import de.uniluebeck.itm.tr.util.Logging;
import de.uniluebeck.itm.tr.util.StringUtils;
import de.uniluebeck.itm.tr.util.TimeDiff;
import de.uniluebeck.itm.wsn.drivers.factories.DeviceFactory;
import de.uniluebeck.itm.wsn.drivers.factories.DeviceFactoryModule;
import de.uniluebeck.itm.wsn.drivers.factories.DeviceType;
import org.apache.log4j.Level;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import static com.google.common.collect.Lists.newLinkedList;
import static de.uniluebeck.itm.tr.runtime.wsnapp.BenchmarkHelper.*;
import static org.jboss.netty.buffer.ChannelBuffers.wrappedBuffer;

public class WSNAppBenchmark {

	private static final Logger log = LoggerFactory.getLogger(WSNAppBenchmark.class);

	static {
		Logging.setLoggingDefaults(Level.TRACE);
	}

	private static class FutureMessageReceiver {

		private final BenchmarkHelper helper;

		private final ImmutableSet<NodeUrn> nodeUrns;

		private final int messageNumber;

		private final OverlayEventBus overlayEventBus;

		private final SettableFuture<byte[]> future;

		private final TimeDiff timeDiff = new TimeDiff();

		private long duration = Long.MAX_VALUE;

		private final RequestFactory requestFactory;

		private FutureMessageReceiver(final ImmutableSet<NodeUrn> nodeUrns,
									  final int messageNumber,
									  final OverlayEventBus overlayEventBus,
									  final RequestFactory requestFactory) {

			this.nodeUrns = nodeUrns;
			this.messageNumber = messageNumber;
			this.overlayEventBus = overlayEventBus;
			this.requestFactory = requestFactory;

			this.future = SettableFuture.create();
			this.helper = new BenchmarkHelper();
		}

		@Subscribe
		public synchronized void onMessageUpstreamRequest(final MessageUpstreamRequest request) {

			final byte[] bytes = request.getMessageBytes();

			if (log.isTraceEnabled()) {
				log.trace("Decoding received bytes: {}", StringUtils.toHexString(bytes));
			}

			final ChannelBuffer decodedMessage = helper.decode(wrappedBuffer(bytes));

			if (log.isTraceEnabled()) {
				byte[] decodedBytes = new byte[decodedMessage.readableBytes()];
				decodedMessage.getBytes(0, decodedBytes);
				log.trace("{} packet decoding complete: {}", this, StringUtils.toHexString(decodedBytes));
			}

			if (decodedMessage.getInt(1) == messageNumber) {
				log.debug("Received response for messageNumber={}", messageNumber);
				duration = timeDiff.ms();
				overlayEventBus.unregister(this);
				future.set(bytes);
			}
		}

		public synchronized void start() {

			final ChannelBuffer buffer = ChannelBuffers.buffer(5);
			buffer.writeByte(10 & 0xFF);
			buffer.writeInt(messageNumber);

			timeDiff.touch();
			overlayEventBus.register(this);

			final MessageDownstreamRequest request = requestFactory.createMessageDownstreamRequest(
					nodeUrns,
					toByteArray(helper.encode(buffer))
			);

			request.getFuture().addListener(new Runnable() {
				@Override
				public void run() {
					try {
						request.getFuture().get();
					} catch (Exception e) {
						future.setException(e);
					}
				}
			}, MoreExecutors.sameThreadExecutor()
			);

			overlayEventBus.post(request);
		}

		public synchronized long getDuration() {
			return duration;
		}

		public synchronized SettableFuture<byte[]> getFuture() {
			return future;
		}

		@Override
		public synchronized String toString() {
			return "FutureMessageReceiver{" +
					"messageNumber=" + messageNumber +
					'}';
		}

		public synchronized int getMessageNumber() {
			return messageNumber;
		}
	}

	private static final NodeUrn URN_NODE_0 = new NodeUrn("urn:local:0x0000");

	private static final NodeUrn URN_NODE_1 = new NodeUrn("urn:local:0x0001");

	private static final NodeUrn URN_NODE_2 = new NodeUrn("urn:local:0x0002");

	private static final NodeUrn URN_NODE_3 = new NodeUrn("urn:local:0x0003");

	private static final NodeUrn URN_NODE_4 = new NodeUrn("urn:local:0x0004");

	private static final NodeUrn URN_NODE_5 = new NodeUrn("urn:local:0x0005");

	private static final NodeUrn URN_NODE_6 = new NodeUrn("urn:local:0x0006");

	private static final NodeUrn URN_NODE_7 = new NodeUrn("urn:local:0x0007");

	private static final NodeUrn URN_NODE_8 = new NodeUrn("urn:local:0x0008");

	private static final NodeUrn URN_NODE_9 = new NodeUrn("urn:local:0x0009");

	private static final ImmutableSet<NodeUrn> NODES_1 = ImmutableSet.of(URN_NODE_0);

	private static final ImmutableSet<NodeUrn> NODES_2 = ImmutableSet.of(URN_NODE_0, URN_NODE_1);

	private static final ImmutableSet<NodeUrn> NODES_3 = ImmutableSet.of(URN_NODE_0, URN_NODE_1, URN_NODE_2);

	private static final ImmutableSet<NodeUrn> NODES_4 =
			ImmutableSet.of(URN_NODE_0, URN_NODE_1, URN_NODE_2, URN_NODE_3);

	private static final ImmutableSet<NodeUrn> NODES_5 =
			ImmutableSet.of(URN_NODE_0, URN_NODE_1, URN_NODE_2, URN_NODE_3, URN_NODE_4);

	@SuppressWarnings("unchecked")
	private static final ImmutableSet<NodeUrn> NODES_6 =
			ImmutableSet.of(URN_NODE_0, URN_NODE_1, URN_NODE_2, URN_NODE_3, URN_NODE_4, URN_NODE_5);

	private static final ImmutableSet<NodeUrn> NODES_7 =
			ImmutableSet.of(URN_NODE_0, URN_NODE_1, URN_NODE_2, URN_NODE_3, URN_NODE_4, URN_NODE_5, URN_NODE_6);

	private static final ImmutableSet<NodeUrn> NODES_8 =
			ImmutableSet.of(URN_NODE_0, URN_NODE_1, URN_NODE_2, URN_NODE_3, URN_NODE_4, URN_NODE_5, URN_NODE_6,
					URN_NODE_7
			);

	private static final ImmutableSet<NodeUrn> NODES_9 =
			ImmutableSet.of(URN_NODE_0, URN_NODE_1, URN_NODE_2, URN_NODE_3, URN_NODE_4, URN_NODE_5, URN_NODE_6,
					URN_NODE_7, URN_NODE_8
			);

	private static final ImmutableSet<NodeUrn> NODES_10 =
			ImmutableSet.of(URN_NODE_0, URN_NODE_1, URN_NODE_2, URN_NODE_3, URN_NODE_4, URN_NODE_5, URN_NODE_6,
					URN_NODE_7, URN_NODE_8, URN_NODE_9
			);

	private static final String URN_PORTAL = "urn:local:portal";

	private static final String TCP_PORTAL = "localhost:2222";

	private static final String TCP_GATEWAY = "localhost:1111";

	private static final ImmutableSet<String> reservedNodes = ImmutableSet.of(
			URN_NODE_0.toString(),
			URN_NODE_1.toString(),
			URN_NODE_2.toString(),
			URN_NODE_3.toString(),
			URN_NODE_4.toString(),
			URN_NODE_5.toString(),
			URN_NODE_6.toString(),
			URN_NODE_7.toString(),
			URN_NODE_8.toString(),
			URN_NODE_9.toString()
	);

	private static final int RUNS = 100;

	private WSNApp wsnApp;

	private TestbedRuntime gatewayTR;

	private TestbedRuntime portalTR;

	private Map<String, WSNDeviceApp> wsnDeviceApps;

	private RequestFactory portalRequestFactory;

	private OverlayEventBus portalOverlayEventBus;

	private Overlay portalOverlay;

	@Before
	public void setUp() throws Exception {

		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(20);

		Injector gatewayTRInjector = Guice.createInjector(
				new TestbedRuntimeModule(scheduler, scheduler, scheduler),
				new WSNDeviceAppModule(),
				new DeviceFactoryModule()
		);

		gatewayTR = gatewayTRInjector.getInstance(TestbedRuntime.class);

		gatewayTR.getLocalNodeNameManager()
				.addLocalNodeName(URN_NODE_0.toString())
				.addLocalNodeName(URN_NODE_1.toString())
				.addLocalNodeName(URN_NODE_2.toString())
				.addLocalNodeName(URN_NODE_3.toString())
				.addLocalNodeName(URN_NODE_4.toString())
				.addLocalNodeName(URN_NODE_5.toString())
				.addLocalNodeName(URN_NODE_6.toString())
				.addLocalNodeName(URN_NODE_7.toString())
				.addLocalNodeName(URN_NODE_8.toString())
				.addLocalNodeName(URN_NODE_9.toString());

		gatewayTR.getRoutingTableService()
				.setNextHop(URN_PORTAL, URN_PORTAL)
				.setNextHop(URN_NODE_0.toString(), URN_NODE_0.toString())
				.setNextHop(URN_NODE_1.toString(), URN_NODE_1.toString())
				.setNextHop(URN_NODE_2.toString(), URN_NODE_2.toString())
				.setNextHop(URN_NODE_3.toString(), URN_NODE_3.toString())
				.setNextHop(URN_NODE_4.toString(), URN_NODE_4.toString())
				.setNextHop(URN_NODE_5.toString(), URN_NODE_5.toString())
				.setNextHop(URN_NODE_6.toString(), URN_NODE_6.toString())
				.setNextHop(URN_NODE_7.toString(), URN_NODE_7.toString())
				.setNextHop(URN_NODE_8.toString(), URN_NODE_8.toString())
				.setNextHop(URN_NODE_9.toString(), URN_NODE_9.toString());

		gatewayTR.getNamingService()
				.addEntry(new NamingEntry(URN_PORTAL, new NamingInterface("tcp", TCP_PORTAL), 1))
				.addEntry(new NamingEntry(URN_NODE_0.toString(), new NamingInterface("tcp", TCP_GATEWAY), 1))
				.addEntry(new NamingEntry(URN_NODE_1.toString(), new NamingInterface("tcp", TCP_GATEWAY), 1))
				.addEntry(new NamingEntry(URN_NODE_2.toString(), new NamingInterface("tcp", TCP_GATEWAY), 1))
				.addEntry(new NamingEntry(URN_NODE_3.toString(), new NamingInterface("tcp", TCP_GATEWAY), 1))
				.addEntry(new NamingEntry(URN_NODE_4.toString(), new NamingInterface("tcp", TCP_GATEWAY), 1))
				.addEntry(new NamingEntry(URN_NODE_5.toString(), new NamingInterface("tcp", TCP_GATEWAY), 1))
				.addEntry(new NamingEntry(URN_NODE_6.toString(), new NamingInterface("tcp", TCP_GATEWAY), 1))
				.addEntry(new NamingEntry(URN_NODE_7.toString(), new NamingInterface("tcp", TCP_GATEWAY), 1))
				.addEntry(new NamingEntry(URN_NODE_8.toString(), new NamingInterface("tcp", TCP_GATEWAY), 1))
				.addEntry(new NamingEntry(URN_NODE_9.toString(), new NamingInterface("tcp", TCP_GATEWAY), 1));

		gatewayTR.getMessageServerService().addMessageServer("tcp", TCP_GATEWAY);

		Injector portalTRInjector = Guice.createInjector(new TestbedRuntimeModule(scheduler, scheduler, scheduler));

		portalTR = portalTRInjector.getInstance(TestbedRuntime.class);

		portalTR.getLocalNodeNameManager()
				.addLocalNodeName(URN_PORTAL);

		portalTR.getRoutingTableService()
				.setNextHop(URN_PORTAL, URN_PORTAL)
				.setNextHop(URN_NODE_0.toString(), URN_NODE_0.toString())
				.setNextHop(URN_NODE_1.toString(), URN_NODE_1.toString())
				.setNextHop(URN_NODE_2.toString(), URN_NODE_2.toString())
				.setNextHop(URN_NODE_3.toString(), URN_NODE_3.toString())
				.setNextHop(URN_NODE_4.toString(), URN_NODE_4.toString())
				.setNextHop(URN_NODE_5.toString(), URN_NODE_5.toString())
				.setNextHop(URN_NODE_6.toString(), URN_NODE_6.toString())
				.setNextHop(URN_NODE_7.toString(), URN_NODE_7.toString())
				.setNextHop(URN_NODE_8.toString(), URN_NODE_8.toString())
				.setNextHop(URN_NODE_9.toString(), URN_NODE_9.toString());

		portalTR.getNamingService()
				.addEntry(new NamingEntry(URN_PORTAL, new NamingInterface("tcp", TCP_PORTAL), 1))
				.addEntry(new NamingEntry(URN_NODE_0.toString(), new NamingInterface("tcp", TCP_GATEWAY), 1))
				.addEntry(new NamingEntry(URN_NODE_1.toString(), new NamingInterface("tcp", TCP_GATEWAY), 1))
				.addEntry(new NamingEntry(URN_NODE_2.toString(), new NamingInterface("tcp", TCP_GATEWAY), 1))
				.addEntry(new NamingEntry(URN_NODE_3.toString(), new NamingInterface("tcp", TCP_GATEWAY), 1))
				.addEntry(new NamingEntry(URN_NODE_4.toString(), new NamingInterface("tcp", TCP_GATEWAY), 1))
				.addEntry(new NamingEntry(URN_NODE_5.toString(), new NamingInterface("tcp", TCP_GATEWAY), 1))
				.addEntry(new NamingEntry(URN_NODE_6.toString(), new NamingInterface("tcp", TCP_GATEWAY), 1))
				.addEntry(new NamingEntry(URN_NODE_7.toString(), new NamingInterface("tcp", TCP_GATEWAY), 1))
				.addEntry(new NamingEntry(URN_NODE_8.toString(), new NamingInterface("tcp", TCP_GATEWAY), 1))
				.addEntry(new NamingEntry(URN_NODE_9.toString(), new NamingInterface("tcp", TCP_GATEWAY), 1));

		portalTR.getMessageServerService().addMessageServer("tcp", TCP_PORTAL);

		// start both nodes' stack
		gatewayTR.startAndWait();
		portalTR.startAndWait();

		createWSNDeviceApps(gatewayTRInjector);
		startWSNDeviceApps();

		wsnApp = Guice.createInjector(new WSNAppModule())
				.getInstance(WSNAppFactory.class)
				.create(portalTR, reservedNodes);

		wsnApp.startAndWait();

		final Injector portalOverlayInjector = Guice.createInjector(new WSNAppOverlayModule(wsnApp));
		portalRequestFactory = portalOverlayInjector.getInstance(RequestFactory.class);
		portalOverlay = portalOverlayInjector.getInstance(Overlay.class);
		portalOverlay.startAndWait();
		portalOverlayEventBus = portalOverlay.getEventBus();
	}

	@After
	public void tearDown() throws Exception {

		portalOverlay.stopAndWait();
		wsnApp.stopAndWait();
		stopWSNDeviceApps();

		gatewayTR.stopAndWait();
		portalTR.stopAndWait();
	}

	@Test
	public void testSerial1Node() throws Exception {
		printDurations(executeSerial(NODES_1));
	}

	@Test
	public void testSerial2Nodes() throws Exception {
		printDurations(executeSerial(NODES_2));
	}

	@Test
	public void testSerial3Nodes() throws Exception {
		printDurations(executeSerial(NODES_3));
	}

	@Test
	public void testSerial4Nodes() throws Exception {
		printDurations(executeSerial(NODES_4));
	}

	@Test
	public void testSerial5Nodes() throws Exception {
		printDurations(executeSerial(NODES_5));
	}

	@Test
	public void testSerial1to10Nodes() throws Exception {
		printDurations(executeSerial(NODES_1));
		printDurations(executeSerial(NODES_2));
		printDurations(executeSerial(NODES_3));
		printDurations(executeSerial(NODES_4));
		printDurations(executeSerial(NODES_5));
		printDurations(executeSerial(NODES_6));
		printDurations(executeSerial(NODES_7));
		printDurations(executeSerial(NODES_8));
		printDurations(executeSerial(NODES_9));
		printDurations(executeSerial(NODES_10));
	}

	@Test
	public void testParallelSendToOneDevice() throws Exception {

		final List<Float> durations = newLinkedList();
		final List<FutureMessageReceiver> receivers = newLinkedList();

		// fork
		FutureMessageReceiver receiver;
		for (int messageNumber = 0; messageNumber < RUNS; messageNumber++) {
			receiver = new FutureMessageReceiver(NODES_1, messageNumber, portalOverlayEventBus, portalRequestFactory);
			receiver.start();
			receivers.add(receiver);
		}

		// join
		for (FutureMessageReceiver messageReceiver : receivers) {
			try {
				messageReceiver.getFuture().get(10, TimeUnit.SECONDS);
			} catch (TimeoutException e) {
				log.warn(
						"TimeoutException for messageNumber={} (hex: {})",
						messageReceiver.getMessageNumber(),
						StringUtils.toHexString(messageReceiver.getMessageNumber())
				);
			}
			durations.add((float) messageReceiver.getDuration());
		}

		printDurations(durations);
	}

	@Test
	public void testParallelWithMultipleDevices() throws Exception {

		final List<Float> durations = newLinkedList();

		for (int messageNumber = 0; messageNumber < RUNS; messageNumber++) {

			final FutureMessageReceiver receiver;
			receiver = new FutureMessageReceiver(NODES_10, messageNumber, portalOverlayEventBus, portalRequestFactory);
			receiver.start();
			try {
				receiver.getFuture().get(5, TimeUnit.SECONDS);
			} catch (TimeoutException e) {
				log.warn(
						"TimeoutException for messageNumber={} (hex: {})",
						receiver.getMessageNumber(),
						StringUtils.toHexString(receiver.getMessageNumber())
				);
			}
			durations.add((float) receiver.getDuration());
		}

		printDurations(durations);
	}

	private void createWSNDeviceApps(final Injector injector) {
		wsnDeviceApps = new HashMap<String, WSNDeviceApp>();
		for (String nodeUrn : reservedNodes) {

			final Map<String, String> nodeConfiguration = new HashMap<String, String>();
			//nodeConfiguration.put("UART_LATENCY", "10");
			nodeConfiguration.put("ECHO", "true");

			final WSNDeviceAppConfiguration configuration = new WSNDeviceAppConfiguration(nodeUrn, URN_PORTAL, null);
			final WSNDeviceAppConnectorConfiguration connectorConfiguration = new WSNDeviceAppConnectorConfiguration(
					nodeUrn,
					DeviceType.MOCK.toString(),
					null, null, nodeConfiguration, null, null, null, null, null, null
			);

			final WSNDeviceAppGuiceFactory factory = injector.getInstance(WSNDeviceAppGuiceFactory.class);
			final DeviceFactory deviceFactory = injector.getInstance(DeviceFactory.class);
			final WSNDeviceApp wsnDeviceApp = factory.create(
					gatewayTR,
					deviceFactory,
					configuration,
					connectorConfiguration
			);
			wsnDeviceApps.put(nodeUrn, wsnDeviceApp);
		}
	}

	private void startWSNDeviceApps() {
		for (Map.Entry<String, WSNDeviceApp> entry : wsnDeviceApps.entrySet()) {
			entry.getValue().startAndWait();
		}
	}

	private void stopWSNDeviceApps() {
		for (Map.Entry<String, WSNDeviceApp> entry : wsnDeviceApps.entrySet()) {
			entry.getValue().stopAndWait();
		}
	}

	private void printDurations(final List<Float> durations) {
		System.out.println("--------------------- Durations ---------------------");
		System.out.println("Min:       " + MIN.apply(durations) + " ms");
		System.out.println("Max:       " + MAX.apply(durations) + " ms");
		System.out.println("Mean:      " + MEAN.apply(durations) + " ms");
		System.out.println("Durations: " + Arrays.toString(durations.toArray()));
		System.out.println("-----------------------------------------------------");
	}

	private List<Float> executeSerial(final ImmutableSet<NodeUrn> nodeUrns)
			throws InterruptedException, TimeoutException, ExecutionException {

		final List<Float> durations = newLinkedList();

		FutureMessageReceiver receiver;
		for (int messageNumber = 0; messageNumber < RUNS; messageNumber++) {

			receiver = new FutureMessageReceiver(nodeUrns, messageNumber, portalOverlayEventBus, portalRequestFactory);
			receiver.start();
			receiver.getFuture().get(5, TimeUnit.SECONDS);
			durations.add((float) receiver.getDuration());
		}

		return durations;
	}
}
