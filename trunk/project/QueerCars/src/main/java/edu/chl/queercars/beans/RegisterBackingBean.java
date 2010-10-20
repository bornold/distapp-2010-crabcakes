/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.queercars.beans;

import edu.chl.queercars.Customer;
import edu.chl.queercars.dbhandlers.CustomerHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
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
    @ManagedProperty(value = "#{loginModelBean}")
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
	Customer customer = new Customer(id, fname, email);
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("queercars_pu");
	CustomerHandler ch = new CustomerHandler(emf);
	ch.saveCustomer(customer);
	loginModelBean.setId(id);
	loginModelBean.setName(fname);
	loginModelBean.setEmail(email);
	emf.close();
	FacesContext ctx = FacesContext.getCurrentInstance();
	ctx.addMessage("loggin", new FacesMessage("You are now registered"));
	return "registered";
    }
}
