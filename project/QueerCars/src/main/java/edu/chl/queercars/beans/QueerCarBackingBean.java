package edu.chl.queercars.beans;

import edu.chl.queercars.Customer;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;

/**
 *
 * @author johanssb & joons
 */
@ManagedBean
@RequestScoped
public class QueerCarBackingBean {

    private EntityManagerFactory emf;
    private String id;
    @ManagedProperty(value = "#{loginModelBean}")
    private LoginModelBean loginModelBean;

    public LoginModelBean getLoginModelBean() {
	return loginModelBean;
    }

    public void setLoginModelBean(LoginModelBean loginModelBean) {
	this.loginModelBean = loginModelBean;
    }

    public String doLogin() {

	Customer cust = validateUser(id);

	if (cust != null) {
	    loginModelBean.setId(id);
	    loginModelBean.setName(cust.getFname());
	    System.out.println("SUCCESS");
	    System.out.println("SUCCESS");
	    System.out.println("SUCCESS");
	    return "success";
	} else {
	    System.out.println("FAIL");
	    System.out.println("FAIL");
	    System.out.println("FAIL");
	    return "failure";
	}
    }

    public String doLogout() {
	loginModelBean.setId(null);
	loginModelBean.setName(null);
	// Behövs detta?
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	if (session != null) {
	    session.invalidate();
	}
	return "logout";
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    /** Creates a new instance of QueerCarBackingBean */
    public QueerCarBackingBean() {
    }

    private Customer validateUser(String username) {
	emf = Persistence.createEntityManagerFactory("queercars_pu");
	EntityManager em = emf.createEntityManager();
	Customer retcust = em.find(Customer.class, username);
	em.close();
	emf.close();
	return retcust;
    }
}
