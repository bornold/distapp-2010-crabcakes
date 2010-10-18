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
    public void removeCar(String id);
    public void addCar(Car c);
}
