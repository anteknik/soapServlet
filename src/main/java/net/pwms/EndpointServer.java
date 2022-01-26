
package net.pwms;

import javax.xml.ws.Endpoint;
import net.pwms.webservices.Request;

/**
 *
 * @author mayanton
 */
public class EndpointServer {
    
    public static void main(String[] args) {
		Endpoint.publish("http://localhost:8181/NewItemToWMS", new Request());
		System.out.println("Request Services Started!");
	}
    /**
         <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:impl="http://webservices.pwms.net/">
        <soapenv:Header/>
        <soapenv:Body>
        <impl:newItemToWMS>
         <DetailSequenceNum>1</DetailSequenceNum>
                <ProductCode>00001AKFB</ProductCode>
                <ProductDescription>ALKINA Kin Field Blend Red BarossaValley</ProductDescription>
                <ProductMobileDescription>ALKINA Kin Field Ble</ProductMobileDescription>
         </impl:newItemToWMS>
        </soapenv:Body>
        </soapenv:Envelope>
     
     */
}
