package com.diy.xMyBank;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.soap.AttachmentPart;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;
import org.json.JSONObject;
import org.json.XML;

@WebServlet({"/getDataFromIAAsJSON"})
public class getDataFromIAAsJSON
  extends HttpServlet
{
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    doPost(request, response);
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    try
    {
      String accountNumber = "undefined";
      if (request.getParameter("accountnumber") != null)
      {
        accountNumber = request.getParameter("accountnumber");
      }
      else
      {
        HttpSession session = request.getSession(true);
        accountNumber = (String)session.getAttribute("accountNumber");
      }
      Log("accountnumber: '" + accountNumber + "'");
      if (accountNumber != null)
      {
        String soap_message = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:quer=\"http://eas.documentum.emc.com/service/model/query\">   <soapenv:Header/>   <soapenv:Body>      <quer:queryRequest>         <serviceContext>            <languageCode>en_US</languageCode>            <consumerApplication>eas_gui</consumerApplication>            <userName>dmadmin</userName>            <profiles>               <profile>investecinvoices_role</profile>            </profiles>         </serviceContext>         <archiveInformationCollection name=\"InvestecInvoices\"/>         <resultSchema name=\"urn:eas-samples:en:xsd:investec_statements.1.0\" version=\"\" aiuIds=\"true\" ciIds=\"true\"/>         <deliveryChannel name=\"eas_access_services\"/>         <searchQueryCriteria type=\"AND\">            <operand>               <name>AccountNumber_0</name>               <operator>Equal</operator>               <!--1 to 10000 repetitions:-->               <value>--accountNumber--</value>            </operand>            <!--operand>               <name>CallStartDate</name>               <operator>LessOrEqual</operator>               <1 to 10000 repetitions>               <value>2015-01-01</value>            </operand-->          </searchQueryCriteria>      </quer:queryRequest>   </soapenv:Body></soapenv:Envelope>";
        
        soap_message = soap_message.replace("--accountNumber--", accountNumber);
        SOAPConnectionFactory sfc = SOAPConnectionFactory.newInstance();
        SOAPConnection connection = sfc.createConnection();
        InputStream is = new ByteArrayInputStream(soap_message.getBytes());
        SOAPMessage soap_request = MessageFactory.newInstance().createMessage(null, is);
        
        URL endpoint = new URL("http://localhost:8080/eas-services/services/QueryService");
        SOAPMessage soap_response = connection.call(soap_request, endpoint);
        
        Iterator<?> iterator = soap_response.getAttachments();
        if (iterator.hasNext())
        {
          AttachmentPart attachment = (AttachmentPart)iterator.next();
          String id = attachment.getContentId();
          String type = attachment.getContentType();
          Log("Attachment " + id + " has content type " + type);
          if (type.equals("text/plain"))
          {
            Object content = attachment.getContent();
            System.out.println("Attachment contains:\n" + content);
          }
          else if (type.equals("application/octet-stream"))
          {
            ByteArrayInputStream bais = (ByteArrayInputStream)attachment.getContent();
            byte[] b = new byte[bais.available()];
            bais.read(b);
            String myXML = new String(b, "UTF-8");
            JSONObject xmlJSONObj = XML.toJSONObject(myXML);
            String jsonPrettyPrintString = xmlJSONObj.toString(4);
            
            Log(myXML);
            response.setContentType("application/json");
            response.getWriter().write(jsonPrettyPrintString);
          }
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  private void Log(String string)
  {
    System.out.println(getClass().getCanonicalName() + " => " + string);
  }
}
