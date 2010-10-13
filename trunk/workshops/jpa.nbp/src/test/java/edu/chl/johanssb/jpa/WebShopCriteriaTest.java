/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.johanssb.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class WebShopCriteriaTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;

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
    public void testCriteria(){
        CriteriaBuilder qbuilder = em.getCriteriaBuilder();
        CriteriaQuery<Customer> cquery = qbuilder.createQuery(Customer.class);
        Root<Customer> root = cquery.from(Customer.class);

        Predicate pred = qbuilder.equal(root.get("fname"),"Thor");
        cquery.where(pred);
        TypedQuery<Customer> tq = em.createQuery(cquery);
        Customer c = tq.getSingleResult();
        assertTrue(c.getLname().equals("Awesomespeed"));
    }

}