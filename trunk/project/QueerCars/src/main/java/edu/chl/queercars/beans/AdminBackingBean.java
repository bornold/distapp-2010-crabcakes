package edu.chl.queercars.beans;

import edu.chl.queercars.Administrator;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * @author joons
 *
 * AdminBackingBean for admin login validation using JSF
 */
@ManagedBean
@RequestScoped
public class AdminBackingBean {

    private String id;
    @ManagedProperty(value = "#{adminLoginModelBean}")
    private AdminLoginModelBean adminLoginModelBean;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public AdminLoginModelBean getAdminLoginModelBean() {
	return adminLoginModelBean;
    }

    public void setAdminLoginModelBean(AdminLoginModelBean adminLoginModelBean) {
	this.adminLoginModelBean = adminLoginModelBean;
    }

    /**
     * Login metod connects to the database to check if login is valid
     *
     * if it is successfull injecting the adminModulebean with admin information
     *
     * @return String for faces redirect infromation
     */

    public String doLogin() {

	UtilityBean ub = new UtilityBean();
	Administrator admin = ub.validateAdmin(id);
	ub.closeEm();


	if (admin != null) {
	    adminLoginModelBean.setId(id);
	    adminLoginModelBean.setFName(admin.getFname());
	    return "success";

	} else {
	    FacesContext ctx = FacesContext.getCurrentInstance();
	    ctx.addMessage("adminForm", new FacesMessage("Login unsuccessfull"));
	    return "failure";

	}
    }

    /**
     * kills the session and emptys the moduleBean
     * @return string for redirect for faces servlet
     */

    public String doLogout() {
	adminLoginModelBean.setId(null);
	adminLoginModelBean.setFName(null);
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	if (session != null) {
	    session.invalidate();
	}
	return "logout";
    }

    /** Creates a new instance of QueerCarBackingBean */
    public AdminBackingBean() {
    }
}
