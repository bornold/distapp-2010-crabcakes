package edu.chl.johanssb.jaxpjdom;
/*
 * Possible XML-W3C-DOM node types are
 *
 * - Document, Rootnode of tree
 * - DocumentFragment, A portion of the document
 * - DocumentType, Interface for entitys in document
 * - Element, an element
 * - Attr, an attribute
 * - Text, Textual content of Element or Attr
 * - Comment,  A comment <!--    -->
 * - Entity, entity
 * - EntityReference, entity reference
 * - ProcessingInstruction
 * - Notation
 * - CDData, text will not be parsed
 * 
 * Dump an xml file to stdout.
 * @author hajo
 */

public final class Main {

    public static final String CUSTOMER_LIST = "./src/main/java/edu/chl/johanssb/jaxpjdom/simpleCustomerList.xml";
    public static final String SLIDESHOW = "./src/main/java/edu/chl/johanssb/jaxpjdom/slideshow.xml";

    private Main(){
    }

    public static void main(final String[] args) {
        TestJAXP.dump();
        TestJAXP.search();

        TestJDOM.dump();
        TestJDOM.search();
    }
}

//Vixen was not here