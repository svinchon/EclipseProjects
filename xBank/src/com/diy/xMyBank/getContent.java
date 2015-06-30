package com.diy.xMyBank;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.AttachmentPart;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;

@WebServlet({"/getContent"})
public class getContent
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
      String contentId = request.getParameter("contentId");
      String soap_message = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:con=\"http://eas.documentum.emc.com/service/model/content\">   <soapenv:Header/>   <soapenv:Body>      <con:contentRequest>         <serviceContext>            <languageCode>en_US</languageCode>            <consumerApplication>eas_gui</consumerApplication>            <userName>dmadmin</userName>            <profiles>               <profile>investecinvoices_role</profile>            </profiles>         </serviceContext>         <contentId>--contentId--</contentId>      </con:contentRequest>   </soapenv:Body></soapenv:Envelope>";
      
      soap_message = soap_message.replace("--contentId--", contentId);
      Log(soap_message);
      SOAPConnectionFactory sfc = SOAPConnectionFactory.newInstance();
      SOAPConnection connection = sfc.createConnection();
      InputStream is = new ByteArrayInputStream(soap_message.getBytes());
      SOAPMessage soap_request = MessageFactory.newInstance().createMessage(null, is);
      URL endpoint = new URL("http://localhost:8080/eas-services/services/ContentService");
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
          
          response.setContentType("application/pdf");
          response.getOutputStream().write(b);
        }
      }
      else
      {
        Log("nnnnnnnnnnnnnn");
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
