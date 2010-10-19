
package edu.chl.queercars.beans;

import edu.chl.queercars.Administrator;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author joons
 */

@ManagedBean
@RequestScoped
public class AdminLogin {
    private EntityManagerFactory emf;
    private Administrator ad;

    private String id;

    public String doLogin(){

	ad = validateUser(id);

        if(ad != null){
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


    /** Creates a new instance of AdminLogin */
    public AdminLogin() {
    }

    private Administrator validateUser(String username) {
        emf = Persistence.createEntityManagerFactory("queercars_pu");
        EntityManager em = emf.createEntityManager();
        Administrator retadmin = em.find(Administrator.class, username);
        em.close();
        emf.close();
        return retadmin;
    }

}
