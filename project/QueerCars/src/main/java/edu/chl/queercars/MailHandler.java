/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.queercars;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author mviktor
 */
public class MailHandler {

    Properties props;
    Message message;

    /**
     * creates a simple mail handler with default values for mailserver
     */
    public MailHandler() {
        try {
            props = new Properties();
            props.put("mail.smtp.host", "mail.chalmers.se");
            Session mailSession = Session.getDefaultInstance(props, null);
            message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress("queercars@gmail.com"));
        } catch (MessagingException ex) {
            System.out.println("Something bad happened in mail constructor...\n");
            ex.printStackTrace();
        }
    }

    /**
     * 
     * @param email the emailadress to send to
     * @return true if mail sucsessfully was sent
     */
    public boolean sendRentalInformation(InternetAddress email) {
        try {
            message.setRecipient(Message.RecipientType.TO, email);
            message.setSubject("QueerCars - booking information");

            String messageBody = "Thank you for using QueerCars as your personal car rental!\n" + "Here is your booking:\n";

            //TODO get some real info to messageBody
            messageBody += "TODO TODO TODO TODO TODO TODO REAL INFO REAL INFO REAL INFO REAL INFO\n";

            messageBody = messageBody + "Have a safe trip (we want the car back in one piece)\n\n" + "//QueerCars";
            message.setText(messageBody);
            System.out.println(messageBody);
            Transport.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @param email the emailadress to send to
     * @return true if mail sucsessfully was sent
     */
    public boolean sendNewAccountInfo(InternetAddress email) {
        try {
            message.setRecipient(Message.RecipientType.TO, email);
            message.setSubject("QueerCars - account information");
            String messageBody = "Thank you for creating a QueerCar account!\n" + "Here is your account information:\n";
            //TODO get some real info to messageBody
            messageBody += "account id: " + "GETREALINFO" + "\n password: " + "GETREALINFO\n";
            messageBody = messageBody + "We have a nice collection of cars, come to our webpage and check them out!\n\n" + "//QueerCars";
            message.setText(messageBody);
            System.out.println(messageBody);
            Transport.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}