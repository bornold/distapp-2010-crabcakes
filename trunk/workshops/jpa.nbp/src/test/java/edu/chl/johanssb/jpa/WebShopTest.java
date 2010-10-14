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
        emf = Persistence.createEntityManagerFactory("webshop_pu");
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
        prod1.setName("Ropetastic 4000");
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
        prod1.setName("Cotton Yarn");
        prod1.setCat("ropes");
        prod1.setPrice(2.90);
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

        Customer cust1 = new Customer("Don", "Johnson", "don@johnson.com", new Address("1600 Pennsylvania Avenue", "Washington D.C.", "USA"));
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

    @Test
    public void testUpdateCustomer(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        List<PurchaseOrder> orders = new ArrayList<PurchaseOrder>();

        PurchaseOrder p1 = new PurchaseOrder(new Date(System.currentTimeMillis()));
        PurchaseOrder p2 = new PurchaseOrder(new Date(System.currentTimeMillis()));
        PurchaseOrder p3 = new PurchaseOrder(new Date(System.currentTimeMillis()));

        orders.add(0, p1);
        orders.add(1, p2);
        orders.add(2, p3);

        Customer cust1 = new Customer("Corey", "Hart", "coreyhart@the80s.com", new Address("Washed Up Musicians", "California", "USA"));
        cust1.setOrders(orders);

        p1.setCustomer(cust1);
        p2.setCustomer(cust1);
        p3.setCustomer(cust1);

        Customer cust2 = cust1;

        tx.begin();
        em.persist(cust1);
        tx.commit();

        em.clear();
        
        PurchaseOrder p4 = new PurchaseOrder(new Date(System.currentTimeMillis()));
        orders.add(3, p4);
        p4.setCustomer(cust1);

        tx.begin();
        cust1 = em.merge(cust1);
        tx.commit();

        assertFalse(cust1 == cust2);
        assertNotNull(cust1.equals(cust2));
        em.close();
    }

    @Test
    public void testCustomerRemove(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        List<PurchaseOrder> orders = new ArrayList<PurchaseOrder>();

        PurchaseOrder p1 = new PurchaseOrder(new Date(System.currentTimeMillis()));
        PurchaseOrder p2 = new PurchaseOrder(new Date(System.currentTimeMillis()));
        PurchaseOrder p3 = new PurchaseOrder(new Date(System.currentTimeMillis()));

        orders.add(0, p1);
        orders.add(1, p2);
        orders.add(2, p3);

        Customer cust1 = new Customer("Mike", "Rotch", "mikerotch@itsajoke.com", new Address("123 Fake Street", "Las Vegas", "USA"));
        cust1.setOrders(orders);

        p1.setCustomer(cust1);
        p2.setCustomer(cust1);
        p3.setCustomer(cust1);

        Customer cust2 = cust1;

        tx.begin();
        em.persist(cust1);
        tx.commit();

        cust2 = em.getReference(Customer.class, cust1.getId());

        tx.begin();
        em.remove(cust2);
        tx.commit();

        assertFalse(em.contains(cust1));
        assertFalse(em.contains(cust2));

        cust2 = em.find(Customer.class, cust1.getId());
        assertNull(cust2);
        em.close();
    }

    @Test
    public void testCompleteGraph(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        //Create a few products.
        Product prod1 = new Product("Kittens", "Pets", 100.0);
        Product prod2 = new Product("Cat Food", "Petfood", 30.0);
        Product prod3 = new Product("Puppies", "Pets", 120.0);
        Product prod4 = new Product("Dog Food", "Petfood", 35.0);

        //Create a few order items.
        OrderItem oi1 = new OrderItem(prod1, 2); //2 kittens!
        OrderItem oi2 = new OrderItem(prod2, 6); //Lots of cat food.
        ArrayList<OrderItem> orderItems1 = new ArrayList();
        orderItems1.add(oi1);
        orderItems1.add(oi2);
        
        OrderItem oi3 = new OrderItem(prod3, 1); //1 cute puppy.
        OrderItem oi4 = new OrderItem(prod4, 10); //Even more dog food for hungry puppy.
        ArrayList<OrderItem> orderItems2 = new ArrayList();
        orderItems2.add(oi3);
        orderItems2.add(oi4);

        //Create some purchase orders with our order items.
        PurchaseOrder po1 = new PurchaseOrder(new Date(System.currentTimeMillis()),orderItems1);
        PurchaseOrder po2 = new PurchaseOrder(new Date(System.currentTimeMillis()),orderItems2);
        List<PurchaseOrder> orders = new ArrayList<PurchaseOrder>();
        orders.add(po1);
        orders.add(po2);
        
        //We need a customer to pay for all this stuff. This guy will do.
        Customer cust1 = new Customer("Thor", "Awesomespeed", "thor@stuff.se", new Address("Bakgatan 5", "Göteborg", "Sweden"));

        //Tie it all together.
        cust1.setOrders(orders);
        po1.setCustomer(cust1);
        po2.setCustomer(cust1);
        oi1.setPurchaseOrder(po1);
        oi2.setPurchaseOrder(po1);
        oi3.setPurchaseOrder(po2);
        oi4.setPurchaseOrder(po2);

        tx.begin();
        em.persist(cust1);
        tx.commit();

        em.clear();

        Customer newcust = em.find(Customer.class, cust1.getId());
        assertNotNull(newcust);
        assertFalse(newcust == cust1); //New reference.
        List<PurchaseOrder> neworders = newcust.getOrders();
        assertTrue(neworders.size()==2);

        PurchaseOrder npo1 = neworders.get(0);
        PurchaseOrder npo2 = neworders.get(1);
        List<OrderItem> noil1 = npo1.getOrderItems();
        List<OrderItem> noil2 = npo2.getOrderItems();
        assertTrue(noil1.size()==2);
        assertTrue(noil1.size()==2);

        OrderItem noi1 = noil1.get(0);
        OrderItem noi2 = noil1.get(1);
        OrderItem noi3 = noil2.get(0);
        OrderItem noi4 = noil2.get(1);
        assertTrue(noi1.getQuantity()==2);
        assertTrue(noi2.getQuantity()==6);
        assertTrue(noi3.getQuantity()==1);
        assertTrue(noi4.getQuantity()==10);

        em.close();
    }
}
