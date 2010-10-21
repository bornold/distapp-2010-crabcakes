/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.queercars.dbhandlers;

import edu.chl.queercars.Car;
import edu.chl.queercars.Customer;
import edu.chl.queercars.administrativeTools.MailHandler;
import edu.chl.queercars.Rental;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author johanssb
 */
public class RentalHandler {

    EntityManagerFactory emf;
    MailHandler mh;
    CarHandler carHandler;

    public RentalHandler(EntityManagerFactory emf) {
        this.emf = emf;
        this.mh = new MailHandler();
        this.carHandler = new CarHandler(emf);
    }

    public void rentCar(Customer cu, Car ca) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        cu = em.find(Customer.class, cu.getId());
        ca = em.find(Car.class, ca.getId());

        String query = "SELECT r FROM Rental r WHERE r.car.id = '" + ca.getId() + "'";
        Query q = em.createQuery(query);
        List<Rental> result = q.getResultList();

        if (result.size() == 0) {
            Rental r = new Rental(cu, ca);
            tx.begin();
            em.persist(r);
            tx.commit();

            mh.sendRentalInformation(r);
            mh.sendOrderToQueerCars(r);
        }
        em.close();
    }

    public List<Rental> getActiveRentals() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        String query = "SELECT r FROM Rental r";
        Query q = em.createQuery(query);

        return q.getResultList();
    }

    public void endRental(String rentalId, String odometerValue) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        System.out.println(odometerValue);
        Long id = Long.parseLong(rentalId);
        
        
        Rental rental = em.find(Rental.class, id);
        Car c = rental.getCar();

        int odometer = Integer.parseInt(odometerValue);
        int diff = odometer - c.getOdometer(); //How many miles the car has travelled.

        c.setOdometer(odometer);
        carHandler.saveCar(c);

        mh.sendInvoice(rental, diff);
        
        tx.begin();
        em.remove(rental);
        tx.commit();
        em.close();
    }
}
