//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.06.03 at 09:15:54 AM MESZ 
//


package eu.wisebed.ns.wiseml._1;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for node.properties complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="node.properties">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://wisebed.eu/ns/wiseml/1.0}position" minOccurs="0"/>
 *         &lt;element ref="{http://wisebed.eu/ns/wiseml/1.0}gateway" minOccurs="0"/>
 *         &lt;element ref="{http://wisebed.eu/ns/wiseml/1.0}programDetails" minOccurs="0"/>
 *         &lt;element ref="{http://wisebed.eu/ns/wiseml/1.0}nodeType" minOccurs="0"/>
 *         &lt;element ref="{http://wisebed.eu/ns/wiseml/1.0}description" minOccurs="0"/>
 *         &lt;element ref="{http://wisebed.eu/ns/wiseml/1.0}capability" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "node.properties", propOrder = {
		"position",
		"gateway",
		"programDetails",
		"nodeType",
		"description",
		"capability"
})
@XmlSeeAlso({
		eu.wisebed.ns.wiseml._1.Setup.Node.class
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-06-03T09:15:54+02:00",
		comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class NodeProperties {

	@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-06-03T09:15:54+02:00",
			comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
	protected Coordinate position;

	@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-06-03T09:15:54+02:00",
			comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
	protected Boolean gateway;

	@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-06-03T09:15:54+02:00",
			comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
	protected String programDetails;

	@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-06-03T09:15:54+02:00",
			comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
	protected String nodeType;

	@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-06-03T09:15:54+02:00",
			comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
	protected String description;

	@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-06-03T09:15:54+02:00",
			comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
	protected List<Capability> capability;

	/**
	 * Gets the value of the position property.
	 *
	 * @return possible object is {@link Coordinate }
	 */
	@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-06-03T09:15:54+02:00",
			comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
	public Coordinate getPosition() {
		return position;
	}

	/**
	 * Sets the value of the position property.
	 *
	 * @param value allowed object is {@link Coordinate }
	 */
	@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-06-03T09:15:54+02:00",
			comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
	public void setPosition(Coordinate value) {
		this.position = value;
	}

	/**
	 * Gets the value of the gateway property.
	 *
	 * @return possible object is {@link Boolean }
	 */
	@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-06-03T09:15:54+02:00",
			comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
	public Boolean isGateway() {
		return gateway;
	}

	/**
	 * Sets the value of the gateway property.
	 *
	 * @param value allowed object is {@link Boolean }
	 */
	@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-06-03T09:15:54+02:00",
			comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
	public void setGateway(Boolean value) {
		this.gateway = value;
	}

	/**
	 * Gets the value of the programDetails property.
	 *
	 * @return possible object is {@link String }
	 */
	@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-06-03T09:15:54+02:00",
			comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
	public String getProgramDetails() {
		return programDetails;
	}

	/**
	 * Sets the value of the programDetails property.
	 *
	 * @param value allowed object is {@link String }
	 */
	@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-06-03T09:15:54+02:00",
			comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
	public void setProgramDetails(String value) {
		this.programDetails = value;
	}

	/**
	 * Gets the value of the nodeType property.
	 *
	 * @return possible object is {@link String }
	 */
	@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-06-03T09:15:54+02:00",
			comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
	public String getNodeType() {
		return nodeType;
	}

	/**
	 * Sets the value of the nodeType property.
	 *
	 * @param value allowed object is {@link String }
	 */
	@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-06-03T09:15:54+02:00",
			comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
	public void setNodeType(String value) {
		this.nodeType = value;
	}

	/**
	 * Gets the value of the description property.
	 *
	 * @return possible object is {@link String }
	 */
	@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-06-03T09:15:54+02:00",
			comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the value of the description property.
	 *
	 * @param value allowed object is {@link String }
	 */
	@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-06-03T09:15:54+02:00",
			comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
	public void setDescription(String value) {
		this.description = value;
	}

	/**
	 * Gets the value of the capability property.
	 * <p/>
	 * <p/>
	 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
	 * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
	 * capability property.
	 * <p/>
	 * <p/>
	 * For example, to add a new item, do as follows:
	 * <pre>
	 *    getCapability().add(newItem);
	 * </pre>
	 * <p/>
	 * <p/>
	 * <p/>
	 * Objects of the following type(s) are allowed in the list {@link Capability }
	 */
	@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-06-03T09:15:54+02:00",
			comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
	public List<Capability> getCapability() {
		if (capability == null) {
			capability = new ArrayList<Capability>();
		}
		return this.capability;
	}

}
