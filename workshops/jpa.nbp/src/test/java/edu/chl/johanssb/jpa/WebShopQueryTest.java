/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.johanssb.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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
public class WebShopQueryTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    public WebShopQueryTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        emf = Persistence.createEntityManagerFactory("webshop_pu");
        em = emf.createEntityManager();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        em.close();
        emf.close();
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
    public void testAll() {
        String allCustomers = "SELECT c FROM Customer c";
        Query q1 = em.createQuery(allCustomers);
        List<Customer> customerList = q1.getResultList();
        assertNotNull(customerList);
        assertTrue(customerList.size() == 4);

        String allPurchaseOrders = "SELECT po FROM PurchaseOrder po";
        Query q2 = em.createQuery(allPurchaseOrders);
        List<PurchaseOrder> poList = q2.getResultList();
        assertNotNull(poList);
        assertTrue(poList.size() == 9);

        String allOrderItems = "SELECT oi FROM OrderItem oi";
        Query q3 = em.createQuery(allOrderItems);
        List<OrderItem> oiList = q3.getResultList();
        assertNotNull(oiList);
        assertTrue(oiList.size() == 4);

        String allProducts = "SELECT p FROM Product p";
        Query q4 = em.createQuery(allProducts);
        List<Product> productList = q4.getResultList();
        assertNotNull(productList);
        assertTrue(productList.size() == 6);
    }

    @Test
    public void testQueryForCustomer() {
        String customerQuery = "SELECT c FROM Customer c WHERE c.id = '151'";
        Query q1  = em.createQuery(customerQuery);
        List<Customer> customers = q1.getResultList();
        assertNotNull(customers);
        assertTrue(customers.size() == 1);
        assertTrue(customers.get(0).getFname().equals("Rick"));
    }

    @Test
    public void testQueryForProduct() {
        String productQuery = "SELECT p FROM Product p WHERE p.id = '1'";
        Query q1  = em.createQuery(productQuery);
        List<Product> products = q1.getResultList();
        assertNotNull(products);
        assertTrue(products.size() == 1);
        assertTrue(products.get(0).getName().equals("MegaStrong 3000"));
    }

    @Test
    public void testQueryForCustomerWithPurchaseOrder() {
        String customerQuery = "SELECT po FROM PurchaseOrder po WHERE po.customer.id = '351'"; //Get Thor Awesomespeed.
        Query q1  = em.createQuery(customerQuery);
        List<PurchaseOrder> pos = q1.getResultList();
        assertNotNull(pos);
        assertTrue(pos.size() == 2); //Should have 2 purchase orders.
    }

    @Test
    public void testQueryForCustomerWithOrderItems() {
        String customerQuery = "SELECT oi FROM OrderItem oi WHERE oi.purchaseOrder.customer.id = '351'"; //Get Thor Awesomespeed.
        Query q1  = em.createQuery(customerQuery);
        List<OrderItem> ois = q1.getResultList();
        assertNotNull(ois);
        assertTrue(ois.size() == 4); //Should have 4 orderitems.
    }

    //@Test
    public void testQueryForTotalPrice() {
        String customerQuery = "SELECT SUM((oi.product.price)*(oi.quantity)) FROM OrderItem oi WHERE oi.purchaseOrder.id = '352'"; //Get specific purchase order.
        Query q1  = em.createQuery(customerQuery);
        Double total = (Double)q1.getSingleResult();
        System.out.println("##############" + total.toString());

        //TODO omgomg
    }

    @Test
    public void testQueryForCategories(){
        String query = "SELECT DISTINCT p.cat FROM OrderItem oi JOIN oi.product p WHERE oi.purchaseOrder.customer.id = '351'";
        Query q1  = em.createQuery(query);
        List categories = q1.getResultList();
        assertNotNull(categories);
        assertTrue(categories.size()==2);
    }
}
