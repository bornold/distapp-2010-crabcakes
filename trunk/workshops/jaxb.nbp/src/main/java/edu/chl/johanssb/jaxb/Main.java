package edu.chl.johanssb.jaxb;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import org.xml.sax.SAXException;

/**
 * RUN teh scripts to conver between Java classes
 *
 */
public class Main{
    public static void main( String[] args ){
        try {
            System.out.println("*** NOTE: See scripts xcj.sh and schemagen.sh ***");

            Marshal.doMarshal();
            Unmarshal.doUnmarshal();
        } catch (SAXException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
