package edu.chl.queercars.servlets;

import edu.chl.queercars.Administrator;
import edu.chl.queercars.Car;
import edu.chl.queercars.Customer;
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
 * @author mviktor
 */
@WebServlet(name="AdminServlet", urlPatterns={"/AdminServlet"})
public class AdminServlet extends HttpServlet {
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
            //TODO real pages
            out.println("<html>");
            out.println("<head>");
            out.println("<title>AddCar</title>");
            out.println("</head>");
            out.println("<body>");
            if (action == null) {
                //TODO indexpage
                request.getRequestDispatcher("WEB-INF/adminIndex.xhtml").forward(request, response);
            } else if(action.equals("showIndexPage")){
                //TODO indexPage
                request.getRequestDispatcher("index.page.AWSWUZM").forward(request, response);
            } else if(action.equals("showCarPage")){
                //TODO adminPage
                request.getRequestDispatcher("car.page.AWSWUZM").forward(request, response);
            } else if(action.equals("showCustomerPage")){
                //TODO userPage
                request.getRequestDispatcher("customer.page.AWSWUZM").forward(request, response);
            } else if(action.equals("showAdministratorPage")){
                //TODO adminPage
                request.getRequestDispatcher("administrator.page.AWSWUZM").forward(request, response);

            } else if(action.equals("addCustomer")){
                addCustomer(request);
                response.sendRedirect("adminServlet?action=showCustomerPage");
            } else if(action.equals("removeCustomer")){
                removeCustomer(request);
                response.sendRedirect("adminServlet?action=showCustomerPage");

            } else if(action.equals("addAdministrator")){
                addAdministrator(request);
                response.sendRedirect("adminServlet?action=showAdministratorPage");
            } else if(action.equals("removeAdministrator")){
                removeAdministrator(request);
                response.sendRedirect("adminServlet?action=showAdministratorPage");

            } else if (action.equals("addCar")) {
                addCar(request);
                response.sendRedirect("adminServlet?action=showCarPage");
            } else if (action.equals("removeCar")){
                removeCar(request);
                response.sendRedirect("adminServlet?action=showCarPage");
            } else {
                request.getRequestDispatcher("index.page.AWSWUZM").forward(request, response);
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

    private void addCar(HttpServletRequest request) {
        emf = Persistence.createEntityManagerFactory("queercars_pu");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        //Create car from input in browser
        Car car = new Car(request.getParameter("id"));

        tx.begin();
        em.persist(car);
        tx.commit();

        em.close();
        emf.close();
    }

    private void removeCar(HttpServletRequest request) {
        emf = Persistence.createEntityManagerFactory("queercars_pu");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        //Get reference to the car with link in the browser
        Car car = em.getReference(Car.class, request.getParameter("id"));

        tx.begin();
        em.remove(car);
        tx.commit();

        em.close();
        emf.close();
    }

    private void addCustomer(HttpServletRequest request) {
        emf = Persistence.createEntityManagerFactory("queercars_pu");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        //Create new customer from information in browser
        Customer customer = new Customer(request.getParameter("id"), request.getParameter("fname"));

        tx.begin();
        em.persist(customer);
        tx.commit();

        em.close();
        emf.close();
    }

    private void removeCustomer(HttpServletRequest request) {
        emf = Persistence.createEntityManagerFactory("queercars_pu");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        //Get reference to the car with link in the browser
        Customer customer = em.getReference(Customer.class, request.getParameter("id"));

        tx.begin();
        em.remove(customer);
        tx.commit();

        em.close();
        emf.close();
    }
    //TODO possible refactor
    private void addAdministrator(HttpServletRequest request) {
        emf = Persistence.createEntityManagerFactory("queercars_pu");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        //Create new customer from information in browser
        Administrator administrator = new Administrator(request.getParameter("id"), request.getParameter("fname"));

        tx.begin();
        em.persist(administrator);
        tx.commit();

        em.close();
        emf.close();
    }
    //TODO possible refactor
    private void removeAdministrator(HttpServletRequest request) {
        emf = Persistence.createEntityManagerFactory("queercars_pu");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        //Get reference to the car with link in the browser
        Administrator administrator = em.getReference(Administrator.class, request.getParameter("id"));

        tx.begin();
        em.remove(administrator);
        tx.commit();

        em.close();
        emf.close();
    }

}