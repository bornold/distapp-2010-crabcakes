/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.queercars.dbhandlers;

import edu.chl.queercars.Car;
import edu.chl.queercars.Customer;
import edu.chl.queercars.MailHandler;
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

    public RentalHandler(EntityManagerFactory emf) {
        this.emf = emf;
        this.mh = new MailHandler(emf);
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
        }
        em.close();
    }
}
