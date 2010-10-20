package edu.chl.queercars.servlets;

import edu.chl.queercars.Administrator;
import edu.chl.queercars.Car;
import edu.chl.queercars.Customer;
import edu.chl.queercars.Model;
import edu.chl.queercars.NewsItem;
import edu.chl.queercars.Rental;
import edu.chl.queercars.beans.AdminLoginModelBean;
import edu.chl.queercars.dbhandlers.AdministratorHandler;
import edu.chl.queercars.dbhandlers.CarHandler;
import edu.chl.queercars.dbhandlers.CustomerHandler;
import edu.chl.queercars.dbhandlers.ModelHandler;
import edu.chl.queercars.dbhandlers.NewsItemHandler;
import edu.chl.queercars.dbhandlers.RentalHandler;
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
import javax.servlet.http.HttpSession;

/**
 * @author mviktor
 */
@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("queercars_pu");
    CustomerHandler custHandler = new CustomerHandler(emf);
    AdministratorHandler adminHandler = new AdministratorHandler(emf);
    CarHandler carHandler = new CarHandler(emf);
    NewsItemHandler newsHandler = new NewsItemHandler(emf);
    ModelHandler modelHandler = new ModelHandler(emf);
    RentalHandler rentalHandler = new RentalHandler(emf);

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

        HttpSession session = request.getSession();
        AdminLoginModelBean almb = (AdminLoginModelBean) session.getAttribute("adminLoginModelBean");

        if (almb == null || almb.getId() == null) {
            response.sendRedirect("adminlogin.xhtml");
        } else {
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
            } else if (action.equals("showRentalEditorPage")) {
                request.getRequestDispatcher("WEB-INF/rentaleditor.xhtml").forward(request, response);

                // This is not how you do it!!
                // // How do you do it?
            } else if (action.equals("logout")) {
                //almb.setId(null);
                //almb.setFName(null);
                session.invalidate();
                response.sendRedirect("index.xhtml");

            } else if (action.equals("saveCustomer")) {
                custHandler.saveCustomer(new Customer(request.getParameter("customerId"), request.getParameter("customerName"), request.getParameter("customerEmail")));
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
                Model carModel;
                if (request.getParameter("carModel").equals("createNewModel")) { //Creating a new model.
                    System.out.println("using a new model.");
                    carModel = new Model(request.getParameter("newModelId"), Double.parseDouble(request.getParameter("modelFuelConsumption")), Integer.parseInt(request.getParameter("modelEmission")));
                    modelHandler.saveModel(carModel);
                } else {
                    System.out.println("using existing model.");
                    carModel = modelHandler.getModel(request.getParameter("carModel"));
                }

                Car car = carHandler.getCar(request.getParameter("carId"));
                if (car != null) { //Car exists.
                    System.out.println("using an existing car.");
                    car.setModel(carModel);
                    carHandler.saveCar(car);
                } else { //Car is new.
                    System.out.println("using a new car.");
                    car = new Car(request.getParameter("carId"), carModel);
                    carHandler.saveCar(car);
                }
                sendCarTable(response);
            } else if (action.equals("removeCar")) {
                carHandler.removeCar(request.getParameter("carId"));
                sendCarTable(response);

            } else if (action.equals("saveNewsItem")) {
                String newsId = request.getParameter("newsId");
                if (newsId.equals("")) {
                    newsHandler.saveNewsItem(new NewsItem(request.getParameter("newsHeadline"), request.getParameter("newsContent")));
                } else {
                    newsHandler.saveNewsItem(new NewsItem(Long.parseLong(newsId), request.getParameter("newsHeadline"), request.getParameter("newsContent")));
                }
                sendNewsItems(response);
            } else if (action.equals("removeNewsItem")) {
                newsHandler.removeNewsItem(Long.parseLong(request.getParameter("newsId")));
                sendNewsItems(response);

            } else if (action.equals("endRental")) {
                rentalHandler.endRental(request.getParameter("rentalId"), request.getParameter("odometerValue"));
                sendActiveRentals(response);

            } else if (action.equals("getCustomerTable")) {
                sendCustomerTable(response);
            } else if (action.equals("getAdministratorTable")) {
                sendAdministratorTable(response);
            } else if (action.equals("getCarTable")) {
                sendCarTable(response);
            } else if (action.equals("getNewsItems")) {
                sendNewsItems(response);
            } else if (action.equals("getModels")) {
                sendModels(response);
            } else if (action.equals("getActiveRentalsList")) {
                sendActiveRentals(response);
            }
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

    /**
     * Method to return a HTML table of customers with headers, content and individualized buttons for editing and deleting users.
     * @param response the httpServletResponse to get the printWriter from
     */
    private void sendCustomerTable(HttpServletResponse response) throws IOException {
        List<Customer> allCustomers = custHandler.getAllCustomers();
        String tableHeader = "<table>\n<tr><th>id</th><th>name</th><th>email</th></tr>\n";
        String tableFooter = "</table>";
        String output = tableHeader;

        for (Customer customer : allCustomers) {
            String editButton = "<input type=\"submit\" class=\"editButton\" id=\"" + customer.getId() + "\" value=\"edit\"/>";
            String removeButton = "<input type=\"submit\" class=\"removeButton\" id=\"" + customer.getId() + "\" value=\"remove\"/>";
            String row = "<tr><td>" + customer.getId() + "</td><td>" + customer.getFname() + "</td><td>" + customer.getEmail() + "</td><td>" + editButton + "</td><td>" + removeButton + "</td></tr>\n";
            output += row;
        }

        output += tableFooter;
        PrintWriter out = response.getWriter();
        out.println(output);
        out.close();
    }

    /**
     * Method to return a HTML table of admins with headers, content and individualized buttons for editing and deleting admins.
     * @param response the httpServletResponse to get the printWriter from
     */
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

    /**
     * Method to return a HTML table of cars with headers, content and individualized buttons for editing and deleting cars.
     * @param response the httpServletResponse to get the printWriter from
     */
    private void sendCarTable(HttpServletResponse response) throws IOException {
        List<Car> allCars = carHandler.getAllCars();
        String tableHeader = "<table border=\"1\">\n<tr><th>id</th><th>model</th><th>co2 emission</th><th>fuel consumtion</th><th>odometer</th></tr>\n";
        String tableFooter = "</table>";
        String output = tableHeader;

        for (Car car : allCars) {
            String editButton = "<input type=\"submit\" class=\"editButton\" id=\"" + car.getId() + "\" value=\"edit\"/>";
            String removeButton = "<input type=\"submit\" class=\"removeButton\" id=\"" + car.getId() + "\" value=\"remove\"/>";
            String row = "<tr><td>" + car.getId() + "</td><td>" + car.getModel() + "</td><td>" + car.getModel().getEmission() + "</td><td>" + car.getModel().getFuelConsumption() + "</td><td>" + car.getOdometer() + "</td><td>" + editButton + "</td><td>" + removeButton + "</td></tr>\n";
            output += row;
        }

        output += tableFooter;
        PrintWriter out = response.getWriter();
        out.println(output);
        out.close();
    }

    /**
     * Method to return a HTML table of news items with headers, content and individualized buttons for editing and deleting newsItems.
     * @param response the httpServletResponse to get the printWriter from
     */
    private void sendNewsItems(HttpServletResponse response) throws IOException {
        List<NewsItem> allNews = newsHandler.getAllNewsItems();
        String output = "";

        for (NewsItem newsItem : allNews) {
            output += "<p><h3>" + newsItem.getHeadline() + "</h3>\n"
                    + "<p>" + newsItem.getContent() + "</p><input type=\"button\" value=\"edit\" class=\"editButton\" id=\"" + newsItem.getId() + "\"/>" + "<input type=\"button\" value=\"remove\" class=\"removeButton\" id=\"" + newsItem.getId() + "\"/>" + "</p>";
        }

        PrintWriter out = response.getWriter();
        out.println(output);
        out.close();
    }

    private void sendModels(HttpServletResponse response) throws IOException {
        List<Model> allModels = modelHandler.getAllModels();
        String output = "";
        for (Model model : allModels) {
            output += "<option class=\"existingModel\" value=\"" + model.getId() + "\">" + model.getId() + "</option>\n";
        }
        output += "<option id=\"newModelSelection\" value=\"createNewModel\">New...</option>\n";
        PrintWriter out = response.getWriter();
        out.println(output);
    }

    private void sendActiveRentals(HttpServletResponse response) throws IOException {
        List<Rental> allActiveRentals = rentalHandler.getActiveRentals();
        String output = "<table border=\"1\"><tr><th>rental id</th><th>car id</th><th>customer id</th><th>odometer value</th></tr>";

        for (Rental rental : allActiveRentals) {
            output += "<tr><td>" + rental.getId() + "</td><td>" + rental.getCar().getId() + "</td><td>" + rental.getCustomer().getId() + "</td><td>" + rental.getCar().getOdometer() + "</td>";
            output += "<td><input type=\"button\" id=\"" + rental.getId() + "\" class=\"endButton\" value=\"End\"/></td>";
            output += "</tr>";
        }
        output += "</table>";
        PrintWriter out = response.getWriter();
        out.println(output);
    }
}
