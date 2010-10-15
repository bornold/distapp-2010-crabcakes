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
import javax.persistence.Persistence;
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
        String allAdministrators = "SELECT a FROM administrator a";
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateAdministrator(Administrator a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
