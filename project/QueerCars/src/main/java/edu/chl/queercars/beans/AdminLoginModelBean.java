package edu.chl.queercars.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author joons
 *
 * A ManageBean with SessionScope the stores Admins id and name
 */
@ManagedBean
@SessionScoped
public class AdminLoginModelBean {

    private String id;
    private String fname;

    public String getFName() {
	return fname;
    }

    public void setFName(String fname) {
	this.fname = fname;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    /** Creates a new instance of AdminLoginModelBean */
    public AdminLoginModelBean() {
    }
}
