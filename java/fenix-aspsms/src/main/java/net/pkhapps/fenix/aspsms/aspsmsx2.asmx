<?xml version="1.0" encoding="utf-8"?>
<wsdl:definitions xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tm="http://microsoft.com/wsdl/mime/textMatching/" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:tns="https://webservice.aspsms.com/aspsmsx2.asmx" xmlns:s="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:http="http://schemas.xmlsoap.org/wsdl/http/" targetNamespace="https://webservice.aspsms.com/aspsmsx2.asmx" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">
  <wsdl:types>
    <s:schema elementFormDefault="qualified" targetNamespace="https://webservice.aspsms.com/aspsmsx2.asmx">
      <s:element name="SimpleWAPPush">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserKey" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Recipients" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Originator" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="WapDescription" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="WapURL" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="DeferredDeliveryTime" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="FlashingSMS" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="TimeZone" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="URLBufferedMessageNotification" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="URLDeliveryNotification" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="URLNonDeliveryNotification" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="AffiliateId" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SimpleWAPPushResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SimpleWAPPushResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SendSimpleTextSMS">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserKey" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Recipients" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Originator" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="MessageText" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SendSimpleTextSMSResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SendSimpleTextSMSResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SendTextSMS">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserKey" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Recipients" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Originator" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="MessageText" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="DeferredDeliveryTime" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="FlashingSMS" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="TimeZone" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="URLBufferedMessageNotification" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="URLDeliveryNotification" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="URLNonDeliveryNotification" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="AffiliateId" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SendTextSMSResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SendTextSMSResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SendUnicodeSMS">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserKey" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Recipients" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Originator" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="MessageText" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="DeferredDeliveryTime" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="FlashingSMS" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="TimeZone" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="URLBufferedMessageNotification" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="URLDeliveryNotification" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="URLNonDeliveryNotification" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="AffiliateId" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SendUnicodeSMSResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SendUnicodeSMSResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="CheckCredits">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserKey" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="CheckCreditsResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CheckCreditsResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SendOriginatorUnlockCode">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserKey" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Originator" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SendOriginatorUnlockCodeResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SendOriginatorUnlockCodeResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="UnlockOriginator">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserKey" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Originator" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="OriginatorUnlockCode" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="AffiliateId" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="UnlockOriginatorResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UnlockOriginatorResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="CheckOriginatorAuthorization">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserKey" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Originator" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="CheckOriginatorAuthorizationResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CheckOriginatorAuthorizationResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="VerifyToken">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserKey" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="PhoneNumber" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="TokenReference" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="VerificationCode" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="VerifyTokenResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="VerifyTokenResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SendTokenSMS">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserKey" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Originator" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Recipients" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="MessageData" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="TokenReference" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="TokenValidity" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="TokenMask" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="VerificationCode" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="TokenCaseSensitive" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="URLBufferedMessageNotification" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="URLDeliveryNotification" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="URLNonDeliveryNotification" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="AffiliateId" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SendTokenSMSResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SendTokenSMSResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="InquireDeliveryNotifications">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserKey" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="TransactionReferenceNumbers" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="InquireDeliveryNotificationsResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="InquireDeliveryNotificationsResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetStatusCodeDescription">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="StatusCode" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetStatusCodeDescriptionResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetStatusCodeDescriptionResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="VersionInfo">
        <s:complexType />
      </s:element>
      <s:element name="VersionInfoResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="VersionInfoResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="string" nillable="true" type="s:string" />
    </s:schema>
  </wsdl:types>
  <wsdl:message name="SimpleWAPPushSoapIn">
    <wsdl:part name="parameters" element="tns:SimpleWAPPush" />
  </wsdl:message>
  <wsdl:message name="SimpleWAPPushSoapOut">
    <wsdl:part name="parameters" element="tns:SimpleWAPPushResponse" />
  </wsdl:message>
  <wsdl:message name="SendSimpleTextSMSSoapIn">
    <wsdl:part name="parameters" element="tns:SendSimpleTextSMS" />
  </wsdl:message>
  <wsdl:message name="SendSimpleTextSMSSoapOut">
    <wsdl:part name="parameters" element="tns:SendSimpleTextSMSResponse" />
  </wsdl:message>
  <wsdl:message name="SendTextSMSSoapIn">
    <wsdl:part name="parameters" element="tns:SendTextSMS" />
  </wsdl:message>
  <wsdl:message name="SendTextSMSSoapOut">
    <wsdl:part name="parameters" element="tns:SendTextSMSResponse" />
  </wsdl:message>
  <wsdl:message name="SendUnicodeSMSSoapIn">
    <wsdl:part name="parameters" element="tns:SendUnicodeSMS" />
  </wsdl:message>
  <wsdl:message name="SendUnicodeSMSSoapOut">
    <wsdl:part name="parameters" element="tns:SendUnicodeSMSResponse" />
  </wsdl:message>
  <wsdl:message name="CheckCreditsSoapIn">
    <wsdl:part name="parameters" element="tns:CheckCredits" />
  </wsdl:message>
  <wsdl:message name="CheckCreditsSoapOut">
    <wsdl:part name="parameters" element="tns:CheckCreditsResponse" />
  </wsdl:message>
  <wsdl:message name="SendOriginatorUnlockCodeSoapIn">
    <wsdl:part name="parameters" element="tns:SendOriginatorUnlockCode" />
  </wsdl:message>
  <wsdl:message name="SendOriginatorUnlockCodeSoapOut">
    <wsdl:part name="parameters" element="tns:SendOriginatorUnlockCodeResponse" />
  </wsdl:message>
  <wsdl:message name="UnlockOriginatorSoapIn">
    <wsdl:part name="parameters" element="tns:UnlockOriginator" />
  </wsdl:message>
  <wsdl:message name="UnlockOriginatorSoapOut">
    <wsdl:part name="parameters" element="tns:UnlockOriginatorResponse" />
  </wsdl:message>
  <wsdl:message name="CheckOriginatorAuthorizationSoapIn">
    <wsdl:part name="parameters" element="tns:CheckOriginatorAuthorization" />
  </wsdl:message>
  <wsdl:message name="CheckOriginatorAuthorizationSoapOut">
    <wsdl:part name="parameters" element="tns:CheckOriginatorAuthorizationResponse" />
  </wsdl:message>
  <wsdl:message name="VerifyTokenSoapIn">
    <wsdl:part name="parameters" element="tns:VerifyToken" />
  </wsdl:message>
  <wsdl:message name="VerifyTokenSoapOut">
    <wsdl:part name="parameters" element="tns:VerifyTokenResponse" />
  </wsdl:message>
  <wsdl:message name="SendTokenSMSSoapIn">
    <wsdl:part name="parameters" element="tns:SendTokenSMS" />
  </wsdl:message>
  <wsdl:message name="SendTokenSMSSoapOut">
    <wsdl:part name="parameters" element="tns:SendTokenSMSResponse" />
  </wsdl:message>
  <wsdl:message name="InquireDeliveryNotificationsSoapIn">
    <wsdl:part name="parameters" element="tns:InquireDeliveryNotifications" />
  </wsdl:message>
  <wsdl:message name="InquireDeliveryNotificationsSoapOut">
    <wsdl:part name="parameters" element="tns:InquireDeliveryNotificationsResponse" />
  </wsdl:message>
  <wsdl:message name="GetStatusCodeDescriptionSoapIn">
    <wsdl:part name="parameters" element="tns:GetStatusCodeDescription" />
  </wsdl:message>
  <wsdl:message name="GetStatusCodeDescriptionSoapOut">
    <wsdl:part name="parameters" element="tns:GetStatusCodeDescriptionResponse" />
  </wsdl:message>
  <wsdl:message name="VersionInfoSoapIn">
    <wsdl:part name="parameters" element="tns:VersionInfo" />
  </wsdl:message>
  <wsdl:message name="VersionInfoSoapOut">
    <wsdl:part name="parameters" element="tns:VersionInfoResponse" />
  </wsdl:message>
  <wsdl:message name="SimpleWAPPushHttpGetIn">
    <wsdl:part name="UserKey" type="s:string" />
    <wsdl:part name="Password" type="s:string" />
    <wsdl:part name="Recipients" type="s:string" />
    <wsdl:part name="Originator" type="s:string" />
    <wsdl:part name="WapDescription" type="s:string" />
    <wsdl:part name="WapURL" type="s:string" />
    <wsdl:part name="DeferredDeliveryTime" type="s:string" />
    <wsdl:part name="FlashingSMS" type="s:string" />
    <wsdl:part name="TimeZone" type="s:string" />
    <wsdl:part name="URLBufferedMessageNotification" type="s:string" />
    <wsdl:part name="URLDeliveryNotification" type="s:string" />
    <wsdl:part name="URLNonDeliveryNotification" type="s:string" />
    <wsdl:part name="AffiliateId" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SimpleWAPPushHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="SendSimpleTextSMSHttpGetIn">
    <wsdl:part name="UserKey" type="s:string" />
    <wsdl:part name="Password" type="s:string" />
    <wsdl:part name="Recipients" type="s:string" />
    <wsdl:part name="Originator" type="s:string" />
    <wsdl:part name="MessageText" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SendSimpleTextSMSHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="SendTextSMSHttpGetIn">
    <wsdl:part name="UserKey" type="s:string" />
    <wsdl:part name="Password" type="s:string" />
    <wsdl:part name="Recipients" type="s:string" />
    <wsdl:part name="Originator" type="s:string" />
    <wsdl:part name="MessageText" type="s:string" />
    <wsdl:part name="DeferredDeliveryTime" type="s:string" />
    <wsdl:part name="FlashingSMS" type="s:string" />
    <wsdl:part name="TimeZone" type="s:string" />
    <wsdl:part name="URLBufferedMessageNotification" type="s:string" />
    <wsdl:part name="URLDeliveryNotification" type="s:string" />
    <wsdl:part name="URLNonDeliveryNotification" type="s:string" />
    <wsdl:part name="AffiliateId" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SendTextSMSHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="SendUnicodeSMSHttpGetIn">
    <wsdl:part name="UserKey" type="s:string" />
    <wsdl:part name="Password" type="s:string" />
    <wsdl:part name="Recipients" type="s:string" />
    <wsdl:part name="Originator" type="s:string" />
    <wsdl:part name="MessageText" type="s:string" />
    <wsdl:part name="DeferredDeliveryTime" type="s:string" />
    <wsdl:part name="FlashingSMS" type="s:string" />
    <wsdl:part name="TimeZone" type="s:string" />
    <wsdl:part name="URLBufferedMessageNotification" type="s:string" />
    <wsdl:part name="URLDeliveryNotification" type="s:string" />
    <wsdl:part name="URLNonDeliveryNotification" type="s:string" />
    <wsdl:part name="AffiliateId" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SendUnicodeSMSHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="CheckCreditsHttpGetIn">
    <wsdl:part name="UserKey" type="s:string" />
    <wsdl:part name="Password" type="s:string" />
  </wsdl:message>
  <wsdl:message name="CheckCreditsHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="SendOriginatorUnlockCodeHttpGetIn">
    <wsdl:part name="UserKey" type="s:string" />
    <wsdl:part name="Password" type="s:string" />
    <wsdl:part name="Originator" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SendOriginatorUnlockCodeHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="UnlockOriginatorHttpGetIn">
    <wsdl:part name="UserKey" type="s:string" />
    <wsdl:part name="Password" type="s:string" />
    <wsdl:part name="Originator" type="s:string" />
    <wsdl:part name="OriginatorUnlockCode" type="s:string" />
    <wsdl:part name="AffiliateId" type="s:string" />
  </wsdl:message>
  <wsdl:message name="UnlockOriginatorHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="CheckOriginatorAuthorizationHttpGetIn">
    <wsdl:part name="UserKey" type="s:string" />
    <wsdl:part name="Password" type="s:string" />
    <wsdl:part name="Originator" type="s:string" />
  </wsdl:message>
  <wsdl:message name="CheckOriginatorAuthorizationHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="VerifyTokenHttpGetIn">
    <wsdl:part name="UserKey" type="s:string" />
    <wsdl:part name="Password" type="s:string" />
    <wsdl:part name="PhoneNumber" type="s:string" />
    <wsdl:part name="TokenReference" type="s:string" />
    <wsdl:part name="VerificationCode" type="s:string" />
  </wsdl:message>
  <wsdl:message name="VerifyTokenHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="SendTokenSMSHttpGetIn">
    <wsdl:part name="UserKey" type="s:string" />
    <wsdl:part name="Password" type="s:string" />
    <wsdl:part name="Originator" type="s:string" />
    <wsdl:part name="Recipients" type="s:string" />
    <wsdl:part name="MessageData" type="s:string" />
    <wsdl:part name="TokenReference" type="s:string" />
    <wsdl:part name="TokenValidity" type="s:string" />
    <wsdl:part name="TokenMask" type="s:string" />
    <wsdl:part name="VerificationCode" type="s:string" />
    <wsdl:part name="TokenCaseSensitive" type="s:string" />
    <wsdl:part name="URLBufferedMessageNotification" type="s:string" />
    <wsdl:part name="URLDeliveryNotification" type="s:string" />
    <wsdl:part name="URLNonDeliveryNotification" type="s:string" />
    <wsdl:part name="AffiliateId" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SendTokenSMSHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="InquireDeliveryNotificationsHttpGetIn">
    <wsdl:part name="UserKey" type="s:string" />
    <wsdl:part name="Password" type="s:string" />
    <wsdl:part name="TransactionReferenceNumbers" type="s:string" />
  </wsdl:message>
  <wsdl:message name="InquireDeliveryNotificationsHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="GetStatusCodeDescriptionHttpGetIn">
    <wsdl:part name="StatusCode" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetStatusCodeDescriptionHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="VersionInfoHttpGetIn" />
  <wsdl:message name="VersionInfoHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="SimpleWAPPushHttpPostIn">
    <wsdl:part name="UserKey" type="s:string" />
    <wsdl:part name="Password" type="s:string" />
    <wsdl:part name="Recipients" type="s:string" />
    <wsdl:part name="Originator" type="s:string" />
    <wsdl:part name="WapDescription" type="s:string" />
    <wsdl:part name="WapURL" type="s:string" />
    <wsdl:part name="DeferredDeliveryTime" type="s:string" />
    <wsdl:part name="FlashingSMS" type="s:string" />
    <wsdl:part name="TimeZone" type="s:string" />
    <wsdl:part name="URLBufferedMessageNotification" type="s:string" />
    <wsdl:part name="URLDeliveryNotification" type="s:string" />
    <wsdl:part name="URLNonDeliveryNotification" type="s:string" />
    <wsdl:part name="AffiliateId" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SimpleWAPPushHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="SendSimpleTextSMSHttpPostIn">
    <wsdl:part name="UserKey" type="s:string" />
    <wsdl:part name="Password" type="s:string" />
    <wsdl:part name="Recipients" type="s:string" />
    <wsdl:part name="Originator" type="s:string" />
    <wsdl:part name="MessageText" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SendSimpleTextSMSHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="SendTextSMSHttpPostIn">
    <wsdl:part name="UserKey" type="s:string" />
    <wsdl:part name="Password" type="s:string" />
    <wsdl:part name="Recipients" type="s:string" />
    <wsdl:part name="Originator" type="s:string" />
    <wsdl:part name="MessageText" type="s:string" />
    <wsdl:part name="DeferredDeliveryTime" type="s:string" />
    <wsdl:part name="FlashingSMS" type="s:string" />
    <wsdl:part name="TimeZone" type="s:string" />
    <wsdl:part name="URLBufferedMessageNotification" type="s:string" />
    <wsdl:part name="URLDeliveryNotification" type="s:string" />
    <wsdl:part name="URLNonDeliveryNotification" type="s:string" />
    <wsdl:part name="AffiliateId" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SendTextSMSHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="SendUnicodeSMSHttpPostIn">
    <wsdl:part name="UserKey" type="s:string" />
    <wsdl:part name="Password" type="s:string" />
    <wsdl:part name="Recipients" type="s:string" />
    <wsdl:part name="Originator" type="s:string" />
    <wsdl:part name="MessageText" type="s:string" />
    <wsdl:part name="DeferredDeliveryTime" type="s:string" />
    <wsdl:part name="FlashingSMS" type="s:string" />
    <wsdl:part name="TimeZone" type="s:string" />
    <wsdl:part name="URLBufferedMessageNotification" type="s:string" />
    <wsdl:part name="URLDeliveryNotification" type="s:string" />
    <wsdl:part name="URLNonDeliveryNotification" type="s:string" />
    <wsdl:part name="AffiliateId" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SendUnicodeSMSHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="CheckCreditsHttpPostIn">
    <wsdl:part name="UserKey" type="s:string" />
    <wsdl:part name="Password" type="s:string" />
  </wsdl:message>
  <wsdl:message name="CheckCreditsHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="SendOriginatorUnlockCodeHttpPostIn">
    <wsdl:part name="UserKey" type="s:string" />
    <wsdl:part name="Password" type="s:string" />
    <wsdl:part name="Originator" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SendOriginatorUnlockCodeHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="UnlockOriginatorHttpPostIn">
    <wsdl:part name="UserKey" type="s:string" />
    <wsdl:part name="Password" type="s:string" />
    <wsdl:part name="Originator" type="s:string" />
    <wsdl:part name="OriginatorUnlockCode" type="s:string" />
    <wsdl:part name="AffiliateId" type="s:string" />
  </wsdl:message>
  <wsdl:message name="UnlockOriginatorHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="CheckOriginatorAuthorizationHttpPostIn">
    <wsdl:part name="UserKey" type="s:string" />
    <wsdl:part name="Password" type="s:string" />
    <wsdl:part name="Originator" type="s:string" />
  </wsdl:message>
  <wsdl:message name="CheckOriginatorAuthorizationHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="VerifyTokenHttpPostIn">
    <wsdl:part name="UserKey" type="s:string" />
    <wsdl:part name="Password" type="s:string" />
    <wsdl:part name="PhoneNumber" type="s:string" />
    <wsdl:part name="TokenReference" type="s:string" />
    <wsdl:part name="VerificationCode" type="s:string" />
  </wsdl:message>
  <wsdl:message name="VerifyTokenHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="SendTokenSMSHttpPostIn">
    <wsdl:part name="UserKey" type="s:string" />
    <wsdl:part name="Password" type="s:string" />
    <wsdl:part name="Originator" type="s:string" />
    <wsdl:part name="Recipients" type="s:string" />
    <wsdl:part name="MessageData" type="s:string" />
    <wsdl:part name="TokenReference" type="s:string" />
    <wsdl:part name="TokenValidity" type="s:string" />
    <wsdl:part name="TokenMask" type="s:string" />
    <wsdl:part name="VerificationCode" type="s:string" />
    <wsdl:part name="TokenCaseSensitive" type="s:string" />
    <wsdl:part name="URLBufferedMessageNotification" type="s:string" />
    <wsdl:part name="URLDeliveryNotification" type="s:string" />
    <wsdl:part name="URLNonDeliveryNotification" type="s:string" />
    <wsdl:part name="AffiliateId" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SendTokenSMSHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="InquireDeliveryNotificationsHttpPostIn">
    <wsdl:part name="UserKey" type="s:string" />
    <wsdl:part name="Password" type="s:string" />
    <wsdl:part name="TransactionReferenceNumbers" type="s:string" />
  </wsdl:message>
  <wsdl:message name="InquireDeliveryNotificationsHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="GetStatusCodeDescriptionHttpPostIn">
    <wsdl:part name="StatusCode" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetStatusCodeDescriptionHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="VersionInfoHttpPostIn" />
  <wsdl:message name="VersionInfoHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:portType name="ASPSMSX2Soap">
    <wsdl:operation name="SimpleWAPPush">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Send Simple WAP Push. Multiple Recipients syntax: Recipient1:TRN1;Recipient2:TRN2;... &lt;br&gt;Returns StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:SimpleWAPPushSoapIn" />
      <wsdl:output message="tns:SimpleWAPPushSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="SendSimpleTextSMS">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Send Text Messages. Multiple Recipients syntax: Recipient1;Recipient2;Recipient3... &lt;br&gt;Returns StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:SendSimpleTextSMSSoapIn" />
      <wsdl:output message="tns:SendSimpleTextSMSSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="SendTextSMS">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Send Text Messages. Multiple Recipients syntax: Recipient1:TRN1;Recipient2:TRN2;... &lt;br&gt;Returns StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:SendTextSMSSoapIn" />
      <wsdl:output message="tns:SendTextSMSSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="SendUnicodeSMS">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Send Unicode Messages. Multiple Recipients syntax: Recipient1:TRN1;Recipient2:TRN2;... &lt;br&gt;Returns StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:SendUnicodeSMSSoapIn" />
      <wsdl:output message="tns:SendUnicodeSMSSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="CheckCredits">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Check available credits. &lt;br&gt;Returns StatusCode:Number or Credits:Count</wsdl:documentation>
      <wsdl:input message="tns:CheckCreditsSoapIn" />
      <wsdl:output message="tns:CheckCreditsSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="SendOriginatorUnlockCode">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Send a originator unlock code to mobile phone. &lt;br&gt;Returns StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:SendOriginatorUnlockCodeSoapIn" />
      <wsdl:output message="tns:SendOriginatorUnlockCodeSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="UnlockOriginator">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Unlock Originator. &lt;br&gt;Returns StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:UnlockOriginatorSoapIn" />
      <wsdl:output message="tns:UnlockOriginatorSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="CheckOriginatorAuthorization">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Check originator authorization. &lt;br&gt;Returns StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:CheckOriginatorAuthorizationSoapIn" />
      <wsdl:output message="tns:CheckOriginatorAuthorizationSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="VerifyToken">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Verify/Check Security Token. &lt;br&gt;Returns StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:VerifyTokenSoapIn" />
      <wsdl:output message="tns:VerifyTokenSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="SendTokenSMS">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Send Security Token to mobile recipient. Multiple Recipients syntax: Recipient1:TRN1;Recipient2:TRN2;... &lt;br&gt;Returns StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:SendTokenSMSSoapIn" />
      <wsdl:output message="tns:SendTokenSMSSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="InquireDeliveryNotifications">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Inquire Delivery Notifications. Multiple TransactionReferenceNumbers delimited by ;&lt;br&gt;Returns DeliveryNotifications or on error StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:InquireDeliveryNotificationsSoapIn" />
      <wsdl:output message="tns:InquireDeliveryNotificationsSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetStatusCodeDescription">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Get Description for StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:GetStatusCodeDescriptionSoapIn" />
      <wsdl:output message="tns:GetStatusCodeDescriptionSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="VersionInfo">
      <wsdl:input message="tns:VersionInfoSoapIn" />
      <wsdl:output message="tns:VersionInfoSoapOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:portType name="ASPSMSX2HttpGet">
    <wsdl:operation name="SimpleWAPPush">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Send Simple WAP Push. Multiple Recipients syntax: Recipient1:TRN1;Recipient2:TRN2;... &lt;br&gt;Returns StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:SimpleWAPPushHttpGetIn" />
      <wsdl:output message="tns:SimpleWAPPushHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="SendSimpleTextSMS">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Send Text Messages. Multiple Recipients syntax: Recipient1;Recipient2;Recipient3... &lt;br&gt;Returns StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:SendSimpleTextSMSHttpGetIn" />
      <wsdl:output message="tns:SendSimpleTextSMSHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="SendTextSMS">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Send Text Messages. Multiple Recipients syntax: Recipient1:TRN1;Recipient2:TRN2;... &lt;br&gt;Returns StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:SendTextSMSHttpGetIn" />
      <wsdl:output message="tns:SendTextSMSHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="SendUnicodeSMS">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Send Unicode Messages. Multiple Recipients syntax: Recipient1:TRN1;Recipient2:TRN2;... &lt;br&gt;Returns StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:SendUnicodeSMSHttpGetIn" />
      <wsdl:output message="tns:SendUnicodeSMSHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="CheckCredits">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Check available credits. &lt;br&gt;Returns StatusCode:Number or Credits:Count</wsdl:documentation>
      <wsdl:input message="tns:CheckCreditsHttpGetIn" />
      <wsdl:output message="tns:CheckCreditsHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="SendOriginatorUnlockCode">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Send a originator unlock code to mobile phone. &lt;br&gt;Returns StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:SendOriginatorUnlockCodeHttpGetIn" />
      <wsdl:output message="tns:SendOriginatorUnlockCodeHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="UnlockOriginator">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Unlock Originator. &lt;br&gt;Returns StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:UnlockOriginatorHttpGetIn" />
      <wsdl:output message="tns:UnlockOriginatorHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="CheckOriginatorAuthorization">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Check originator authorization. &lt;br&gt;Returns StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:CheckOriginatorAuthorizationHttpGetIn" />
      <wsdl:output message="tns:CheckOriginatorAuthorizationHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="VerifyToken">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Verify/Check Security Token. &lt;br&gt;Returns StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:VerifyTokenHttpGetIn" />
      <wsdl:output message="tns:VerifyTokenHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="SendTokenSMS">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Send Security Token to mobile recipient. Multiple Recipients syntax: Recipient1:TRN1;Recipient2:TRN2;... &lt;br&gt;Returns StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:SendTokenSMSHttpGetIn" />
      <wsdl:output message="tns:SendTokenSMSHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="InquireDeliveryNotifications">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Inquire Delivery Notifications. Multiple TransactionReferenceNumbers delimited by ;&lt;br&gt;Returns DeliveryNotifications or on error StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:InquireDeliveryNotificationsHttpGetIn" />
      <wsdl:output message="tns:InquireDeliveryNotificationsHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetStatusCodeDescription">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Get Description for StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:GetStatusCodeDescriptionHttpGetIn" />
      <wsdl:output message="tns:GetStatusCodeDescriptionHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="VersionInfo">
      <wsdl:input message="tns:VersionInfoHttpGetIn" />
      <wsdl:output message="tns:VersionInfoHttpGetOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:portType name="ASPSMSX2HttpPost">
    <wsdl:operation name="SimpleWAPPush">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Send Simple WAP Push. Multiple Recipients syntax: Recipient1:TRN1;Recipient2:TRN2;... &lt;br&gt;Returns StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:SimpleWAPPushHttpPostIn" />
      <wsdl:output message="tns:SimpleWAPPushHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="SendSimpleTextSMS">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Send Text Messages. Multiple Recipients syntax: Recipient1;Recipient2;Recipient3... &lt;br&gt;Returns StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:SendSimpleTextSMSHttpPostIn" />
      <wsdl:output message="tns:SendSimpleTextSMSHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="SendTextSMS">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Send Text Messages. Multiple Recipients syntax: Recipient1:TRN1;Recipient2:TRN2;... &lt;br&gt;Returns StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:SendTextSMSHttpPostIn" />
      <wsdl:output message="tns:SendTextSMSHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="SendUnicodeSMS">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Send Unicode Messages. Multiple Recipients syntax: Recipient1:TRN1;Recipient2:TRN2;... &lt;br&gt;Returns StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:SendUnicodeSMSHttpPostIn" />
      <wsdl:output message="tns:SendUnicodeSMSHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="CheckCredits">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Check available credits. &lt;br&gt;Returns StatusCode:Number or Credits:Count</wsdl:documentation>
      <wsdl:input message="tns:CheckCreditsHttpPostIn" />
      <wsdl:output message="tns:CheckCreditsHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="SendOriginatorUnlockCode">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Send a originator unlock code to mobile phone. &lt;br&gt;Returns StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:SendOriginatorUnlockCodeHttpPostIn" />
      <wsdl:output message="tns:SendOriginatorUnlockCodeHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="UnlockOriginator">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Unlock Originator. &lt;br&gt;Returns StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:UnlockOriginatorHttpPostIn" />
      <wsdl:output message="tns:UnlockOriginatorHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="CheckOriginatorAuthorization">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Check originator authorization. &lt;br&gt;Returns StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:CheckOriginatorAuthorizationHttpPostIn" />
      <wsdl:output message="tns:CheckOriginatorAuthorizationHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="VerifyToken">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Verify/Check Security Token. &lt;br&gt;Returns StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:VerifyTokenHttpPostIn" />
      <wsdl:output message="tns:VerifyTokenHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="SendTokenSMS">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Send Security Token to mobile recipient. Multiple Recipients syntax: Recipient1:TRN1;Recipient2:TRN2;... &lt;br&gt;Returns StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:SendTokenSMSHttpPostIn" />
      <wsdl:output message="tns:SendTokenSMSHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="InquireDeliveryNotifications">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Inquire Delivery Notifications. Multiple TransactionReferenceNumbers delimited by ;&lt;br&gt;Returns DeliveryNotifications or on error StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:InquireDeliveryNotificationsHttpPostIn" />
      <wsdl:output message="tns:InquireDeliveryNotificationsHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetStatusCodeDescription">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Get Description for StatusCode:Number</wsdl:documentation>
      <wsdl:input message="tns:GetStatusCodeDescriptionHttpPostIn" />
      <wsdl:output message="tns:GetStatusCodeDescriptionHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="VersionInfo">
      <wsdl:input message="tns:VersionInfoHttpPostIn" />
      <wsdl:output message="tns:VersionInfoHttpPostOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:binding name="ASPSMSX2Soap" type="tns:ASPSMSX2Soap">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="SimpleWAPPush">
      <soap:operation soapAction="https://webservice.aspsms.com/aspsmsx2.asmx/SimpleWAPPush" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendSimpleTextSMS">
      <soap:operation soapAction="https://webservice.aspsms.com/aspsmsx2.asmx/SendSimpleTextSMS" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendTextSMS">
      <soap:operation soapAction="https://webservice.aspsms.com/aspsmsx2.asmx/SendTextSMS" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendUnicodeSMS">
      <soap:operation soapAction="https://webservice.aspsms.com/aspsmsx2.asmx/SendUnicodeSMS" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CheckCredits">
      <soap:operation soapAction="https://webservice.aspsms.com/aspsmsx2.asmx/CheckCredits" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendOriginatorUnlockCode">
      <soap:operation soapAction="https://webservice.aspsms.com/aspsmsx2.asmx/SendOriginatorUnlockCode" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="UnlockOriginator">
      <soap:operation soapAction="https://webservice.aspsms.com/aspsmsx2.asmx/UnlockOriginator" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CheckOriginatorAuthorization">
      <soap:operation soapAction="https://webservice.aspsms.com/aspsmsx2.asmx/CheckOriginatorAuthorization" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="VerifyToken">
      <soap:operation soapAction="https://webservice.aspsms.com/aspsmsx2.asmx/VerifyToken" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendTokenSMS">
      <soap:operation soapAction="https://webservice.aspsms.com/aspsmsx2.asmx/SendTokenSMS" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="InquireDeliveryNotifications">
      <soap:operation soapAction="https://webservice.aspsms.com/aspsmsx2.asmx/InquireDeliveryNotifications" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetStatusCodeDescription">
      <soap:operation soapAction="https://webservice.aspsms.com/aspsmsx2.asmx/GetStatusCodeDescription" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="VersionInfo">
      <soap:operation soapAction="https://webservice.aspsms.com/aspsmsx2.asmx/VersionInfo" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="ASPSMSX2Soap12" type="tns:ASPSMSX2Soap">
    <soap12:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="SimpleWAPPush">
      <soap12:operation soapAction="https://webservice.aspsms.com/aspsmsx2.asmx/SimpleWAPPush" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendSimpleTextSMS">
      <soap12:operation soapAction="https://webservice.aspsms.com/aspsmsx2.asmx/SendSimpleTextSMS" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendTextSMS">
      <soap12:operation soapAction="https://webservice.aspsms.com/aspsmsx2.asmx/SendTextSMS" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendUnicodeSMS">
      <soap12:operation soapAction="https://webservice.aspsms.com/aspsmsx2.asmx/SendUnicodeSMS" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CheckCredits">
      <soap12:operation soapAction="https://webservice.aspsms.com/aspsmsx2.asmx/CheckCredits" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendOriginatorUnlockCode">
      <soap12:operation soapAction="https://webservice.aspsms.com/aspsmsx2.asmx/SendOriginatorUnlockCode" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="UnlockOriginator">
      <soap12:operation soapAction="https://webservice.aspsms.com/aspsmsx2.asmx/UnlockOriginator" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CheckOriginatorAuthorization">
      <soap12:operation soapAction="https://webservice.aspsms.com/aspsmsx2.asmx/CheckOriginatorAuthorization" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="VerifyToken">
      <soap12:operation soapAction="https://webservice.aspsms.com/aspsmsx2.asmx/VerifyToken" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendTokenSMS">
      <soap12:operation soapAction="https://webservice.aspsms.com/aspsmsx2.asmx/SendTokenSMS" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="InquireDeliveryNotifications">
      <soap12:operation soapAction="https://webservice.aspsms.com/aspsmsx2.asmx/InquireDeliveryNotifications" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetStatusCodeDescription">
      <soap12:operation soapAction="https://webservice.aspsms.com/aspsmsx2.asmx/GetStatusCodeDescription" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="VersionInfo">
      <soap12:operation soapAction="https://webservice.aspsms.com/aspsmsx2.asmx/VersionInfo" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="ASPSMSX2HttpGet" type="tns:ASPSMSX2HttpGet">
    <http:binding verb="GET" />
    <wsdl:operation name="SimpleWAPPush">
      <http:operation location="/SimpleWAPPush" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendSimpleTextSMS">
      <http:operation location="/SendSimpleTextSMS" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendTextSMS">
      <http:operation location="/SendTextSMS" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendUnicodeSMS">
      <http:operation location="/SendUnicodeSMS" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CheckCredits">
      <http:operation location="/CheckCredits" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendOriginatorUnlockCode">
      <http:operation location="/SendOriginatorUnlockCode" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="UnlockOriginator">
      <http:operation location="/UnlockOriginator" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CheckOriginatorAuthorization">
      <http:operation location="/CheckOriginatorAuthorization" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="VerifyToken">
      <http:operation location="/VerifyToken" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendTokenSMS">
      <http:operation location="/SendTokenSMS" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="InquireDeliveryNotifications">
      <http:operation location="/InquireDeliveryNotifications" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetStatusCodeDescription">
      <http:operation location="/GetStatusCodeDescription" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="VersionInfo">
      <http:operation location="/VersionInfo" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="ASPSMSX2HttpPost" type="tns:ASPSMSX2HttpPost">
    <http:binding verb="POST" />
    <wsdl:operation name="SimpleWAPPush">
      <http:operation location="/SimpleWAPPush" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendSimpleTextSMS">
      <http:operation location="/SendSimpleTextSMS" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendTextSMS">
      <http:operation location="/SendTextSMS" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendUnicodeSMS">
      <http:operation location="/SendUnicodeSMS" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CheckCredits">
      <http:operation location="/CheckCredits" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendOriginatorUnlockCode">
      <http:operation location="/SendOriginatorUnlockCode" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="UnlockOriginator">
      <http:operation location="/UnlockOriginator" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CheckOriginatorAuthorization">
      <http:operation location="/CheckOriginatorAuthorization" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="VerifyToken">
      <http:operation location="/VerifyToken" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendTokenSMS">
      <http:operation location="/SendTokenSMS" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="InquireDeliveryNotifications">
      <http:operation location="/InquireDeliveryNotifications" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetStatusCodeDescription">
      <http:operation location="/GetStatusCodeDescription" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="VersionInfo">
      <http:operation location="/VersionInfo" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:service name="ASPSMSX2">
    <wsdl:port name="ASPSMSX2Soap" binding="tns:ASPSMSX2Soap">
      <soap:address location="https://webservice.aspsms.com/aspsmsx2.asmx" />
    </wsdl:port>
    <wsdl:port name="ASPSMSX2Soap12" binding="tns:ASPSMSX2Soap12">
      <soap12:address location="https://webservice.aspsms.com/aspsmsx2.asmx" />
    </wsdl:port>
    <wsdl:port name="ASPSMSX2HttpGet" binding="tns:ASPSMSX2HttpGet">
      <http:address location="https://webservice.aspsms.com/aspsmsx2.asmx" />
    </wsdl:port>
    <wsdl:port name="ASPSMSX2HttpPost" binding="tns:ASPSMSX2HttpPost">
      <http:address location="https://webservice.aspsms.com/aspsmsx2.asmx" />
    </wsdl:port>
  </wsdl:service>
</wsdl:definitions>