/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.queercars.servlets;

import edu.chl.queercars.Administrator;
import edu.chl.queercars.Car;
import edu.chl.queercars.Customer;
import edu.chl.queercars.Model;
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
 *
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

        Customer cust1 = new Customer("4412121234","Lennart");
        Customer cust2 = new Customer("3312121234","Emil");
        Customer cust3 = new Customer("2212121234","Filip");
        Customer cust4 = new Customer("1112121234","Jens");
        Customer cust5 = new Customer("5512121234","Mio");
        Model m1 = new Model("blackCar", "black_car.jpg");
        Model m2 = new Model("queerCar", "queer_car.jpg");
        Model m3 = new Model("mercuryCar", "mercury.jpg");
        Model m4 = new Model("cadillacCar", "cadillac.jpg");
        Car car1 = new Car("KKN111", m1);
        Car car2 = new Car("KKN222", m2);
        Car car3 = new Car("KKN333", m3);
        Car car4 = new Car("KKN444", m4);
        Car car5 = new Car("KKN555", m1);
        Car car6 = new Car("KKN666", m2);
        Administrator admin1 = new Administrator("jakob", "Jakob");
        Administrator admin2 = new Administrator("damien", "Damien");

        tx.begin();
        em.persist(cust1);
        em.persist(cust2);
        em.persist(cust3);
        em.persist(cust4);
        em.persist(cust5);
        em.persist(car1);
        em.persist(car2);
        em.persist(car3);
        em.persist(car4);
        em.persist(car5);
        em.persist(car6);
        em.persist(admin1);
        em.persist(admin2);
        tx.commit();
        em.close();
        emf.close();
    }
}
