
package net.pwms.domain;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mayanton
 */

@XmlRootElement
public class Product implements Serializable{
    
    /*P<DetailSequenceNum>1</DetailSequenceNum>
                <ProductCode>00001AKFB</ProductCode>
                <ProductDescription>ALKINA Kin Field Blend Red BarossaValley</ProductDescription>
                <ProductMobileDescription>ALKINA Kin Field Ble</ProductMobileDescription> */
    
    private Integer detailSequenceNum;
    private String productCode;
    private String productDescription;
    private String productMobileDescription;

    public Product() {
        super();
    }
    
    

    public Integer getDetailSequenceNum() {
        return detailSequenceNum;
    }

    public void setDetailSequenceNum(Integer detailSequenceNum) {
        this.detailSequenceNum = detailSequenceNum;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductMobileDescription() {
        return productMobileDescription;
    }

    public void setProductMobileDescription(String productMobileDescription) {
        this.productMobileDescription = productMobileDescription;
    }
    
    
    
}
