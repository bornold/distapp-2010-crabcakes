package edu.chl.queercars.dbhandlers;

import edu.chl.queercars.Car;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author mviktor
 */
public class CarHandler {

    EntityManagerFactory emf;

    /**
     *
     * @param emf The entityManagerFactory to use
     */
    public CarHandler(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /**
     *
     * @return a list of all cars
     */
    public List<Car> getAllCars() {
        EntityManager em = emf.createEntityManager();
        String allCars = "SELECT c FROM Car c";
        Query q = em.createQuery(allCars);
        List<Car> results = q.getResultList();
        em.close();
        return results;
    }

    /**
     *
     * @return a list of all the available cars
     */
    public List<Car> getAllAvailableCars(){
        EntityManager em = emf.createEntityManager();
        String allCars = "SELECT c FROM Car c WHERE c.id NOT IN (SELECT r.car.id FROM Rental r)";
        Query q = em.createQuery(allCars);
        List<Car> results = q.getResultList();
        em.close();
        return results;
    }

    /**
     *
     * @param id the id of the car to remove
     */
    public void removeCar(String id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Car car = em.getReference(Car.class, id);
        tx.begin();
        em.remove(car);
        tx.commit();

        em.close();
    }

    /**
     *
     * @param c the car to save (if old it updates the database with new information)
     */
    public void saveCar(Car c) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Car existingCar = em.find(Car.class, c.getId());

        if (existingCar != null) {
            c.setOdometer(existingCar.getOdometer());
            tx.begin();
            em.merge(c);
            tx.commit();
        } else {
            tx.begin();
            em.persist(c);
            tx.commit();
        }
        em.close();
    }

    /**
     *
     * @param id the id of the car (registration number)
     * @return the car
     */
    public Car getCar(String id){
        EntityManager em = emf.createEntityManager();

        Car car = em.find(Car.class, id);
        em.close();
        return car;
    }
}