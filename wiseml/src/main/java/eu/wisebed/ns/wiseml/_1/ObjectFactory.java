//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.06.03 at 09:15:54 AM MESZ 
//


package eu.wisebed.ns.wiseml._1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface generated in the
 * eu.wisebed.ns.wiseml._1 package. <p>An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model groups.  Factory
 * methods for each of these are provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _Gateway_QNAME = new QName("http://wisebed.eu/ns/wiseml/1.0", "gateway");

	private final static QName _Theta_QNAME = new QName("http://wisebed.eu/ns/wiseml/1.0", "theta");

	private final static QName _Position_QNAME = new QName("http://wisebed.eu/ns/wiseml/1.0", "position");

	private final static QName _Virtual_QNAME = new QName("http://wisebed.eu/ns/wiseml/1.0", "virtual");

	private final static QName _Interpolation_QNAME = new QName("http://wisebed.eu/ns/wiseml/1.0", "interpolation");

	private final static QName _Origin_QNAME = new QName("http://wisebed.eu/ns/wiseml/1.0", "origin");

	private final static QName _Name_QNAME = new QName("http://wisebed.eu/ns/wiseml/1.0", "name");

	private final static QName _Description_QNAME = new QName("http://wisebed.eu/ns/wiseml/1.0", "description");

	private final static QName _Start_QNAME = new QName("http://wisebed.eu/ns/wiseml/1.0", "start");

	private final static QName _Default_QNAME = new QName("http://wisebed.eu/ns/wiseml/1.0", "default");

	private final static QName _ProgramDetails_QNAME = new QName("http://wisebed.eu/ns/wiseml/1.0", "programDetails");

	private final static QName _Duration_QNAME = new QName("http://wisebed.eu/ns/wiseml/1.0", "duration");

	private final static QName _Encrypted_QNAME = new QName("http://wisebed.eu/ns/wiseml/1.0", "encrypted");

	private final static QName _Unit_QNAME = new QName("http://wisebed.eu/ns/wiseml/1.0", "unit");

	private final static QName _Timestamp_QNAME = new QName("http://wisebed.eu/ns/wiseml/1.0", "timestamp");

	private final static QName _X_QNAME = new QName("http://wisebed.eu/ns/wiseml/1.0", "x");

	private final static QName _Y_QNAME = new QName("http://wisebed.eu/ns/wiseml/1.0", "y");

	private final static QName _End_QNAME = new QName("http://wisebed.eu/ns/wiseml/1.0", "end");

	private final static QName _Phi_QNAME = new QName("http://wisebed.eu/ns/wiseml/1.0", "phi");

	private final static QName _Z_QNAME = new QName("http://wisebed.eu/ns/wiseml/1.0", "z");

	private final static QName _NodeType_QNAME = new QName("http://wisebed.eu/ns/wiseml/1.0", "nodeType");

	private final static QName _Timeinfo_QNAME = new QName("http://wisebed.eu/ns/wiseml/1.0", "timeinfo");

	private final static QName _Datatype_QNAME = new QName("http://wisebed.eu/ns/wiseml/1.0", "datatype");

	private final static QName _CoordinateType_QNAME = new QName("http://wisebed.eu/ns/wiseml/1.0", "coordinateType");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package:
	 * eu.wisebed.ns.wiseml._1
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link NodeProperties }
	 */
	public NodeProperties createNodeProperties() {
		return new NodeProperties();
	}

	/**
	 * Create an instance of {@link Setup }
	 */
	public Setup createSetup() {
		return new Setup();
	}

	/**
	 * Create an instance of {@link Timeinfo }
	 */
	public Timeinfo createTimeinfo() {
		return new Timeinfo();
	}

	/**
	 * Create an instance of {@link Coordinate }
	 */
	public Coordinate createCoordinate() {
		return new Coordinate();
	}

	/**
	 * Create an instance of {@link Defaults }
	 */
	public Defaults createDefaults() {
		return new Defaults();
	}

	/**
	 * Create an instance of {@link Scenario }
	 */
	public Scenario createScenario() {
		return new Scenario();
	}

	/**
	 * Create an instance of {@link Scenario.Node }
	 */
	public Scenario.Node createScenarioNode() {
		return new Scenario.Node();
	}

	/**
	 * Create an instance of {@link Rssi }
	 */
	public Rssi createRssi() {
		return new Rssi();
	}

	/**
	 * Create an instance of {@link Trace }
	 */
	public Trace createTrace() {
		return new Trace();
	}

	/**
	 * Create an instance of {@link LinkProperties }
	 */
	public LinkProperties createLinkProperties() {
		return new LinkProperties();
	}

	/**
	 * Create an instance of {@link Data }
	 */
	public Data createData() {
		return new Data();
	}

	/**
	 * Create an instance of {@link Setup.Link }
	 */
	public Setup.Link createSetupLink() {
		return new Setup.Link();
	}

	/**
	 * Create an instance of {@link EnableNode }
	 */
	public EnableNode createEnableNode() {
		return new EnableNode();
	}

	/**
	 * Create an instance of {@link Trace.Node }
	 */
	public Trace.Node createTraceNode() {
		return new Trace.Node();
	}

	/**
	 * Create an instance of {@link EnableLink }
	 */
	public EnableLink createEnableLink() {
		return new EnableLink();
	}

	/**
	 * Create an instance of {@link Setup.Node }
	 */
	public Setup.Node createSetupNode() {
		return new Setup.Node();
	}

	/**
	 * Create an instance of {@link DisableLink }
	 */
	public DisableLink createDisableLink() {
		return new DisableLink();
	}

	/**
	 * Create an instance of {@link DisableNode }
	 */
	public DisableNode createDisableNode() {
		return new DisableNode();
	}

	/**
	 * Create an instance of {@link Capability }
	 */
	public Capability createCapability() {
		return new Capability();
	}

	/**
	 * Create an instance of {@link Trace.Link }
	 */
	public Trace.Link createTraceLink() {
		return new Trace.Link();
	}

	/**
	 * Create an instance of {@link Wiseml }
	 */
	public Wiseml createWiseml() {
		return new Wiseml();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
	 */
	@XmlElementDecl(namespace = "http://wisebed.eu/ns/wiseml/1.0", name = "gateway")
	public JAXBElement<Boolean> createGateway(Boolean value) {
		return new JAXBElement<Boolean>(_Gateway_QNAME, Boolean.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
	 */
	@XmlElementDecl(namespace = "http://wisebed.eu/ns/wiseml/1.0", name = "theta")
	public JAXBElement<Double> createTheta(Double value) {
		return new JAXBElement<Double>(_Theta_QNAME, Double.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link Coordinate }{@code >}}
	 */
	@XmlElementDecl(namespace = "http://wisebed.eu/ns/wiseml/1.0", name = "position")
	public JAXBElement<Coordinate> createPosition(Coordinate value) {
		return new JAXBElement<Coordinate>(_Position_QNAME, Coordinate.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
	 */
	@XmlElementDecl(namespace = "http://wisebed.eu/ns/wiseml/1.0", name = "virtual")
	public JAXBElement<Boolean> createVirtual(Boolean value) {
		return new JAXBElement<Boolean>(_Virtual_QNAME, Boolean.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link Interpolation }{@code >}}
	 */
	@XmlElementDecl(namespace = "http://wisebed.eu/ns/wiseml/1.0", name = "interpolation")
	public JAXBElement<Interpolation> createInterpolation(Interpolation value) {
		return new JAXBElement<Interpolation>(_Interpolation_QNAME, Interpolation.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link Coordinate }{@code >}}
	 */
	@XmlElementDecl(namespace = "http://wisebed.eu/ns/wiseml/1.0", name = "origin")
	public JAXBElement<Coordinate> createOrigin(Coordinate value) {
		return new JAXBElement<Coordinate>(_Origin_QNAME, Coordinate.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
	 */
	@XmlElementDecl(namespace = "http://wisebed.eu/ns/wiseml/1.0", name = "name")
	public JAXBElement<String> createName(String value) {
		return new JAXBElement<String>(_Name_QNAME, String.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
	 */
	@XmlElementDecl(namespace = "http://wisebed.eu/ns/wiseml/1.0", name = "description")
	public JAXBElement<String> createDescription(String value) {
		return new JAXBElement<String>(_Description_QNAME, String.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
	 */
	@XmlElementDecl(namespace = "http://wisebed.eu/ns/wiseml/1.0", name = "start")
	public JAXBElement<XMLGregorianCalendar> createStart(XMLGregorianCalendar value) {
		return new JAXBElement<XMLGregorianCalendar>(_Start_QNAME, XMLGregorianCalendar.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
	 */
	@XmlElementDecl(namespace = "http://wisebed.eu/ns/wiseml/1.0", name = "default")
	public JAXBElement<String> createDefault(String value) {
		return new JAXBElement<String>(_Default_QNAME, String.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
	 */
	@XmlElementDecl(namespace = "http://wisebed.eu/ns/wiseml/1.0", name = "programDetails")
	public JAXBElement<String> createProgramDetails(String value) {
		return new JAXBElement<String>(_ProgramDetails_QNAME, String.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
	 */
	@XmlElementDecl(namespace = "http://wisebed.eu/ns/wiseml/1.0", name = "duration")
	public JAXBElement<String> createDuration(String value) {
		return new JAXBElement<String>(_Duration_QNAME, String.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
	 */
	@XmlElementDecl(namespace = "http://wisebed.eu/ns/wiseml/1.0", name = "encrypted")
	public JAXBElement<Boolean> createEncrypted(Boolean value) {
		return new JAXBElement<Boolean>(_Encrypted_QNAME, Boolean.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
	 */
	@XmlElementDecl(namespace = "http://wisebed.eu/ns/wiseml/1.0", name = "unit")
	public JAXBElement<String> createUnit(String value) {
		return new JAXBElement<String>(_Unit_QNAME, String.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
	 */
	@XmlElementDecl(namespace = "http://wisebed.eu/ns/wiseml/1.0", name = "timestamp")
	public JAXBElement<String> createTimestamp(String value) {
		return new JAXBElement<String>(_Timestamp_QNAME, String.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
	 */
	@XmlElementDecl(namespace = "http://wisebed.eu/ns/wiseml/1.0", name = "x")
	public JAXBElement<Double> createX(Double value) {
		return new JAXBElement<Double>(_X_QNAME, Double.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
	 */
	@XmlElementDecl(namespace = "http://wisebed.eu/ns/wiseml/1.0", name = "y")
	public JAXBElement<Double> createY(Double value) {
		return new JAXBElement<Double>(_Y_QNAME, Double.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
	 */
	@XmlElementDecl(namespace = "http://wisebed.eu/ns/wiseml/1.0", name = "end")
	public JAXBElement<XMLGregorianCalendar> createEnd(XMLGregorianCalendar value) {
		return new JAXBElement<XMLGregorianCalendar>(_End_QNAME, XMLGregorianCalendar.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
	 */
	@XmlElementDecl(namespace = "http://wisebed.eu/ns/wiseml/1.0", name = "phi")
	public JAXBElement<Double> createPhi(Double value) {
		return new JAXBElement<Double>(_Phi_QNAME, Double.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
	 */
	@XmlElementDecl(namespace = "http://wisebed.eu/ns/wiseml/1.0", name = "z")
	public JAXBElement<Double> createZ(Double value) {
		return new JAXBElement<Double>(_Z_QNAME, Double.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
	 */
	@XmlElementDecl(namespace = "http://wisebed.eu/ns/wiseml/1.0", name = "nodeType")
	public JAXBElement<String> createNodeType(String value) {
		return new JAXBElement<String>(_NodeType_QNAME, String.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link Timeinfo }{@code >}}
	 */
	@XmlElementDecl(namespace = "http://wisebed.eu/ns/wiseml/1.0", name = "timeinfo")
	public JAXBElement<Timeinfo> createTimeinfo(Timeinfo value) {
		return new JAXBElement<Timeinfo>(_Timeinfo_QNAME, Timeinfo.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link Dtypes }{@code >}}
	 */
	@XmlElementDecl(namespace = "http://wisebed.eu/ns/wiseml/1.0", name = "datatype")
	public JAXBElement<Dtypes> createDatatype(Dtypes value) {
		return new JAXBElement<Dtypes>(_Datatype_QNAME, Dtypes.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
	 */
	@XmlElementDecl(namespace = "http://wisebed.eu/ns/wiseml/1.0", name = "coordinateType")
	public JAXBElement<String> createCoordinateType(String value) {
		return new JAXBElement<String>(_CoordinateType_QNAME, String.class, null, value);
	}

}
