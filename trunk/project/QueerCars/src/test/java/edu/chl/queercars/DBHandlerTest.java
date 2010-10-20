/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.queercars;

import edu.chl.queercars.dbhandlers.AdministratorHandler;
import edu.chl.queercars.dbhandlers.CarHandler;
import edu.chl.queercars.dbhandlers.CustomerHandler;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mviktor
 */
public class DBHandlerTest {

    private static EntityManagerFactory emf;
    private static CustomerHandler custHandler;
    private static AdministratorHandler adminHandler;
    private static CarHandler carHandler;

    public DBHandlerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        emf.close();
    }

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("queercars_pu");
        custHandler = new CustomerHandler(emf);
        adminHandler = new AdministratorHandler(emf);
        carHandler = new CarHandler(emf);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddCar() {

        Model m = new Model("blackCar", 0.1, 123);
        Car car = new Car("TAC123", m);

        assertFalse(carHandler.getCar("TAC123").equals(car));

        carHandler.saveCar(car);

        assertTrue(carHandler.getCar("TAC123").equals(car));
    }

    @Test
    public void testAddCustomer() {

        Customer customer = new Customer("8903091122", "Vixen", "adfasdf@mail.com");

        assertFalse(custHandler.getCustomer("8903091122").equals(customer));

        custHandler.saveCustomer(customer);

        assertTrue(custHandler.getCustomer("8903091122").equals(customer));
    }

    @Test
    public void testAddAdmin() {
        Administrator administrator = new Administrator("SecretIDIDID", "BeardedDude");

        assertFalse(adminHandler.getAdministrator("SecretIDIDID").equals(administrator));

        adminHandler.saveAdministrator(administrator);

        assertFalse(adminHandler.getAdministrator("SecretIDIDID").equals(administrator));
    }

    @Test
    public void testUpdateCar() {

        Model m1 = new Model("blackCar", 0.1, 153);
        Car car = new Car("TUC123", m1);

        carHandler.saveCar(car);

        Model m2 = new Model("mercuryCar", 0.03, 126);
        Car newCar = new Car("TUC123", m2);
        carHandler.saveCar(newCar); //Overwrite car1.

        Car checkCar = carHandler.getCar("TUC123");
        assertTrue(checkCar.getModel().equals(m2));
    }

    @Test
    public void testUpdateCustomer() {

        Customer cust = new Customer("8805055555", "Charlie", "Chailie@fasdf.com");

        custHandler.saveCustomer(cust);

        Customer newCustomer = new Customer("8805055555", "Amanda", "asdfasdf@mail.com");
        custHandler.saveCustomer(newCustomer);

        Customer checkCustomer = custHandler.getCustomer("8805055555");
        assertTrue(checkCustomer.getFname().equals(newCustomer.getFname()));
    }

    @Test
    public void testUpdateAdmin() {

        Administrator admin = new Administrator("adminidid", "Balthasar");

        adminHandler.saveAdministrator(admin);

        Administrator newAdministrator = new Administrator("adminidid", "Balto");
        adminHandler.saveAdministrator(newAdministrator);

        Administrator checkAdministrator = adminHandler.getAdministrator("adminidid");
        assertTrue(checkAdministrator.getFname().equals(newAdministrator.getFname()));
    }

    @Test
    public void testDeleteCar() {

        Model m1 = new Model("blackCar", 0.12, 231);
        Car car1 = new Car("TDC123", m1);

        carHandler.saveCar(car1);

        carHandler.removeCar("TDC123");

        assertTrue(carHandler.getCar("TDC123") == null);
    }
}
