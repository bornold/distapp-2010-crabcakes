package edu.chl.queercars.beans;

import edu.chl.queercars.Customer;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.ExternalContext;
import javax.servlet.http.HttpSession;

/**
 * @author johanssb & joons
 */
@ManagedBean
@RequestScoped
public class QueerCarBackingBean {

    private String id;
    @ManagedProperty(value = "#{loginModelBean}")
    private LoginModelBean loginModelBean;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public LoginModelBean getLoginModelBean() {
	return loginModelBean;
    }

    public void setLoginModelBean(LoginModelBean loginModelBean) {
	this.loginModelBean = loginModelBean;
    }

    public String doLogin() {

	UtilityBean ub = new UtilityBean();
	Customer cust = ub.validateUser(id);
	ub.closeEm();
	FacesContext ctx = FacesContext.getCurrentInstance();

	if (cust != null) {
	    loginModelBean.setId(id);
	    loginModelBean.setName(cust.getFname());
	    loginModelBean.setEmail(cust.getEmail());
	    ctx.addMessage("loggin", new FacesMessage("Login Successfull"));
	    return "success";
	} else {
	    ctx.addMessage("loggin", new FacesMessage("Login unsuccessfull"));
	    return "failure";
	}
    }

    public String doLogout() {
	FacesContext ctx = FacesContext.getCurrentInstance();
	ctx.addMessage("loggin", new FacesMessage("Goodbye " + loginModelBean.getName()));
	
	loginModelBean.setId(null);
	loginModelBean.setName(null);
	loginModelBean.setEmail(null);
	//////Behövs detta?/////
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	if (session != null) {
	    session.invalidate();
	}
	////////////////////////
	return "logout";
    }

    /** Creates a new instance of QueerCarBackingBean */
    public QueerCarBackingBean() {
    }
}
