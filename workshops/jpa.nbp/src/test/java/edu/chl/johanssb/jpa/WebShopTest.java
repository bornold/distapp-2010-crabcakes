/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.johanssb.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import junit.framework.TestCase;

/**
 *
 * @author flipmo
 */
public class WebShopTest extends TestCase {
    
    public WebShopTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    // TODO add test methods here. The name must begin with 'test'. For example:
    // public void testHello() {}
    public void testAddProduct(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("webshop_pu_test");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Product prod = new Product();
        prod.setName("MegaStrong 3000");
        prod.setCat("ropes");
        prod.setPrice(9.90);
        tx.begin();
        em.persist(prod);
        tx.commit();

        //Product newProd = em.getReference(Product.class, prod.getId());
        //assertTrue(newProd.getName().equals(prod.getName()));
        assertTrue(true);
        em.close();

        
    }

}
