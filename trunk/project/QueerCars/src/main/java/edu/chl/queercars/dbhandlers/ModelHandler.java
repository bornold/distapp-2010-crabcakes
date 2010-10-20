/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.queercars.dbhandlers;

import edu.chl.queercars.Model;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author johanssb
 */
public class ModelHandler {
    EntityManagerFactory emf;

    public ModelHandler(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public List<Model> getAllModels() {
        EntityManager em = emf.createEntityManager();
        String allModels = "SELECT m FROM Model m";
        Query q = em.createQuery(allModels);
        List<Model> results = q.getResultList();
        em.close();
        return results;
    }

    public void saveModel(Model m){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.persist(m);
        tx.commit();
        em.close();
    }
}