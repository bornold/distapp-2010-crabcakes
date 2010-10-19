/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.queercars.beans;

import edu.chl.queercars.Customer;
import edu.chl.queercars.dbhandlers.CustomerHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author joons
 */
@ManagedBean
@RequestScoped
public class RegisterBackingBean {

    String id;
    String fname;
    String email;

    @ManagedProperty(value = "#{registerModelBean}")
    private LoginModelBean loginModelBean;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getFname() {
	return fname;
    }

    public void setFname(String fname) {
	this.fname = fname;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }
    
    public LoginModelBean getLoginModelBean() {
	return loginModelBean;
    }

    public void setLoginModelBean(LoginModelBean loginModelBean) {
	this.loginModelBean = loginModelBean;
    }
    public String doRegister() {
	Customer c = new Customer(id, fname, email);
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("queercars_pu");
	CustomerHandler ch = new CustomerHandler(emf);
	ch.saveCustomer(c);

	emf.close();
	return "registered";
    }
}
