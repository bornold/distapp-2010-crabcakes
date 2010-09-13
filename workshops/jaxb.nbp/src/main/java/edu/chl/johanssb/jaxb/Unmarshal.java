package edu.chl.johanssb.jaxb;

import edu.chl.johanssb.jaxb.po.Items.Item;
import edu.chl.johanssb.jaxb.po.PurchaseOrderType;
import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

/**
 * XML to object
 * @author hajo
 */
public class Unmarshal {

    public static void doUnmarshal() throws JAXBException, SAXException {
        // The classes must be there (run xcj.sh script to generate)
        JAXBContext jc = JAXBContext.newInstance("edu.chl.johanssb.jaxb.po");
        Unmarshaller unmarshaller = jc.createUnmarshaller();

        // Set validating
        unmarshaller.setSchema(SchemaFactory.
                newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).
                newSchema(new File("src/main/resources/po.xsd")));


        JAXBElement e = (JAXBElement) unmarshaller.unmarshal(new File("src/main/resources/anorder.xml"));
        PurchaseOrderType po = (PurchaseOrderType) e.getValue();

        System.out.println("---------------------------------");
        System.out.println("Date " + po.getOrderDate());
        System.out.println("Bill to " + po.getBillTo().getCountry());
        System.out.println("Street " + po.getShipTo().getStreet());
        for (Item i : po.getItems().getItem()) {
            System.out.println("Partnr " + i.getPartNum());
            System.out.println("Prod name " + i.getProductName());
            System.out.println("Qty " + i.getQuantity());
        }



    }
}
