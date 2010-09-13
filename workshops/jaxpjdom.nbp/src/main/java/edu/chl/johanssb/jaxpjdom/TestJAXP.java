package edu.chl.johanssb.jaxpjdom;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author hajo
 */
public final class TestJAXP {

    private TestJAXP() {
    }

    /*
     * Dump DOM tree to standard out 
     */
    public static void dump() {
        //get the factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            //Parse using builder to get DOM representation of the XML file
            org.w3c.dom.Document dom = db.parse(Main.CUSTOMER_LIST);

            NodeList nl = dom.getElementsByTagName("simpleCustomer");
            for(int i = 0; i < nl.getLength(); i++){

                System.out.print("Customer ID: " + nl.item(i).getAttributes().getNamedItem("id").getNodeValue());

                NodeList info = nl.item(i).getChildNodes();

                for(int j = 0; j < info.getLength(); j++){
                    
                    System.out.print(info.item(j).getTextContent());
                }

            }

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (SAXException se) {
            se.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /*
     * Node search using XPath 
     */
    public static void search() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //factory.setValidating(true);
        //factory.setNamespaceAware(true);

        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = null;
        try {
            File file = new File(Main.SLIDESHOW);
            document = builder.parse(file);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // a)
        XPath xpath = XPathFactory.newInstance().newXPath();
        NodeList nodes = null;
        try {
            nodes = (NodeList) xpath.evaluate("//@type", document, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i));
        }

        // b)
        try {
            nodes = (NodeList) xpath.evaluate("//slide[title='Overview']", document,
                    XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getTextContent());
        }

        // c)
        try {
            nodes = (NodeList) xpath.evaluate("//item", document,
                    XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getTextContent());
        }
    }
}

