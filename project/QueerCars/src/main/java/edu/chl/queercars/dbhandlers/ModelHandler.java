package edu.chl.queercars.dbhandlers;

import edu.chl.queercars.Model;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 * Acts as an interface to the database for managing Model objects.
 * @author johanssb
 */
public class ModelHandler {
    EntityManagerFactory emf;

    /**
     * @param emf EntityManagerFactory from parent class.
     */
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

    public Model getModel(String id){
        EntityManager em = emf.createEntityManager();
        Model m = em.find(Model.class, id);
        em.close();
        return m;
    }
}