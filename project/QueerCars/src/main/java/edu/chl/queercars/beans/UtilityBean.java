/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.queercars.beans;

import edu.chl.queercars.Administrator;
import edu.chl.queercars.Customer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author joons
 *
 * Bean for databasehandling utility
 * 
 */
public class UtilityBean {

    EntityManagerFactory emf;

    public UtilityBean(EntityManagerFactory emf) {
	this.emf = emf;
    }
    public UtilityBean() {
	this.emf = Persistence.createEntityManagerFactory("queercars_pu");
    }

    public boolean isUser(String username){
	return (validateUser(username)!= null );
    }
    public boolean isAdmin(String username){
	return (validateAdmin(username)!= null );
    }

    /**
     * validate user using EntityManager
     * @param The username to be checked
     * @return Customer with the username or null if it doesn't exist
     *
     */
    public Customer validateUser(String username) {
	EntityManager em = emf.createEntityManager();
	Customer retcust = em.find(Customer.class, username);
	em.close();
	return retcust;
    }

     /**
     * validate admin using EntityManager
     * @param The username to be checked
     * @return Admin with the name or null if it doesn't exist
     *
     */
    public Administrator validateAdmin(String username) {
	EntityManager em = emf.createEntityManager();
	Administrator retadmin = em.find(Administrator.class, username);
	em.close();
	return retadmin;
    }

    void closeEm() {
	emf.close();
    }
}
