/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.queercars.dbhandlers;

import edu.chl.queercars.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author flipmo
 */
public class CustomerHandler implements ICustomerHandler{
    EntityManagerFactory emf;

    //Use other constructor please
    public CustomerHandler(EntityManagerFactory emf) {
        this.emf = emf;
    }


    @Override
    public List<Customer> getAllCustomers() {
        EntityManager em = emf.createEntityManager();
        String allCustomers = "SELECT c FROM Customer c";
        Query q = em.createQuery(allCustomers);
        List<Customer> results = q.getResultList();
        return results;
    }

    @Override
    public void removeCustomer(String id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Customer c = em.getReference(Customer.class, id);
        tx.begin();
        em.remove(c);
        tx.commit();

        em.close();
    }

    @Override
    public void addCustomer(Customer c) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Customer existingCustomer = em.find(Customer.class, c.getId());

        if (existingCustomer != null) { //Customer exists, update the record.
            tx.begin();
            em.merge(c);
            tx.commit();
        } else { //Customer is new, add a record.
            tx.begin();
            em.persist(c);
            tx.commit();
        }
        em.close();
    }
}