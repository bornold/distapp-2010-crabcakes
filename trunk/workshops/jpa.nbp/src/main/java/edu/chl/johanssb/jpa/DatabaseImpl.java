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
        
        return products;
    }

    @Override
    public boolean updateProduct(Product p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean addProduct(Product p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean removeProduct(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Product getProduct(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
