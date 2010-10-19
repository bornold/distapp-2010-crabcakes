package edu.chl.queercars.dbhandlers;

import edu.chl.queercars.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author flipmo, vixen
 */
public class CustomerHandler implements ICustomerHandler {

    EntityManagerFactory emf;

    /**
     *
     * @param emf The entityManagerFactory to use
     */
    public CustomerHandler(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /**
     *
     * @return a list of all the customers
     */
    @Override
    public List<Customer> getAllCustomers() {
        EntityManager em = emf.createEntityManager();
        String allCustomers = "SELECT c FROM Customer c";
        Query q = em.createQuery(allCustomers);
        List<Customer> results = q.getResultList();
        em.close();
        return results;
    }

    /**
     *
     * @param id the id of the customer to remove
     */
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

    /**
     *
     * @param c the customer to save (if old it updates the database with new information)
     */
    @Override
    public void saveCustomer(Customer c) {
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

    /**
     *
     * @param id the id of the customer to get (personal id number)
     * @return the customer
     */
    @Override
    public Customer getCustomer(String id) {
        EntityManager em = emf.createEntityManager();

        Customer customer = em.find(Customer.class, id);
        em.close();
        return customer;
    }
}
