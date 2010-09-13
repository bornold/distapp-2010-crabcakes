/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.johanssb.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author flipmo
 */
@XmlType(name = "Class4Schema", propOrder = {"number","number2","text"})
@XmlRootElement
public class Class4Schema {
    @XmlElement(required = true)
    protected int number;
    @XmlElement(required = true)
    protected String text;
    @XmlAttribute
    @XmlSchemaType(name = "float")
    protected float number2;

    public float getNumber2() {
        return number2;
    }
    public void setNumber2(float number2) {
        this.number2 = number2;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public int getNumber(){
        return number;
    }
    public void setNumber(int value){
        this.number = value;
    }
}
