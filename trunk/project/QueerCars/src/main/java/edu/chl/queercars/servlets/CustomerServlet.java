/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.queercars.servlets;

import edu.chl.queercars.Car;
import edu.chl.queercars.Customer;
import edu.chl.queercars.NewsItem;
import edu.chl.queercars.beans.LoginModelBean;
import edu.chl.queercars.dbhandlers.CarHandler;
import edu.chl.queercars.dbhandlers.NewsItemHandler;
import edu.chl.queercars.dbhandlers.RentalHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet for usage by the customer page
 * @author johanssb
 */
public class CustomerServlet extends HttpServlet {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("queercars_pu");
    CarHandler carHandler = new CarHandler(emf);
    NewsItemHandler newsHandler = new NewsItemHandler(emf);
    RentalHandler rentalHandler = new RentalHandler(emf);

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
        HttpSession session = request.getSession();
        LoginModelBean lmb = (LoginModelBean) session.getAttribute("loginModelBean");
        boolean loggedIn = (lmb != null && lmb.getId() != null); // Valid session.

        String action = request.getParameter("action");
        if (action == null) {
        } else if (action.equals("getDetailedCarTable")) {
            sendDetailedCarTable(response);
        } else if (action.equals("getNewsFeed")) {
            sendNewsFeed(response);
        } else if (action.equals("doRental")) {
            if (loggedIn) {
                System.out.println(lmb.getId() + " " + lmb.getName());
                rentalHandler.rentCar(new Customer(lmb.getId(), lmb.getName(), null), new Car(request.getParameter("carId"), null));
            }
        } else if (action.equals("isLoggedIn")) {
            PrintWriter out = response.getWriter();
            System.out.println(loggedIn);
            out.println(loggedIn);
            out.close();
        }
    }

    /**
     * Sends detaild car table to show on the page
     * @param response the servlet response
     * @throws IOException
     */
    private void sendDetailedCarTable(HttpServletResponse response) throws IOException {
        List<Car> allCars = carHandler.getAllAvailableCars();
        String tableHeader = "<table>";
        String tableFooter = "</table>";

        String output = tableHeader;
        for (Car car : allCars) {
            String rentButtonTag = "<input type=\"submit\" class=\"rentButton\" id=\"" + car.getId() + "\" value=\"rent this car\"/>";
            String showInfoButtonTag = "<input type=\"submit\" class=\"showButton\" id=\"" + car.getModel() + "\" value=\"show Info\" />";
            String imgTag = "<img src=\"images/" + car.getModel().getImgFileName() + "\"/>";

            String row = "<tr><td>" + car.getId() + "</td><td>" + imgTag + "</td><td>" + showInfoButtonTag + "</td><td>" + rentButtonTag + "</td></tr>";
            output += row;
        }

        output += tableFooter;
        PrintWriter out = response.getWriter();
        out.println(output);
        out.close();
    }

    /**
     *
     * @param response servlet rseponse
     * @throws IOException if IOExeption occurs
     */
    private void sendNewsFeed(HttpServletResponse response) throws IOException {
        List<NewsItem> allNews = newsHandler.getAllNewsItems();
        String newsFeed = "";
        for (NewsItem newsItem : allNews) {
            String newsEntry = "<p><b>" + newsItem.getEntryDate().toString() + " " + newsItem.getHeadline() + "</b><br/>"
                    + newsItem.getContent() + "</p>";
            newsFeed += newsEntry;
        }
        PrintWriter out = response.getWriter();
        out.println(newsFeed);
        out.close();
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
