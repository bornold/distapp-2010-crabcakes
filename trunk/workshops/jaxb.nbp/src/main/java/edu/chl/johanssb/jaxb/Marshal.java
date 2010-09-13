package edu.chl.johanssb.jaxb;

import edu.chl.johanssb.jaxb.po.Items;
import edu.chl.johanssb.jaxb.po.ObjectFactory;
import edu.chl.johanssb.jaxb.po.PurchaseOrderType;
import edu.chl.johanssb.jaxb.po.USAddress;
import java.math.BigDecimal;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *  From objects to XML
 *
 * NOTE Possible missing @XmlRootElement (not generated??!)
 * @author hajo
 */
public class Marshal {

    public static void doMarshal() throws JAXBException, DatatypeConfigurationException {
       
        // Using the generated ObjectFactory
        ObjectFactory objFactory = new ObjectFactory();

        PurchaseOrderType po = createOrder(objFactory);

        // Package for classes the context need to recognize
        JAXBContext jc = JAXBContext.newInstance("edu.chl.johanssb.jaxb.po");
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                Boolean.TRUE);
        // Dump XML data
        m.marshal(po, System.out);
    }

    private static USAddress createUSAddress(String... args) {
        USAddress ua = new USAddress();
        ua.setName(args[0]);
        ua.setStreet(args[1]);
        ua.setCity(args[2]);
        ua.setState(args[3]);
        ua.setZip(BigDecimal.valueOf(Long.parseLong(args[4])));
        ua.setCountry(args[5]);

        return ua;
    }

    private static Items.Item createItemType(String name, int qty, BigDecimal price,
            String comment, XMLGregorianCalendar shipDate, String partNum) {
        Items.Item i = new Items.Item();
        i.setComment(comment);
        i.setPartNum(partNum);
        i.setProductName(name);
        i.setQuantity(qty);
        i.setShipDate(shipDate);
        i.setUSPrice(price);

        return i;
    }

    private static PurchaseOrderType createOrder(ObjectFactory o) throws DatatypeConfigurationException{
        PurchaseOrderType po = o.createPurchaseOrderType();
        // TODO
        po.setOrderDate(DatatypeFactory.newInstance().newXMLGregorianCalendar());
        USAddress shipTo = createUSAddress("Alice Smith",
                "123 Maple Street",
                "Cambridge",
                "MA",
                "12345", "US");
        po.setShipTo(shipTo);
        USAddress billTo = createUSAddress("Robert Smith",
                "8 Oak Avenue",
                "Cambridge",
                "MA",
                "12345", "US");
        po.setBillTo(billTo);
        Items items = o.createItems();
        List itemList = items.getItem();
        itemList.add(createItemType(
                "Nosferatu - Special Edition (1929)",
                new Integer("5"),
                new BigDecimal("19.99"),
                null,
                null,
                "242-NO"));
        itemList.add(createItemType("The Mummy (1959)",
                new Integer("3"),
                new BigDecimal("19.98"),
                null,
                null,
                "242-MU"));
        po.setItems(items);
        return po;
    }
}

