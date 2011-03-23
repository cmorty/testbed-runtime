package de.itm.uniluebeck.tr.wiseml;

import com.google.common.collect.Lists;
import eu.wisebed.ns.wiseml._1.Capability;
import eu.wisebed.ns.wiseml._1.NodeProperties;
import eu.wisebed.ns.wiseml._1.Setup;
import eu.wisebed.ns.wiseml._1.Setup.Node;
import eu.wisebed.ns.wiseml._1.Wiseml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXB;
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class WiseMLHelper {

	/**
	 * The logger instance
	 */
	private static final Logger log = LoggerFactory.getLogger(WiseMLHelper.class);

	private WiseMLHelper() {
		// forbid access
	}

	/**
	 * De-serializes the WiseML document and returns the object representation of the parsed document.
	 *
	 * @param serializedWiseML the serialized WiseML document
	 *
	 * @return the object representation of the parsed document
	 */
	@SuppressWarnings("unused")
	public static Wiseml deserialize(final String serializedWiseML) {
		return JAXB.unmarshal(new StringReader(serializedWiseML), Wiseml.class);
	}

	/**
	 * Parses the serialized WiseML document and returns the {@link Node} instance with URN {@code nodeID}.
	 *
	 * @param serializedWiseML the serialized WiseML document
	 * @param nodeID		   the URN of the node to return
	 *
	 * @return the {@link Node} instance with URN {@code nodeID}
	 */
	@SuppressWarnings("unused")
	public static Node getNode(final String serializedWiseML, final String nodeID) {
		Wiseml wiseml = deserialize(serializedWiseML);

		for (Setup.Node node : wiseml.getSetup().getNode()) {
			if (node.getId().equals(nodeID)) {
				return node;
			}
		}

		return null;
	}

	/**
	 * Reads out all nodes that are contained in the setup-part of the document.
	 *
	 * @param wiseml a serialized WiseML document
	 *
	 * @return a List of {@link Node} instances
	 */
	@SuppressWarnings("unused")
	public static List<Node> getNodes(final Wiseml wiseml) {
		return getNodes(wiseml, (String[]) null);
	}

	/**
	 * Parses the WiseML document that is passed in as String in {@code serializedWiseML} and reads out all nodes that are
	 * contained in the setup-part of the document.
	 *
	 * @param serializedWiseML a serialized WiseML document
	 *
	 * @return a List of {@link Node} instances
	 */
	@SuppressWarnings("unused")
	public static List<Node> getNodes(final String serializedWiseML) {
		return getNodes(serializedWiseML, (String[]) null);
	}

	/**
	 * Parses the WiseML document that is passed in as String in {@code serializedWiseML} and reads out all nodes that are
	 * contained in the setup-part of the document.
	 *
	 * @param serializedWiseML a serialized WiseML document
	 * @param types			node types to include, e.g. "isense", "telosb" will include all iSense and all TelosB nodes
	 *                         contained in the WiseML document
	 *
	 * @return a List of {@link Node} instances
	 */
	@SuppressWarnings("unused")
	public static List<Node> getNodes(final String serializedWiseML, final String... types) {
		return getNodes(deserialize(serializedWiseML), types);
	}

	/**
	 * Reads out all nodes that are contained in the setup-part of the document.
	 *
	 * @param wiseml the WiseML document
	 * @param types  node types to include, e.g. "isense", "telosb" will include all iSense and all TelosB nodes contained
	 *               in the WiseML document
	 *
	 * @return a List of {@link Node} instances
	 */
	@SuppressWarnings("unused")
	public static List<Node> getNodes(final Wiseml wiseml, final String... types) {

		List<String> nodeTypes = types == null ? null : Lists.newArrayList(types);
		List<Node> nodes = Lists.newArrayList();

		for (Node node : wiseml.getSetup().getNode()) {
			if (types == null || types.length == 0) {
				nodes.add(node);
			} else {
				// if "containsIgnoreCase"...
				for (String nodeType : nodeTypes) {
					if (nodeType.equalsIgnoreCase(node.getNodeType())) {
						nodes.add(node);
					}
				}
			}
		}

		return nodes;
	}

	/**
	 * Parses the WiseML document that is passed in as String in {@code wiseML} and reads out all node URNs that are
	 * contained in the setup-part of the document.
	 *
	 * @param serializedWiseML a serialized WiseML document
	 *
	 * @return a List of node URNs
	 */
	@SuppressWarnings("unused")
	public static List<String> getNodeUrns(final String serializedWiseML) {
		return getNodeUrns(serializedWiseML, (String[]) null);
	}

	/**
	 * Parses the WiseML document that is passed in as String in {@code serializedWiseML} and reads out all node URNs that
	 * are contained in the setup-part of the document.
	 *
	 * @param serializedWiseML a serialized WiseML document
	 * @param types			node types to include, e.g. "isense", "telosb" will include all iSense and all TelosB nodes
	 *                         contained in the WiseML document
	 *
	 * @return a List of node URNs
	 */
	@SuppressWarnings("unused")
	public static List<String> getNodeUrns(final String serializedWiseML, final String... types) {

		List<String> nodeUrns = new LinkedList<String>();

		for (Node node : getNodes(serializedWiseML, types)) {
			nodeUrns.add(node.getId());
		}

		return nodeUrns;
	}

	/**
	 * Returns a prettily formatted and indented version of the given serialized WiseML document in {@code
	 * serializedWiseML}.
	 *
	 * @param serializedWiseML the serialized WiseML document
	 *
	 * @return a prettily formatted and indented serialized WiseML document
	 */
	@SuppressWarnings("unused")
	public static String prettyPrintWiseML(final String serializedWiseML) {
		return serialize(deserialize(serializedWiseML));
	}

	/**
	 * Reads a WiseML document from a file and returns it serialized and prettily formatted and indented.
	 *
	 * @param filename the name of the file to read from
	 *
	 * @return a serialized and prettily formatted and indented WiseML document or {@code null} if an error occurs
	 */
	@SuppressWarnings("unused")
	public static String readWiseMLFromFile(final String filename) {

		File wiseMLFile = new File(filename);

		if (!wiseMLFile.exists()) {
			log.error("WiseML file {} does not exist!", wiseMLFile.getAbsolutePath());
			return null;
		} else if (wiseMLFile.isDirectory()) {
			log.error("WiseML file name {} points to a directory!", wiseMLFile.getAbsolutePath());
			return null;
		} else if (!wiseMLFile.canRead()) {
			log.error("WiseML file {} can't be read!", wiseMLFile.getAbsolutePath());
			return null;
		}

		try {

			BufferedReader wiseMLFileReader = new BufferedReader(new FileReader(wiseMLFile));
			StringBuilder wiseMLBuilder = new StringBuilder();

			while (wiseMLFileReader.ready()) {
				wiseMLBuilder.append(wiseMLFileReader.readLine());
			}

			return prettyPrintWiseML(wiseMLBuilder.toString());

		} catch (IOException e) {
			log.error("" + e, e);
			return null;
		}

	}

	/**
	 * Serializes the {@link Wiseml} instance.
	 *
	 * @param wiseML the {@link Wiseml} instance to serialize
	 *
	 * @return the serialized WiseML document
	 */
	@SuppressWarnings("unused")
	public static String serialize(final Wiseml wiseML) {
		StringWriter writer = new StringWriter();
		JAXB.marshal(wiseML, writer);
		return writer.toString();
	}

	/**
	 * Returns a String representation of {@code node}.
	 *
	 * @param node the {@link Node} instance
	 *
	 * @return a String representation of {@code node}
	 */
	public static String toString(Node node) {
		return "Node{" +
				"id='" + node.getId() + '\'' +
				", capability=" + toString(node.getCapability()) +
				", position=" + node.getPosition() +
				", gateway=" + node.isGateway() +
				", programDetails='" + node.getProgramDetails() + '\'' +
				", nodeType='" + node.getNodeType() + '\'' +
				", description='" + node.getDescription() + '\'' +
				"}";
	}

	/**
	 * Returns a String representation of {@code capabilities}.
	 *
	 * @param capabilities the {@link List<Capability>} instance
	 *
	 * @return a String representation of {@code capabilities}
	 */
	public static String toString(List<Capability> capabilities) {
		return Arrays.toString(capabilities.toArray());
	}

	/**
	 * Returns a String representation of {@code capability}.
	 *
	 * @param capability the {@link Capability} instance
	 *
	 * @return a String representation of {@code capability}
	 */
	public static String toString(Capability capability) {
		return "Capability{" +
				"_default='" + capability.getDefault() + '\'' +
				", name='" + capability.getName() + '\'' +
				", datatype=" + capability.getDatatype() +
				", unit=" + capability.getUnit() +
				'}';
	}

	/**
	 * Returns a String representation of {@code nodeProperties}.
	 *
	 * @param nodeProperties the {@link NodeProperties} instance
	 *
	 * @return a String representation of {@code nodeProperties}
	 */
	public static String toString(NodeProperties nodeProperties) {
		return "NodeProperties{" +
				"capability=" + nodeProperties.getCapability() +
				", position=" + nodeProperties.getPosition() +
				", gateway=" + nodeProperties.isGateway() +
				", programDetails='" + nodeProperties.getProgramDetails() + '\'' +
				", nodeType='" + nodeProperties.getNodeType() + '\'' +
				", description='" + nodeProperties.getDescription() + '\'' +
				'}';
	}

}
