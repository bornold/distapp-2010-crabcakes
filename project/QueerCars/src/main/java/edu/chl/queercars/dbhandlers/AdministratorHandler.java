/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.queercars.dbhandlers;

import edu.chl.queercars.Administrator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author mviktor
 */
public class AdministratorHandler implements IAdministratorHandler {

    EntityManagerFactory emf;

    public AdministratorHandler(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Administrator> getAllAdministrators() {
        EntityManager em = emf.createEntityManager();
        String allAdministrators = "SELECT a FROM Administrator a";
        Query q = em.createQuery(allAdministrators);
        List<Administrator> results = q.getResultList();
        return results;
    }

    @Override
    public void removeAdministrator(String id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Administrator admin = em.getReference(Administrator.class, id);
        tx.begin();
        em.remove(admin);
        tx.commit();

        em.close();
    }

    @Override
    public void addAdministrator(Administrator a) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Administrator existingAdmin = em.find(Administrator.class, a.getId());

        if (existingAdmin != null) { //Administrator exists, update the record.
            tx.begin();
            em.merge(a);
            tx.commit();
        } else { //Administrator is new, add a record.
            tx.begin();
            em.persist(a);
            tx.commit();
        }
        em.close();
    }

    @Override
    public void updateAdministrator(Administrator a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
