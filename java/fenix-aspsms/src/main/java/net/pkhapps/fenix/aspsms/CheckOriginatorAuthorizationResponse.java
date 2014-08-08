
package net.pkhapps.fenix.aspsms;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CheckOriginatorAuthorizationResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "checkOriginatorAuthorizationResult"
})
@XmlRootElement(name = "CheckOriginatorAuthorizationResponse")
public class CheckOriginatorAuthorizationResponse {

    @XmlElement(name = "CheckOriginatorAuthorizationResult")
    protected String checkOriginatorAuthorizationResult;

    /**
     * Gets the value of the checkOriginatorAuthorizationResult property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCheckOriginatorAuthorizationResult() {
        return checkOriginatorAuthorizationResult;
    }

    /**
     * Sets the value of the checkOriginatorAuthorizationResult property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCheckOriginatorAuthorizationResult(String value) {
        this.checkOriginatorAuthorizationResult = value;
    }

}
