//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.07.05 at 07:35:15 PM MESZ 
//


package de.uniluebeck.itm.tr.runtime.socketconnector.xml;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="port" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "port"
})
@XmlRootElement(name = "socketconnector")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-07-05T07:35:15+02:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class Socketconnector {

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-07-05T07:35:15+02:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected int port;

    /**
     * Gets the value of the port property.
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-07-05T07:35:15+02:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public int getPort() {
        return port;
    }

    /**
     * Sets the value of the port property.
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-07-05T07:35:15+02:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPort(int value) {
        this.port = value;
    }

}