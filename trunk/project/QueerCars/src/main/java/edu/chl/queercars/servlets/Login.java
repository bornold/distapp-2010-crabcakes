
package edu.chl.queercars.servlets;

import edu.chl.queercars.Customer;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author johanssb & joons
 */
@ManagedBean(name="Login")
@RequestScoped
public class Login {
    private EntityManagerFactory emf;
    private Customer cust;

    private String id;

    public String doLogin(){

	cust = validateUser(id);

        if(cust != null){
            return "success";
        }else{
            return "failure";
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /** Creates a new instance of Login */
    public Login() {
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