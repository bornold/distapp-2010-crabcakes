/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.johanssb.jpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
        ArrayList<Product> products = new ArrayList<Product>();

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
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
