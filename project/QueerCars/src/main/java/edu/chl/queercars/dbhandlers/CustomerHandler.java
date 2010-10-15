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
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author flipmo
 */
public class CustomerHandler implements ICustomerHandler{
    EntityManagerFactory emf;

    public CustomerHandler() {
        emf = Persistence.createEntityManagerFactory("queercars_pu");
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateCustomer(Customer c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
