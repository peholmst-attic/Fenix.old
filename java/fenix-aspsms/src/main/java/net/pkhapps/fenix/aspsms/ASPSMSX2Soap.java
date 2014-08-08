
package net.pkhapps.fenix.aspsms;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 */
@WebService(name = "ASPSMSX2Soap", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
@XmlSeeAlso({
        ObjectFactory.class
})
public interface ASPSMSX2Soap {


    /**
     * Send Simple WAP Push. Multiple Recipients syntax: Recipient1:TRN1;Recipient2:TRN2;... <br>Returns StatusCode:Number
     *
     * @param wapDescription
     * @param flashingSMS
     * @param timeZone
     * @param urlBufferedMessageNotification
     * @param originator
     * @param affiliateId
     * @param userKey
     * @param password
     * @param wapURL
     * @param recipients
     * @param urlNonDeliveryNotification
     * @param deferredDeliveryTime
     * @param urlDeliveryNotification
     * @return returns java.lang.String
     */
    @WebMethod(operationName = "SimpleWAPPush", action = "https://webservice.aspsms.com/aspsmsx2.asmx/SimpleWAPPush")
    @WebResult(name = "SimpleWAPPushResult", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
    @RequestWrapper(localName = "SimpleWAPPush", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx", className = "net.pkhapps.fenix.aspsms.SimpleWAPPush")
    @ResponseWrapper(localName = "SimpleWAPPushResponse", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx", className = "net.pkhapps.fenix.aspsms.SimpleWAPPushResponse")
    public String simpleWAPPush(
            @WebParam(name = "UserKey", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String userKey,
            @WebParam(name = "Password", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String password,
            @WebParam(name = "Recipients", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String recipients,
            @WebParam(name = "Originator", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String originator,
            @WebParam(name = "WapDescription", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String wapDescription,
            @WebParam(name = "WapURL", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String wapURL,
            @WebParam(name = "DeferredDeliveryTime", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String deferredDeliveryTime,
            @WebParam(name = "FlashingSMS", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String flashingSMS,
            @WebParam(name = "TimeZone", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String timeZone,
            @WebParam(name = "URLBufferedMessageNotification", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String urlBufferedMessageNotification,
            @WebParam(name = "URLDeliveryNotification", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String urlDeliveryNotification,
            @WebParam(name = "URLNonDeliveryNotification", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String urlNonDeliveryNotification,
            @WebParam(name = "AffiliateId", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String affiliateId);

    /**
     * Send Text Messages. Multiple Recipients syntax: Recipient1;Recipient2;Recipient3... <br>Returns StatusCode:Number
     *
     * @param messageText
     * @param password
     * @param recipients
     * @param originator
     * @param userKey
     * @return returns java.lang.String
     */
    @WebMethod(operationName = "SendSimpleTextSMS", action = "https://webservice.aspsms.com/aspsmsx2.asmx/SendSimpleTextSMS")
    @WebResult(name = "SendSimpleTextSMSResult", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
    @RequestWrapper(localName = "SendSimpleTextSMS", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx", className = "net.pkhapps.fenix.aspsms.SendSimpleTextSMS")
    @ResponseWrapper(localName = "SendSimpleTextSMSResponse", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx", className = "net.pkhapps.fenix.aspsms.SendSimpleTextSMSResponse")
    public String sendSimpleTextSMS(
            @WebParam(name = "UserKey", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String userKey,
            @WebParam(name = "Password", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String password,
            @WebParam(name = "Recipients", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String recipients,
            @WebParam(name = "Originator", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String originator,
            @WebParam(name = "MessageText", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String messageText);

    /**
     * Send Text Messages. Multiple Recipients syntax: Recipient1:TRN1;Recipient2:TRN2;... <br>Returns StatusCode:Number
     *
     * @param messageText
     * @param password
     * @param flashingSMS
     * @param recipients
     * @param timeZone
     * @param urlBufferedMessageNotification
     * @param originator
     * @param urlNonDeliveryNotification
     * @param affiliateId
     * @param userKey
     * @param deferredDeliveryTime
     * @param urlDeliveryNotification
     * @return returns java.lang.String
     */
    @WebMethod(operationName = "SendTextSMS", action = "https://webservice.aspsms.com/aspsmsx2.asmx/SendTextSMS")
    @WebResult(name = "SendTextSMSResult", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
    @RequestWrapper(localName = "SendTextSMS", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx", className = "net.pkhapps.fenix.aspsms.SendTextSMS")
    @ResponseWrapper(localName = "SendTextSMSResponse", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx", className = "net.pkhapps.fenix.aspsms.SendTextSMSResponse")
    public String sendTextSMS(
            @WebParam(name = "UserKey", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String userKey,
            @WebParam(name = "Password", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String password,
            @WebParam(name = "Recipients", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String recipients,
            @WebParam(name = "Originator", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String originator,
            @WebParam(name = "MessageText", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String messageText,
            @WebParam(name = "DeferredDeliveryTime", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String deferredDeliveryTime,
            @WebParam(name = "FlashingSMS", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String flashingSMS,
            @WebParam(name = "TimeZone", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String timeZone,
            @WebParam(name = "URLBufferedMessageNotification", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String urlBufferedMessageNotification,
            @WebParam(name = "URLDeliveryNotification", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String urlDeliveryNotification,
            @WebParam(name = "URLNonDeliveryNotification", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String urlNonDeliveryNotification,
            @WebParam(name = "AffiliateId", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String affiliateId);

    /**
     * Send Unicode Messages. Multiple Recipients syntax: Recipient1:TRN1;Recipient2:TRN2;... <br>Returns StatusCode:Number
     *
     * @param messageText
     * @param password
     * @param flashingSMS
     * @param recipients
     * @param timeZone
     * @param urlBufferedMessageNotification
     * @param originator
     * @param urlNonDeliveryNotification
     * @param affiliateId
     * @param userKey
     * @param deferredDeliveryTime
     * @param urlDeliveryNotification
     * @return returns java.lang.String
     */
    @WebMethod(operationName = "SendUnicodeSMS", action = "https://webservice.aspsms.com/aspsmsx2.asmx/SendUnicodeSMS")
    @WebResult(name = "SendUnicodeSMSResult", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
    @RequestWrapper(localName = "SendUnicodeSMS", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx", className = "net.pkhapps.fenix.aspsms.SendUnicodeSMS")
    @ResponseWrapper(localName = "SendUnicodeSMSResponse", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx", className = "net.pkhapps.fenix.aspsms.SendUnicodeSMSResponse")
    public String sendUnicodeSMS(
            @WebParam(name = "UserKey", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String userKey,
            @WebParam(name = "Password", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String password,
            @WebParam(name = "Recipients", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String recipients,
            @WebParam(name = "Originator", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String originator,
            @WebParam(name = "MessageText", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String messageText,
            @WebParam(name = "DeferredDeliveryTime", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String deferredDeliveryTime,
            @WebParam(name = "FlashingSMS", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String flashingSMS,
            @WebParam(name = "TimeZone", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String timeZone,
            @WebParam(name = "URLBufferedMessageNotification", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String urlBufferedMessageNotification,
            @WebParam(name = "URLDeliveryNotification", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String urlDeliveryNotification,
            @WebParam(name = "URLNonDeliveryNotification", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String urlNonDeliveryNotification,
            @WebParam(name = "AffiliateId", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String affiliateId);

    /**
     * Check available credits. <br>Returns StatusCode:Number or Credits:Count
     *
     * @param password
     * @param userKey
     * @return returns java.lang.String
     */
    @WebMethod(operationName = "CheckCredits", action = "https://webservice.aspsms.com/aspsmsx2.asmx/CheckCredits")
    @WebResult(name = "CheckCreditsResult", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
    @RequestWrapper(localName = "CheckCredits", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx", className = "net.pkhapps.fenix.aspsms.CheckCredits")
    @ResponseWrapper(localName = "CheckCreditsResponse", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx", className = "net.pkhapps.fenix.aspsms.CheckCreditsResponse")
    public String checkCredits(
            @WebParam(name = "UserKey", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String userKey,
            @WebParam(name = "Password", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String password);

    /**
     * Send a originator unlock code to mobile phone. <br>Returns StatusCode:Number
     *
     * @param password
     * @param originator
     * @param userKey
     * @return returns java.lang.String
     */
    @WebMethod(operationName = "SendOriginatorUnlockCode", action = "https://webservice.aspsms.com/aspsmsx2.asmx/SendOriginatorUnlockCode")
    @WebResult(name = "SendOriginatorUnlockCodeResult", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
    @RequestWrapper(localName = "SendOriginatorUnlockCode", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx", className = "net.pkhapps.fenix.aspsms.SendOriginatorUnlockCode")
    @ResponseWrapper(localName = "SendOriginatorUnlockCodeResponse", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx", className = "net.pkhapps.fenix.aspsms.SendOriginatorUnlockCodeResponse")
    public String sendOriginatorUnlockCode(
            @WebParam(name = "UserKey", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String userKey,
            @WebParam(name = "Password", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String password,
            @WebParam(name = "Originator", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String originator);

    /**
     * Unlock Originator. <br>Returns StatusCode:Number
     *
     * @param originatorUnlockCode
     * @param password
     * @param originator
     * @param affiliateId
     * @param userKey
     * @return returns java.lang.String
     */
    @WebMethod(operationName = "UnlockOriginator", action = "https://webservice.aspsms.com/aspsmsx2.asmx/UnlockOriginator")
    @WebResult(name = "UnlockOriginatorResult", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
    @RequestWrapper(localName = "UnlockOriginator", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx", className = "net.pkhapps.fenix.aspsms.UnlockOriginator")
    @ResponseWrapper(localName = "UnlockOriginatorResponse", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx", className = "net.pkhapps.fenix.aspsms.UnlockOriginatorResponse")
    public String unlockOriginator(
            @WebParam(name = "UserKey", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String userKey,
            @WebParam(name = "Password", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String password,
            @WebParam(name = "Originator", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String originator,
            @WebParam(name = "OriginatorUnlockCode", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String originatorUnlockCode,
            @WebParam(name = "AffiliateId", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String affiliateId);

    /**
     * Check originator authorization. <br>Returns StatusCode:Number
     *
     * @param password
     * @param originator
     * @param userKey
     * @return returns java.lang.String
     */
    @WebMethod(operationName = "CheckOriginatorAuthorization", action = "https://webservice.aspsms.com/aspsmsx2.asmx/CheckOriginatorAuthorization")
    @WebResult(name = "CheckOriginatorAuthorizationResult", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
    @RequestWrapper(localName = "CheckOriginatorAuthorization", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx", className = "net.pkhapps.fenix.aspsms.CheckOriginatorAuthorization")
    @ResponseWrapper(localName = "CheckOriginatorAuthorizationResponse", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx", className = "net.pkhapps.fenix.aspsms.CheckOriginatorAuthorizationResponse")
    public String checkOriginatorAuthorization(
            @WebParam(name = "UserKey", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String userKey,
            @WebParam(name = "Password", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String password,
            @WebParam(name = "Originator", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String originator);

    /**
     * Verify/Check Security Token. <br>Returns StatusCode:Number
     *
     * @param password
     * @param phoneNumber
     * @param tokenReference
     * @param userKey
     * @param verificationCode
     * @return returns java.lang.String
     */
    @WebMethod(operationName = "VerifyToken", action = "https://webservice.aspsms.com/aspsmsx2.asmx/VerifyToken")
    @WebResult(name = "VerifyTokenResult", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
    @RequestWrapper(localName = "VerifyToken", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx", className = "net.pkhapps.fenix.aspsms.VerifyToken")
    @ResponseWrapper(localName = "VerifyTokenResponse", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx", className = "net.pkhapps.fenix.aspsms.VerifyTokenResponse")
    public String verifyToken(
            @WebParam(name = "UserKey", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String userKey,
            @WebParam(name = "Password", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String password,
            @WebParam(name = "PhoneNumber", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String phoneNumber,
            @WebParam(name = "TokenReference", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String tokenReference,
            @WebParam(name = "VerificationCode", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String verificationCode);

    /**
     * Send Security Token to mobile recipient. Multiple Recipients syntax: Recipient1:TRN1;Recipient2:TRN2;... <br>Returns StatusCode:Number
     *
     * @param urlBufferedMessageNotification
     * @param tokenValidity
     * @param originator
     * @param affiliateId
     * @param userKey
     * @param tokenMask
     * @param verificationCode
     * @param password
     * @param recipients
     * @param tokenReference
     * @param tokenCaseSensitive
     * @param urlNonDeliveryNotification
     * @param messageData
     * @param urlDeliveryNotification
     * @return returns java.lang.String
     */
    @WebMethod(operationName = "SendTokenSMS", action = "https://webservice.aspsms.com/aspsmsx2.asmx/SendTokenSMS")
    @WebResult(name = "SendTokenSMSResult", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
    @RequestWrapper(localName = "SendTokenSMS", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx", className = "net.pkhapps.fenix.aspsms.SendTokenSMS")
    @ResponseWrapper(localName = "SendTokenSMSResponse", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx", className = "net.pkhapps.fenix.aspsms.SendTokenSMSResponse")
    public String sendTokenSMS(
            @WebParam(name = "UserKey", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String userKey,
            @WebParam(name = "Password", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String password,
            @WebParam(name = "Originator", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String originator,
            @WebParam(name = "Recipients", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String recipients,
            @WebParam(name = "MessageData", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String messageData,
            @WebParam(name = "TokenReference", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String tokenReference,
            @WebParam(name = "TokenValidity", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String tokenValidity,
            @WebParam(name = "TokenMask", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String tokenMask,
            @WebParam(name = "VerificationCode", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String verificationCode,
            @WebParam(name = "TokenCaseSensitive", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String tokenCaseSensitive,
            @WebParam(name = "URLBufferedMessageNotification", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String urlBufferedMessageNotification,
            @WebParam(name = "URLDeliveryNotification", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String urlDeliveryNotification,
            @WebParam(name = "URLNonDeliveryNotification", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String urlNonDeliveryNotification,
            @WebParam(name = "AffiliateId", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String affiliateId);

    /**
     * Inquire Delivery Notifications. Multiple TransactionReferenceNumbers delimited by ;<br>Returns DeliveryNotifications or on error StatusCode:Number
     *
     * @param password
     * @param userKey
     * @param transactionReferenceNumbers
     * @return returns java.lang.String
     */
    @WebMethod(operationName = "InquireDeliveryNotifications", action = "https://webservice.aspsms.com/aspsmsx2.asmx/InquireDeliveryNotifications")
    @WebResult(name = "InquireDeliveryNotificationsResult", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
    @RequestWrapper(localName = "InquireDeliveryNotifications", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx", className = "net.pkhapps.fenix.aspsms.InquireDeliveryNotifications")
    @ResponseWrapper(localName = "InquireDeliveryNotificationsResponse", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx", className = "net.pkhapps.fenix.aspsms.InquireDeliveryNotificationsResponse")
    public String inquireDeliveryNotifications(
            @WebParam(name = "UserKey", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String userKey,
            @WebParam(name = "Password", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String password,
            @WebParam(name = "TransactionReferenceNumbers", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String transactionReferenceNumbers);

    /**
     * Get Description for StatusCode:Number
     *
     * @param statusCode
     * @return returns java.lang.String
     */
    @WebMethod(operationName = "GetStatusCodeDescription", action = "https://webservice.aspsms.com/aspsmsx2.asmx/GetStatusCodeDescription")
    @WebResult(name = "GetStatusCodeDescriptionResult", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
    @RequestWrapper(localName = "GetStatusCodeDescription", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx", className = "net.pkhapps.fenix.aspsms.GetStatusCodeDescription")
    @ResponseWrapper(localName = "GetStatusCodeDescriptionResponse", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx", className = "net.pkhapps.fenix.aspsms.GetStatusCodeDescriptionResponse")
    public String getStatusCodeDescription(
            @WebParam(name = "StatusCode", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
            String statusCode);

    /**
     * @return returns java.lang.String
     */
    @WebMethod(operationName = "VersionInfo", action = "https://webservice.aspsms.com/aspsmsx2.asmx/VersionInfo")
    @WebResult(name = "VersionInfoResult", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx")
    @RequestWrapper(localName = "VersionInfo", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx", className = "net.pkhapps.fenix.aspsms.VersionInfo")
    @ResponseWrapper(localName = "VersionInfoResponse", targetNamespace = "https://webservice.aspsms.com/aspsmsx2.asmx", className = "net.pkhapps.fenix.aspsms.VersionInfoResponse")
    public String versionInfo();

}
