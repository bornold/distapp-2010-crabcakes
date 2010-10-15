package edu.chl.queercars.servlets;

import edu.chl.queercars.Administrator;
import edu.chl.queercars.Car;
import edu.chl.queercars.Customer;
import edu.chl.queercars.Model;
import edu.chl.queercars.dbhandlers.CustomerHandler;
import edu.chl.queercars.dbhandlers.ICustomerHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("queercars_pu");
    ICustomerHandler custHandler = new CustomerHandler();

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
        String action = request.getParameter("action");
        System.out.println("************************************");
        //TODO real pages
        if (action == null) {
            //TODO indexpage
            //Show initial admin navigation page.
            request.getRequestDispatcher("WEB-INF/adminindex.xhtml").forward(request, response);
        } else if (action.equals("showCarPage")) {
            //TODO adminPage
            request.getRequestDispatcher("WEB-INF/careditor.xhtml").forward(request, response);
        } else if (action.equals("showCustomerPage")) {
            //TODO userPage
            request.getRequestDispatcher("WEB-INF/customereditor.xhtml").forward(request, response);
        } else if (action.equals("showAdministratorPage")) {
            //TODO adminPage
            request.getRequestDispatcher("WEB-INF/admineditor.xhtml").forward(request, response);

        } else if (action.equals("saveCustomer")) {
            saveCustomer(request,response);
        } else if (action.equals("removeCustomer")) {
            removeCustomer(request, response);

        } else if (action.equals("addAdministrator")) {
            addAdministrator(request);
            response.sendRedirect("AdminServlet?action=showAdministratorPage");
        } else if (action.equals("removeAdministrator")) {
            removeAdministrator(request);
            response.sendRedirect("AdminServlet?action=showAdministratorPage");

        } else if (action.equals("addCar")) {
            addCar(request);
            response.sendRedirect("AdminServlet?action=showCarPage");
        } else if (action.equals("removeCar")) {
            removeCar(request);
            response.sendRedirect("AdminServlet?action=showCarPage");
        } else if (action.equals("getCustomerTable")) {
            sendCustomerTable(response);
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
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        //Create car from input in browser
        Car car = new Car(request.getParameter("id"), new Model(request.getParameter("model")));

        tx.begin();
        em.persist(car);
        tx.commit();

        em.close();
    }

    private void removeCar(HttpServletRequest request) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        //Get reference to the car with link in the browser
        Car car = em.getReference(Car.class, request.getParameter("id"));

        tx.begin();
        em.remove(car);
        tx.commit();

        em.close();
    }

    private void saveCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //Create new customer from information in browser
        Customer customer = new Customer(request.getParameter("customerId"), request.getParameter("customerName"));
        custHandler.addCustomer(customer);
        sendCustomerTable(response);
    }

    private void removeCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Customer customer = new Customer(request.getParameter("customerId"), request.getParameter("customerName"));
        custHandler.removeCustomer(customer.getId());
        sendCustomerTable(response);
    }
    //TODO possible refactor

    private void addAdministrator(HttpServletRequest request) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        //Create new customer from information in browser
        Administrator administrator = new Administrator(request.getParameter("id"), request.getParameter("fname"));

        tx.begin();
        em.persist(administrator);
        tx.commit();

        em.close();
    }
    //TODO possible refactor

    private void removeAdministrator(HttpServletRequest request) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        //Get reference to the car with link in the browser
        Administrator administrator = em.getReference(Administrator.class, request.getParameter("id"));

        tx.begin();
        em.remove(administrator);
        tx.commit();

        em.close();
    }

    /*
     * Method to return a HTML table of customers with headers, content and individualized buttons for editing and deleting users.
     */
    private void sendCustomerTable(HttpServletResponse response) throws IOException {
        List<Customer> allCustomers = custHandler.getAllCustomers();
        String tableHeader = "<table>\n<tr><th>id</th><th>name</th></tr>\n";
        String tableFooter = "</table>";
        String output = tableHeader;
        for (Customer customer : allCustomers) {
            String editButton = "<input type=\"submit\" class=\"editButton\" id=\"" + customer.getId() + "\" value=\"edit\"/>";
            String removeButton = "<input type=\"submit\" class=\"removeButton\" id=\"" + customer.getId() + "\" value=\"remove\"/>";
            String row = "<tr><td>" + customer.getId() + "</td><td>" + customer.getFname() + "</td><td>" + editButton + "</td><td>" + removeButton + "</td></tr>\n";
            output += row;
        }
        output += tableFooter;
        PrintWriter out = response.getWriter();
        out.println(output);
        out.close();
    }
}
