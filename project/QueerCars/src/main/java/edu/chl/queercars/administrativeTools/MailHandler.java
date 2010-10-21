/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.queercars.administrativeTools;

import edu.chl.queercars.Car;
import edu.chl.queercars.Customer;
import edu.chl.queercars.Invoice;
import edu.chl.queercars.Rental;
import java.util.Date;
import java.util.Properties;
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
     * @param rental  the rental to include
     * @return true if mail sucsessfully was sent
     */
    public boolean sendRentalInformation(Rental rental) {
        try {
            Customer cu = rental.getCustomer();
            Car ca = rental.getCar();
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(cu.getEmail()));
            message.setSubject("QueerCars - booking information");

            String messageBody = "Hello " + cu.getFname() + "!\nThank you for using QueerCars as your personal car rental!\n" + "Here is your booking:\n";

            messageBody += "You have rented a " + ca.getModel() + " with registration number " + ca.getId() + "\n";
            messageBody += "This order was recieved at " + rental.getRentalDate() + ".\n";

            messageBody = messageBody + "Have a safe trip (we want the car back in one piece)\n\n" + "//QueerCars";
            message.setText(messageBody);
            Transport.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 
     * @return true if mail sucsessfully was sent
     */
    public boolean sendNewAccountInfo(Customer customer) {
        try {
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(customer.getEmail()));
            message.setSubject("QueerCars - account information");
            String messageBody = "Thank you " + customer.getFname() +   " for creating a QueerCars account!\n" + "Here is your account information:\n\n";
            messageBody += "account id: " + customer.getId();
            messageBody = messageBody + "\n\nWe have a nice collection of cars, come to our webpage and check them out!\n\n" + "//QueerCars";
            message.setText(messageBody);
            Transport.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @param rental the rental to save
     * @return true if mail sucsessfully was sent
     */
    public boolean sendOrderToQueerCars(Rental rental) {
        try {
            Customer customer = rental.getCustomer();
            Car car = rental.getCar();
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("queercars@gmail.com"));
            message.setSubject(rental.getRentalDate() + " Order from: " + customer + " with: " + car);

            String messageBody = rental.getRentalDate() + " - " + customer.getFname() + " has booked " + car.getId();

            messageBody += "\n\nMake the car ready for delivery\n";
            messageBody += "This order was recieved at " + rental.getRentalDate() + ".\n";

            message.setText(messageBody);
            Transport.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean sendInvoice(Rental rental, int diff) {
        Date endDate = new Date(System.currentTimeMillis());
        Long duration = endDate.getTime() - rental.getRentalDate().getTime();

        Invoice invoice = new Invoice(duration, diff, rental.getCar().getModel());

        try {
            Customer cu = rental.getCustomer();
            Car ca = rental.getCar();
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(cu.getEmail()));
            message.setSubject("QueerCars - rental invoice");

            String messageBody = "Hello " + cu.getFname() + "!\nThank you for using QueerCars as your personal car rental!\n" + "Here is your invoice:\n";

            messageBody += "You have rented a " + ca.getModel() + " with registration number " + ca.getId() + "\n";
            messageBody += "This rental order was recieved at " + rental.getRentalDate() + " and concluded at " + endDate + ".\n\n";
            messageBody += invoice.getInvoiceText();

            messageBody = messageBody + "We expect payment promptly.\n\n" + "//QueerCars";
            message.setText(messageBody);
            Transport.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
