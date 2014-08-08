
package net.pkhapps.fenix.aspsms;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 */
@WebServiceClient(name = "ASPSMSX2", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx", wsdlLocation = "https://webservice.aspsms.com/aspsmsx2.asmx?WSDL")
public class ASPSMSX2
        extends Service {

    private final static URL ASPSMSX2_WSDL_LOCATION;
    private final static WebServiceException ASPSMSX2_EXCEPTION;
    private final static QName ASPSMSX2_QNAME = new QName("https://webservice.aspsms.com/aspsmsx2.asmx", "ASPSMSX2");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("https://webservice.aspsms.com/aspsmsx2.asmx?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ASPSMSX2_WSDL_LOCATION = url;
        ASPSMSX2_EXCEPTION = e;
    }

    public ASPSMSX2() {
        super(__getWsdlLocation(), ASPSMSX2_QNAME);
    }

    public ASPSMSX2(WebServiceFeature... features) {
        super(__getWsdlLocation(), ASPSMSX2_QNAME, features);
    }

    public ASPSMSX2(URL wsdlLocation) {
        super(wsdlLocation, ASPSMSX2_QNAME);
    }

    public ASPSMSX2(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ASPSMSX2_QNAME, features);
    }

    public ASPSMSX2(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ASPSMSX2(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    private static URL __getWsdlLocation() {
        if (ASPSMSX2_EXCEPTION != null) {
            throw ASPSMSX2_EXCEPTION;
        }
        return ASPSMSX2_WSDL_LOCATION;
    }

    /**
     * @return returns ASPSMSX2Soap
     */
    @WebEndpoint(name = "ASPSMSX2Soap")
    public ASPSMSX2Soap getASPSMSX2Soap() {
        return super.getPort(new QName("https://webservice.aspsms.com/aspsmsx2.asmx", "ASPSMSX2Soap"), ASPSMSX2Soap.class);
    }

    /**
     * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns ASPSMSX2Soap
     */
    @WebEndpoint(name = "ASPSMSX2Soap")
    public ASPSMSX2Soap getASPSMSX2Soap(WebServiceFeature... features) {
        return super.getPort(new QName("https://webservice.aspsms.com/aspsmsx2.asmx", "ASPSMSX2Soap"), ASPSMSX2Soap.class, features);
    }

    /**
     * @return returns ASPSMSX2Soap
     */
    @WebEndpoint(name = "ASPSMSX2Soap12")
    public ASPSMSX2Soap getASPSMSX2Soap12() {
        return super.getPort(new QName("https://webservice.aspsms.com/aspsmsx2.asmx", "ASPSMSX2Soap12"), ASPSMSX2Soap.class);
    }

    /**
     * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns ASPSMSX2Soap
     */
    @WebEndpoint(name = "ASPSMSX2Soap12")
    public ASPSMSX2Soap getASPSMSX2Soap12(WebServiceFeature... features) {
        return super.getPort(new QName("https://webservice.aspsms.com/aspsmsx2.asmx", "ASPSMSX2Soap12"), ASPSMSX2Soap.class, features);
    }

    /**
     * @return returns ASPSMSX2HttpGet
     */
    @WebEndpoint(name = "ASPSMSX2HttpGet")
    public ASPSMSX2HttpGet getASPSMSX2HttpGet() {
        return super.getPort(new QName("https://webservice.aspsms.com/aspsmsx2.asmx", "ASPSMSX2HttpGet"), ASPSMSX2HttpGet.class);
    }

    /**
     * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns ASPSMSX2HttpGet
     */
    @WebEndpoint(name = "ASPSMSX2HttpGet")
    public ASPSMSX2HttpGet getASPSMSX2HttpGet(WebServiceFeature... features) {
        return super.getPort(new QName("https://webservice.aspsms.com/aspsmsx2.asmx", "ASPSMSX2HttpGet"), ASPSMSX2HttpGet.class, features);
    }

    /**
     * @return returns ASPSMSX2HttpPost
     */
    @WebEndpoint(name = "ASPSMSX2HttpPost")
    public ASPSMSX2HttpPost getASPSMSX2HttpPost() {
        return super.getPort(new QName("https://webservice.aspsms.com/aspsmsx2.asmx", "ASPSMSX2HttpPost"), ASPSMSX2HttpPost.class);
    }

    /**
     * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns ASPSMSX2HttpPost
     */
    @WebEndpoint(name = "ASPSMSX2HttpPost")
    public ASPSMSX2HttpPost getASPSMSX2HttpPost(WebServiceFeature... features) {
        return super.getPort(new QName("https://webservice.aspsms.com/aspsmsx2.asmx", "ASPSMSX2HttpPost"), ASPSMSX2HttpPost.class, features);
    }

}
