/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.queercars.dbhandlers;

import edu.chl.queercars.Car;
import java.util.List;

/**
 *
 * @author mviktor
 */
public interface ICarHandler {
    public List<Car> getAllCars();
    public List<Car> getAllAvailableCars();
    public void removeCar(String id);
    public void saveCar(Car c);
    public Car getCar(String id);
}
