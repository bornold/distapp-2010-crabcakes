package edu.chl.queercars.beans;

import edu.chl.queercars.Administrator;
import edu.chl.queercars.Customer;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * @author joons
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

    public String doLogin() {

	UtilityBean ub = new UtilityBean();
	Administrator admin = ub.validateAdmin(id);
	ub.closeEm();

	if (admin != null) {
	    adminLoginModelBean.setId(id);
	    adminLoginModelBean.setFName(admin.getFname());
	    return "success";
	} else {
	    return "failure";
	}
    }

    public String doLogout() {
	adminLoginModelBean.setId(null);
	adminLoginModelBean.setFName(null);
	// Behövs detta?//////////
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	if (session != null) {
	    session.invalidate();
	}
	//////////////////////////
	return "logout";
    }

    /** Creates a new instance of QueerCarBackingBean */
    public AdminBackingBean() {
    }
}
