package edu.chl.johanssb.jaxpjdom;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jdom.Content;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;


public final class TestJDOM {

    private TestJDOM() {
    }
    /*
     * Dump DOM tree to standard out 
     */
    public static void dump() {
        // create the SAXBuilder
        SAXBuilder builder = new SAXBuilder();
        try {
            org.jdom.Document doc = builder.build(Main.CUSTOMER_LIST);

            for (Content c : (List<Content>)doc.getContent()) {
                System.out.println(c.getValue());
            }

        } catch(JDOMException e) {
            System.out.println(e.getMessage());
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /*
     * Node search using XPath 
     */
    public static void search() {
       SAXBuilder parser = new SAXBuilder();
        try {
            File file = new File(Main.SLIDESHOW);

            org.jdom.Document doc = parser.build(file);
            System.out.println(doc.getRootElement().getName());

            org.jdom.Element firstChild = (Element) doc.getRootElement().getChildren().get(0);
            System.out.println(firstChild.getAttributeValue("id"));

            /*
             * This is JDOM XPath
             * NEED JAXEN Lib for this
             */
            //A
            org.jdom.xpath.XPath xpath = org.jdom.xpath.XPath.newInstance("//@type");
            List<org.jdom.Attribute> list = xpath.selectNodes(firstChild);
            for( org.jdom.Attribute a : list ){
                System.out.println(a.getValue());
            }

            //B
            xpath = org.jdom.xpath.XPath.newInstance("//slide[title='Overview']");
            List<org.jdom.Element> newlist = xpath.selectNodes(firstChild);
            for( org.jdom.Element a : newlist ){
                System.out.println(a.getValue());
            }

            //C
            xpath = org.jdom.xpath.XPath.newInstance("//item");
            List<org.jdom.Element> lastlist = xpath.selectNodes(firstChild);
            for( org.jdom.Element a : lastlist ){
                System.out.println(a.getValue());
            }

        } catch (org.jdom.JDOMException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
 
    }
}
