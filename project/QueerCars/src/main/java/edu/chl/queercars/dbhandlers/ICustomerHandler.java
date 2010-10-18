/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.queercars.dbhandlers;

import edu.chl.queercars.Customer;
import java.util.List;

/**
 *
 * @author flipmo
 */
public interface ICustomerHandler {
    public List<Customer> getAllCustomers();
    public void removeCustomer(String id);
    public void saveCustomer(Customer c);
}
