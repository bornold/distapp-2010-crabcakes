package edu.chl.queercars.servlets;

import edu.chl.queercars.Administrator;
import edu.chl.queercars.Car;
import edu.chl.queercars.Customer;
import edu.chl.queercars.Model;
import edu.chl.queercars.NewsItem;
import edu.chl.queercars.dbhandlers.AdministratorHandler;
import edu.chl.queercars.dbhandlers.CarHandler;
import edu.chl.queercars.dbhandlers.CustomerHandler;
import edu.chl.queercars.dbhandlers.IAdministratorHandler;
import edu.chl.queercars.dbhandlers.ICarHandler;
import edu.chl.queercars.dbhandlers.ICustomerHandler;
import edu.chl.queercars.dbhandlers.INewsItemHandler;
import edu.chl.queercars.dbhandlers.NewsItemHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManagerFactory;
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
    ICustomerHandler custHandler = new CustomerHandler(emf);
    IAdministratorHandler adminHandler = new AdministratorHandler(emf);
    ICarHandler carHandler = new CarHandler(emf);
    INewsItemHandler newsHandler = new NewsItemHandler(emf);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");

        if (action == null) {
            request.getRequestDispatcher("WEB-INF/adminindex.xhtml").forward(request, response);
        } else if (action.equals("showCarPage")) {
            request.getRequestDispatcher("WEB-INF/careditor.xhtml").forward(request, response);
        } else if (action.equals("showCustomerPage")) {
            request.getRequestDispatcher("WEB-INF/customereditor.xhtml").forward(request, response);
        } else if (action.equals("showAdministratorPage")) {
            request.getRequestDispatcher("WEB-INF/admineditor.xhtml").forward(request, response);

        } else if (action.equals("showNewsEditorPage")) {
            request.getRequestDispatcher("WEB-INF/newseditor.xhtml").forward(request, response);

        } else if (action.equals("saveCustomer")) {
            custHandler.saveCustomer(new Customer(request.getParameter("customerId"), request.getParameter("customerName")));
            sendCustomerTable(response);
        } else if (action.equals("removeCustomer")) {
            custHandler.removeCustomer(request.getParameter("customerId"));
            sendCustomerTable(response);

        } else if (action.equals("saveAdministrator")) {
            adminHandler.saveAdministrator(new Administrator(request.getParameter("adminId"), request.getParameter("adminName")));
            sendAdministratorTable(response);
        } else if (action.equals("removeAdministrator")) {
            adminHandler.removeAdministrator(request.getParameter("adminId"));
            sendAdministratorTable(response);

        } else if (action.equals("saveCar")) {
            carHandler.saveCar(new Car(request.getParameter("carId"), new Model(request.getParameter("carModel"))));
            sendCarTable(response);
        } else if (action.equals("removeCar")) {
            carHandler.removeCar(request.getParameter("carId"));
            sendCarTable(response);


        } else if (action.equals("saveNewsItem")) {
            String newsId = request.getParameter("newsId");
            if(newsId.equals("")){
                newsHandler.saveNewsItem(new NewsItem(request.getParameter("newsHeadline"), request.getParameter("newsContent")));
            } else {
                newsHandler.saveNewsItem(new NewsItem(Long.parseLong(newsId), request.getParameter("newsHeadline"), request.getParameter("newsContent")));
            }
            sendNewsItems(response);
        } else if (action.equals("removeNewsItem")) {
            newsHandler.removeNewsItem(Long.parseLong(request.getParameter("newsId")));
            sendNewsItems(response);


        } else if (action.equals("getCustomerTable")) {
            sendCustomerTable(response);
        } else if (action.equals("getAdministratorTable")) {
            sendAdministratorTable(response);
        } else if (action.equals("getCarTable")) {
            sendCarTable(response);
        } else if (action.equals("getNewsItems")) {
            sendNewsItems(response);
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

    private void sendAdministratorTable(HttpServletResponse response) throws IOException {
        List<Administrator> allAdministrators = adminHandler.getAllAdministrators();
        String tableHeader = "<table>\n<tr><th>id</th><th>name</th></tr>\n";
        String tableFooter = "</table>";
        String output = tableHeader;

        for (Administrator administrator : allAdministrators) {
            String editButton = "<input type=\"submit\" class=\"editButton\" id=\"" + administrator.getId() + "\" value=\"edit\"/>";
            String removeButton = "<input type=\"submit\" class=\"removeButton\" id=\"" + administrator.getId() + "\" value=\"remove\"/>";
            String row = "<tr><td>" + administrator.getId() + "</td><td>" + administrator.getFname() + "</td><td>" + editButton + "</td><td>" + removeButton + "</td></tr>\n";
            output += row;
        }

        output += tableFooter;
        PrintWriter out = response.getWriter();
        out.println(output);
        out.close();
    }

    private void sendCarTable(HttpServletResponse response) throws IOException {
        List<Car> allCars = carHandler.getAllCars();
        String tableHeader = "<table>\n<tr><th>id</th><th>model</th></tr>\n";
        String tableFooter = "</table>";
        String output = tableHeader;

        for (Car car : allCars) {
            String editButton = "<input type=\"submit\" class=\"editButton\" id=\"" + car.getId() + "\" value=\"edit\"/>";
            String removeButton = "<input type=\"submit\" class=\"removeButton\" id=\"" + car.getId() + "\" value=\"remove\"/>";
            String row = "<tr><td>" + car.getId() + "</td><td>" + car.getModel() + "</td><td>" + editButton + "</td><td>" + removeButton + "</td></tr>\n";
            output += row;
        }

        output += tableFooter;
        PrintWriter out = response.getWriter();
        out.println(output);
        out.close();
    }

    private void sendNewsItems(HttpServletResponse response) throws IOException {
        List<NewsItem> allNews = newsHandler.getAllNewsItems();
        String output = "";

        for (NewsItem newsItem : allNews) {
            output += "<p><h3>" + newsItem.getHeadline() + "</h3>\n" +
                    "<p>" + newsItem.getContent() + "</p><input type=\"button\" value=\"edit\" class=\"editButton\" id=\"" + newsItem.getId() + "\"/>" + "<input type=\"button\" value=\"remove\" class=\"removeButton\" id=\"" + newsItem.getId() + "\"/>" + "</p>";
        }

        PrintWriter out = response.getWriter();
        out.println(output);
        out.close();
    }
}
