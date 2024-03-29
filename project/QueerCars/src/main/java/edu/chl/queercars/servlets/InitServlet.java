package edu.chl.queercars.servlets;

import edu.chl.queercars.Administrator;
import edu.chl.queercars.Car;
import edu.chl.queercars.Customer;
import edu.chl.queercars.Model;
import edu.chl.queercars.NewsItem;
import edu.chl.queercars.Rental;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A servlet to init the database with boilerplate data useful for testing.
 * @author johanssb
 */
@WebServlet(name = "InitServlet", urlPatterns = {"/InitServlet"})
public class InitServlet extends HttpServlet {

    EntityManagerFactory emf;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InitServlet</title>");
            out.println("</head>");
            out.println("<body>");
            if (action == null) {
                showMenuPage(out);
            } else if (action.equals("recreate")) {
                recreateData(out);
            }
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    private void showMenuPage(PrintWriter out) {
        out.println("<p><a href=\"?action=recreate\">Create awesome boilerplate data for testing.</a></p>");
    }

    private void recreateData(PrintWriter out) {
        out.println("<p>Recreating data.</p>");
        emf = Persistence.createEntityManagerFactory("queercars_pu");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Customer cust1 = new Customer("5402034578","Lennart", "Lennieboy@mail.se");
        Customer cust2 = new Customer("6504068523","Emil", "emilio_12@mail.se");
        Customer cust3 = new Customer("7811128563","Filip", "Flippy_flopp@mail.se");
        Customer cust4 = new Customer("8401124512","Jens", "jens_cool_kille@mail.se");
        Customer cust5 = new Customer("7710102252","Mio", "mio_din_mio@mail.se");
        
        Model m1 = new Model("blackCar", 0.15, 200);
        Model m2 = new Model("queerCar", 0.1, 155 );
        Model m3 = new Model("mercuryCar", 0.08, 120);
        Model m4 = new Model("cadillacCar", 0.2, 250);
        Car car1 = new Car("ADD510", m1);
        Car car2 = new Car("CAR874", m2);
        Car car3 = new Car("ELK156", m3);
        Car car4 = new Car("MAN872", m4);
        Car car5 = new Car("NEW933", m1);
        Car car6 = new Car("RAD052", m2);
        Rental r1 = new Rental(cust1,car1);
        Rental r2 = new Rental(cust2,car2);
        Administrator admin1 = new Administrator("jakob", "Jakob");
        Administrator admin2 = new Administrator("damien", "Damien");
        NewsItem news1 = new NewsItem("Dewey defeats Nixon!", "OMG Dewey is new president! Totally true! Not at all a misjudgement!");
        NewsItem news2 = new NewsItem("LHC Disaster", "LHC created a huge black hole and swallowed the earth, is totally sorry.");
        NewsItem news3 = new NewsItem("Young people have fun", "Young people apparently like to do fun stuff, old people are appaled by this.");
        NewsItem news4 = new NewsItem("Nixon really sad", "Richard Nixon responded to initial report of his loss by being really sad and wanting to be alone for a while.");
        NewsItem news5 = new NewsItem("Nixon vindicated", "Promises not to be a jerk while in office, for reals.");

        tx.begin();
        em.persist(cust1);
        em.persist(cust2);
        em.persist(cust3);
        em.persist(cust4);
        em.persist(cust5);
        em.persist(m1);
        em.persist(m2);
        em.persist(m3);
        em.persist(m4);
        em.persist(car1);
        em.persist(car2);
        em.persist(car3);
        em.persist(car4);
        em.persist(car5);
        em.persist(car6);
        em.persist(r1);
        em.persist(r2);
        em.persist(admin1);
        em.persist(admin2);
        em.persist(news1);
        em.persist(news2);
        em.persist(news3);
        em.persist(news4);
        em.persist(news5);
        tx.commit();
        em.close();
        emf.close();
    }
}
