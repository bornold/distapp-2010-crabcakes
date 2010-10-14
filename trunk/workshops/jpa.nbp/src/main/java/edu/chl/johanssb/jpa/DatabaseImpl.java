/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.johanssb.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author johanssb
 */
public class DatabaseImpl implements IDatabase{

    EntityManagerFactory emf;

    public DatabaseImpl() {
        emf = Persistence.createEntityManagerFactory("webshop_pu");

    }


    @Override
    public List<Product> getAllProducts() {
        List<Product> products;

        EntityManager em = emf.createEntityManager();
        String allProducts = "SELECT p FROM Product p";
        Query q = em.createQuery(allProducts);

        products = q.getResultList();

        em.close();
        return products;
    }

    @Override
    public boolean updateProduct(Product p) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.merge(p);
        tx.commit();

        em.close();
        return true;
    }

    @Override
    public boolean addProduct(Product p) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.persist(p);
        tx.commit();

        em.close();
        return true;
    }

    @Override
    public boolean removeProduct(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Product p = em.getReference(Product.class, id);
        tx.begin();
        em.remove(p);
        tx.commit();

        em.close();
        return true;
    }
}
