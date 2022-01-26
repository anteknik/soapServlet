# Soap Server

## Update

### Phase 1

- Deploy Apache ~~Tomcat~~ Jetty web servlet [use of CFX (soap protocols) not required.]
- High level validation - is a soap message & has correct namespace (http://paperless-warehousing.com.au... [sic]) & has correct http headers
- Pass `<body`> of message through existing file+script interface to WMS
- Let WMS perform the Validation (Field lengths, Data types, String values etc)
- Ensure / review security to inhibit receiving messages from unknow sources (Secure VPN ipsec tunnel)

### Testing

- JUnit assertions
  - various message types
  - various header values
  - various message structures
- Message receipt
- Message rejection
- Port configuration
- Script invocation

### Deployment

- (1) Run both PW Legacy Soap and New server in parallel to validate message receipt
	... $ mvn clean jetty:run
	
- (2) Turn off PW Legacy Soap

### Testing

    go to branch development

    ... $ mvn clean jetty:run

    ... try to access the endpoint : http://localhost:8080/services/NewItemToWMS


    ... Request soap below
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
    ...