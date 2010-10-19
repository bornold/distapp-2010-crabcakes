/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.queercars.dbhandlers;

import edu.chl.queercars.Car;
import edu.chl.queercars.Customer;
import edu.chl.queercars.Rental;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 *
 * @author johanssb
 */
public class RentalHandler implements IRentalHandler {
    EntityManagerFactory emf;

    public RentalHandler(EntityManagerFactory emf){
        this.emf = emf;
    }
    
    @Override
    public void rentCar(Customer cu, Car ca) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        cu = em.find(Customer.class, cu.getId());
        ca = em.find(Car.class, ca.getId());

        Rental r = new Rental(cu, ca);
        tx.begin();
        em.persist(r);
        tx.commit();
        
        em.close();
    }

}
