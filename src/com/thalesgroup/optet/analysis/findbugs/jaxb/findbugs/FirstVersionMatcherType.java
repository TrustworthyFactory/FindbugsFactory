//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.07.29 at 11:43:56 AM CEST 
//


package com.thalesgroup.optet.analysis.findbugs.jaxb.findbugs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FirstVersionMatcherType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FirstVersionMatcherType">
 *   &lt;complexContent>
 *     &lt;extension base="{}MatcherType">
 *       &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="relOp" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FirstVersionMatcherType")
public class FirstVersionMatcherType
    extends MatcherType
{

    @XmlAttribute(name = "value", required = true)
    protected long value;
    @XmlAttribute(name = "relOp", required = true)
    protected String relOp;

    /**
     * Gets the value of the value property.
     * 
     */
    public long getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     */
    public void setValue(long value) {
        this.value = value;
    }

    /**
     * Gets the value of the relOp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelOp() {
        return relOp;
    }

    /**
     * Sets the value of the relOp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelOp(String value) {
        this.relOp = value;
    }

}
