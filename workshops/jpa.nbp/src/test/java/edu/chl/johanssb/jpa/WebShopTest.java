/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.johanssb.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
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
 *JTAJTA
 * @author flipmo
 */
public class WebShopTest {

    EntityManagerFactory emf;

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
        emf = Persistence.createEntityManagerFactory("webshop_pu_test");
    }

    @After
    public void tearDown() {
        emf.close();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testAddProduct() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Product prod1 = new Product();
        prod1.setName("MegaStrong 3000");
        prod1.setCat("ropes");
        prod1.setPrice(9.90);
        Product prod2;

        assertFalse(em.contains(prod1));

        tx.begin();
        em.persist(prod1); //Add to DB.
        tx.commit(); //Commit changes.

        assertTrue(em.contains(prod1));

        prod2 = em.getReference(Product.class, prod1.getId());

        assertTrue(em.contains(prod2));
        assertTrue(prod1 == prod2);
        assertTrue(prod1.equals(prod2));

        em.detach(prod1);
        assertFalse(em.contains(prod1));

        em.close();
    }

    @Test
    public void testUpdateProduct() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Product prod1 = new Product();
        prod1.setName("MegaStrong 3000");
        prod1.setCat("ropes");
        prod1.setPrice(9.90);
        Product prod2 = prod1;
        double newprice = 11.90;

        tx.begin();
        em.persist(prod1);
        tx.commit();

        em.clear();

        prod1.setPrice(newprice);

        tx.begin();
        prod1 = em.merge(prod1);
        tx.commit();

        assertTrue(prod1.equals(prod2));
        assertFalse(prod1 == prod2);

        prod2 = em.getReference(Product.class, prod1.getId());
        assertTrue(prod2.getPrice() == newprice);

        em.close();
    }

    @Test
    public void testDeleteProduct() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Product prod1 = new Product();
        prod1.setName("MegaStrong 3000");
        prod1.setCat("ropes");
        prod1.setPrice(9.90);
        Product prod2 = prod1;

        tx.begin();
        em.persist(prod1);
        tx.commit();

        prod2 = em.getReference(Product.class, prod1.getId());
        tx.begin();
        em.remove(prod2);
        tx.commit();

        assertFalse(em.contains(prod1));
        assertFalse(em.contains(prod2));
        prod2 = em.find(Product.class, prod1.getId());

        assertTrue(prod2 == null);
        em.close();
    }
    @Test
    public void testAddCustomer(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Customer cust1 = new Customer("Rick", "Astley", "rick@rolled.com", new Address("Rodeo drive 1", "Hallywuud", "U.S. of EY"));
        Customer cust2;

        tx.begin();
        em.persist(cust1);
        tx.commit();

        cust2 = em.getReference(Customer.class, cust1.getId());
        assertTrue(cust2.equals(cust1));
        Address test = cust2.getAddress();
        assertTrue(test.getCity().equals("Hallywuud"));
        em.close();
    }
    @Test
    public void testCustomer(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        List<PurchaseOrder> orders = new ArrayList<PurchaseOrder>();
        
        PurchaseOrder p1 = new PurchaseOrder(new Date(System.currentTimeMillis()));
        PurchaseOrder p2 = new PurchaseOrder(new Date(System.currentTimeMillis()));
        PurchaseOrder p3 = new PurchaseOrder(new Date(System.currentTimeMillis()));
        
        orders.add(0, p1);
        orders.add(1, p2);
        orders.add(2, p3);

        Customer cust1 = new Customer("Don", "Johnson", "don@johnson.com", new Address("Rodeo drive 1", "Hallywuud", "U.S. of EY"));
        cust1.setOrders(orders);

        p1.setCustomer(cust1);
        p2.setCustomer(cust1);
        p3.setCustomer(cust1);

        tx.begin();
        em.persist(cust1);
        tx.commit();

        Customer cust2 = em.getReference(Customer.class, cust1.getId());
        assertEquals(cust1,cust2);
        assertEquals(p1, cust2.getOrders().get(0));
        assertEquals(p2, cust2.getOrders().get(1));
        assertEquals(p3, cust2.getOrders().get(2));
        em.close();
    }
}
