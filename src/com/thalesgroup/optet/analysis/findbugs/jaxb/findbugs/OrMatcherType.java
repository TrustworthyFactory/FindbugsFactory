//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.07.29 at 11:43:56 AM CEST 
//


package com.thalesgroup.optet.analysis.findbugs.jaxb.findbugs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrMatcherType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrMatcherType">
 *   &lt;complexContent>
 *     &lt;extension base="{}MatcherType">
 *       &lt;sequence>
 *         &lt;element ref="{}Matcher" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrMatcherType", propOrder = {
    "matcher"
})
public class OrMatcherType
    extends MatcherType
{

    @XmlElementRef(name = "Matcher", type = JAXBElement.class)
    protected List<JAXBElement<? extends MatcherType>> matcher;

    /**
     * Gets the value of the matcher property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the matcher property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMatcher().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link BugCodeMatcherType }{@code >}
     * {@link JAXBElement }{@code <}{@link PackageMatcherType }{@code >}
     * {@link JAXBElement }{@code <}{@link DesignationMatcherType }{@code >}
     * {@link JAXBElement }{@code <}{@link MethodMatcherType }{@code >}
     * {@link JAXBElement }{@code <}{@link FieldMatcherType }{@code >}
     * {@link JAXBElement }{@code <}{@link ClassMatcherType }{@code >}
     * {@link JAXBElement }{@code <}{@link RankMatcherType }{@code >}
     * {@link JAXBElement }{@code <}{@link BugMatcherType }{@code >}
     * {@link JAXBElement }{@code <}{@link OrMatcherType }{@code >}
     * {@link JAXBElement }{@code <}{@link PriorityMatcherType }{@code >}
     * {@link JAXBElement }{@code <}{@link AndMatcherType }{@code >}
     * {@link JAXBElement }{@code <}{@link MatcherType }{@code >}
     * {@link JAXBElement }{@code <}{@link NotMatcherType }{@code >}
     * {@link JAXBElement }{@code <}{@link MatchMatcherType }{@code >}
     * {@link JAXBElement }{@code <}{@link LocalMatcherType }{@code >}
     * {@link JAXBElement }{@code <}{@link LastVersionMatcherType }{@code >}
     * {@link JAXBElement }{@code <}{@link FirstVersionMatcherType }{@code >}
     * {@link JAXBElement }{@code <}{@link BugPatternMatcherType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends MatcherType>> getMatcher() {
        if (matcher == null) {
            matcher = new ArrayList<JAXBElement<? extends MatcherType>>();
        }
        return this.matcher;
    }

}
