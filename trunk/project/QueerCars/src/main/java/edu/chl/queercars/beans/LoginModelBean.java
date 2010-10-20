/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.queercars.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author joons
 */

@ManagedBean
@SessionScoped
public class LoginModelBean {
    private String id;
    private String fname;
    private String email;

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getName() {
	return fname;
    }

    public void setName(String name) {
	this.fname = name;
    }
}
