/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.queercars.dbhandlers;

import edu.chl.queercars.Administrator;
import edu.chl.queercars.Car;
import edu.chl.queercars.Customer;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author mviktor
 */
public class CarHandler implements ICarHandler {

    EntityManagerFactory emf;

    public CarHandler(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Car> getAllCars() {
        EntityManager em = emf.createEntityManager();
        String allCars = "SELECT c FROM Car c";
        Query q = em.createQuery(allCars);
        List<Car> results = q.getResultList();
        return results;
    }

    @Override
    public void removeCar(String id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Car car = em.getReference(Car.class, id);
        tx.begin();
        em.remove(car);
        tx.commit();

        em.close();
    }

    @Override
    public void addCar(Car c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateCar(Car c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
