/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.queercars.servlets;

import edu.chl.queercars.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author johanssb
 */
public class UserLogin extends HttpServlet {

    EntityManagerFactory emf;
    Customer cust;
    
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

        //Session for tracking the logged in user.
        HttpSession session = request.getSession();

        String action = request.getParameter("action");
        String username = request.getParameter("username");
        try {
            if (action.equals("doLogin")) {
                cust = validateUser(username);
            }
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InitServlet</title>");
            out.println("</head>");
            out.println("<body>");
            if(cust != null){
                out.println("<p>Logged in user: " + cust.getId() + " " + cust.getFname() + "</p>");
                session.setAttribute("customerId", cust.getId());
            }
            else{
                out.println("<p>Epic fail!</p>");
            }
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    private Customer validateUser(String username) {
        emf = Persistence.createEntityManagerFactory("queercars_pu");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Customer retcust = em.find(Customer.class, username);
        em.close();
        emf.close();
        return retcust;
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
}
