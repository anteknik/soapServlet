package net.pwms;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
// import java.io.IOException;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.MessageFactory;

// import org.apache.xmlbeans.XmlException;
import org.w3c.dom.Document;
import org.xml.sax.XMLReader;

public class SoapServlet extends HttpServlet {

   private static final long serialVersionUID = 1L;

   public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      PrintWriter out = response.getWriter();
      out.println("SoapServlet is Running at " + LocalDateTime.now());
      out.flush();
      out.close();
   }

   public void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      PrintWriter out = response.getWriter();
      PropertyManager pm = new PropertyManager("pwserver");
      String soapAction = request.getHeader("SOAPAction");
      String scriptAction = pm.get(soapAction + ".Action");
      String shellAction = pm.get("pwsOSProcessPrefix") + " " + scriptAction;
      if (scriptAction.length() > 0) {
         out.println("SoapServlet is Running at " + LocalDateTime.now());
         out.println("Request action is " + soapAction);
         out.println("Shell action is " + shellAction);
         out.println("Request body " + getMessageBody(request));

         //<<todo>>
         // get soap body
         // check namespace
         // write to file ./tmp/soapAction_99999.xml
         // invoke script
         // return response from script
         // add other message types
         // logging
         //<</todo>>

         // response.setContentType("text/xml");
         // response.setCharacterEncoding("UTF-8");
         // response.getWriter().write(handleSoapMessage(request));
         out.flush();
         out.close();
      }
   }

   private String getMessageBody(HttpServletRequest request){
      StringBuffer jb = new StringBuffer();
      String line = null;
      String returnString = null;
      try {
         BufferedReader reader = request.getReader();
         while ((line = reader.readLine()) != null)
         jb.append(line + System.lineSeparator());
         returnString = jb.toString();
      } catch (Exception e) {
         returnString = "Exception " + e;
      }
      return returnString;
   }

   private String handleSoapMessage(HttpServletRequest request) {
      String returnString = null;
      // System.setProperty("jakarta.xml.soap.SAAJMetaFactory",
      // "com.sun.xml.messaging.saaj.soap.SAAJMetaFactoryImpl");

      try {
         InputStream inputStream = request.getInputStream();
         SOAPMessage message = MessageFactory.newInstance().createMessage(null, inputStream);
         Document document = message.getSOAPBody().extractContentAsDocument();

         // FileWriter output = new FileWriter("");

         returnString = document.getTextContent();
      } catch (Exception e) {
         returnString = "Exception " + e.getMessage();
      }
      // } finally {
      // returnString = "Request xml generated Parsed successfully";
      return returnString;
      // }

   }

}