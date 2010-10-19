/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.queercars.dbhandlers;

import edu.chl.queercars.Car;
import edu.chl.queercars.Customer;

/**
 *
 * @author johanssb
 */
public interface IRentalHandler {
    public void rentCar(Customer cu, Car ca);
}
