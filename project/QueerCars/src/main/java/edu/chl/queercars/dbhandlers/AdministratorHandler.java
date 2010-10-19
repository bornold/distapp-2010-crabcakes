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

    /**
     * 
     * @param emf The entityManagerFactory to use
     */
    public AdministratorHandler(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /**
     *
     * @return list of all administrators
     */
    @Override
    public List<Administrator> getAllAdministrators() {
        EntityManager em = emf.createEntityManager();
        String allAdministrators = "SELECT a FROM Administrator a";
        Query q = em.createQuery(allAdministrators);
        List<Administrator> results = q.getResultList();
        em.close();
        return results;
    }

    /**
     *
     * @param id the id of the admin to remove
     */
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

    /**
     *
     * @param a the administrator to save (if old it updates the database with new information)
     */
    @Override
    public void saveAdministrator(Administrator a) {
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

    /**
     *
     * @param id the id of the administrator to get
     * @return returns the administrator
     */
    @Override
    public Administrator getAdministrator(String id) {
        EntityManager em = emf.createEntityManager();

        Administrator administrator = em.find(Administrator.class, id);
        em.close();
        return administrator;
    }
}
