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
import java.io.FileOutputStream;
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
   private static final long pid = ProcessHandle.current().pid();
   private static final String tempDir = "tmp";
   private static final String rescDir = "things";
   private long transactionId = 0;

   public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      PrintWriter out = response.getWriter();
      out.println("SoapServlet is Running at " + LocalDateTime.now());
      out.flush();
      out.close();
   }

   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      PrintWriter out = response.getWriter();
      PropertyManager pm = new PropertyManager(SoapServlet.rescDir + "/pwserver");
      String soapAction = request.getHeader("SOAPAction");
      String scriptAction = pm.get(soapAction + ".Action");
      String shellAction = pm.get("pwsOSProcessPrefix") + " " + scriptAction;

      if (scriptAction.length() > 0) { // valid message type
         String requestBody = getMessageBody(request);
         out.println("SoapServlet is Running on pid " + SoapServlet.pid + " at " + LocalDateTime.now());
         out.println("SoapServlet request # " + ++this.transactionId);
         out.println("Request action is " + soapAction);
         out.println("Shell action is " + shellAction);
         out.println("Request body:");
         out.print(requestBody);
         // writeMessageToFile(requestBody, soapAction);
         // stream body to stdout
         out.println("Shell Action result:");
         out.print(invokeShellAction(shellAction));

         // <<todo>>
         // * get soap body better
         // check namespace
         // add other message types
         // logging - l4j
         // * buiuld to single jar with embedded servlet
         // <</todo>>

         // response.setContentType("text/xml");
         // response.setCharacterEncoding("UTF-8");
         // response.getWriter().write(handleSoapMessage(request));
         out.flush();
         out.close();
      }
   }

   private String getMessageBody(HttpServletRequest request) {
      StringBuffer sb = new StringBuffer();
      String line = null;
      String returnString = null;
      boolean appendFlag = false;
      try {
         BufferedReader reader = request.getReader();
         while ((line = reader.readLine()) != null) {
            if (line.toLowerCase().contains(":body>"))
               appendFlag ^= true; // toggle flag
            else {
               if (appendFlag)
                  sb.append(line.trim() + System.lineSeparator());
            }
         }
         returnString = sb.toString();
      } catch (Exception e) {
         returnString = "Exception " + e;
      }
      return returnString;
   }

   private void writeMessageToFile(String body, String name) {
      StringBuilder fileName = new StringBuilder(SoapServlet.tempDir);
      fileName.append(name);
      fileName.append("_");
      fileName.append(SoapServlet.pid + this.transactionId);
      fileName.append("_in.xml");
      try {
         PrintWriter pw = new PrintWriter(fileName.toString());
         pw.write(body);
         pw.close();
      } catch (IOException e) {
         System.out.println("Exception:" + e);
      }
   }

   private String invokeShellAction(String shellAction) {
      StringBuilder sb = new StringBuilder();
      String returnValue = null;
      try {
         Runtime runtime = Runtime.getRuntime();
         Process proc = runtime.exec(shellAction);
         InputStream is = proc.getInputStream();
         InputStreamReader isr = new InputStreamReader(is);
         BufferedReader reader = new BufferedReader(isr);
         String line;
         while ((line = reader.readLine()) != null) {
            sb.append(line + System.lineSeparator());
         }
         reader.close();
         proc.getOutputStream().close();
         returnValue = sb.toString();
      } catch (Exception e) {
         returnValue = "Exception " + e;
      }
      return returnValue;

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