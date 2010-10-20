package edu.chl.queercars.beans;

import edu.chl.queercars.Administrator;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author joons
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

    public String doLogin() {

	UtilityBean ub = new UtilityBean();
	Administrator ad = ub.validateAdmin(id);
	ub.closeEm();

	if (ad != null) {
	    return "success";
	} else {
	    return "failure";
	}
    }

    /** Creates a new instance of AdminLoginModelBean */
    public AdminLoginModelBean() {
    }
}
