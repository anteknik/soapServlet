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

// import org.apache.xmlbeans.XmlException;
import org.w3c.dom.Document;

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
      out.println("SoapServlet is Running at " + LocalDateTime.now());
      out.println("Request action is " + request.getHeader("SOAPAction"));
      out.println("Request body " + handleSoapMessage(request));

      // response.setContentType("text/xml");
      // response.setCharacterEncoding("UTF-8");
      // response.getWriter().write(handleSoapMessage(request));
      out.flush();
      out.close();
   }

   private String handleSoapMessage(HttpServletRequest request) {
      String returnString = null;
      StringBuffer jb = new StringBuffer();
      String line = null;
      try {
        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null)
          jb.append(line);
          returnString = jb.toString();
      } catch (Exception e) { 
         returnString = "Exception " + e;
      }
    
      // try {
      //   JSONObject jsonObject =  HTTP.toJSONObject(jb.toString());
      // } catch (JSONException e) {
      //   // crash and burn
      //   throw new IOException("Error parsing JSON request string");
      // }
      // try {
      //    StringBuffer sb = new StringBuffer();
      //    InputStream inputStream = request.getInputStream();
      //    DocumentBuilderFactory docFactory = null;
      //    DocumentBuilder docBuilder = null;
      //    Document document = null;
      //    docFactory = DocumentBuilderFactory.newInstance();
      //    docBuilder = docFactory.newDocumentBuilder();
      //    document = docBuilder.parse(inputStream);
         // returnString = document.getTextContent();
      // } catch (Exception e) {
      //    returnString = "Exception " + e;
      // }
      // } finally {
         // returnString = "Request xml generated Parsed successfully";
         return returnString;
      // }

   }
}