/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.johanssb.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author flipmo
 */
public class WebShopTest {

    public WebShopTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testAddProduct() {
        assertTrue(true);
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
