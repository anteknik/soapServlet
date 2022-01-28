package net.pwms.webservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import net.pwms.domain.Product;

/**
 *
 * @author mayanton
 */
@WebService
public class Request {

    /**
     * Web service operation
     */
    @WebMethod
    public Product newItemToWMS(@WebParam(name = "DetailSequenceNum") int sequenceNum,
            @WebParam(name = "ProductCode") String code, @WebParam(name = "ProductDescription") String productDescription,
            @WebParam(name = "ProductMobileDescription") String productMobileDescription) {

        Product product = new Product();
        product.setDetailSequenceNum(sequenceNum);
        product.setProductCode(code);
        product.setProductDescription(productDescription);
        product.setProductMobileDescription(productMobileDescription);
        return product;
    }

}
