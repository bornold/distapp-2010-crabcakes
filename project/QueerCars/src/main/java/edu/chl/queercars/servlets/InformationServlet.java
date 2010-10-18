/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.queercars.servlets;

import edu.chl.queercars.Car;
import edu.chl.queercars.dbhandlers.CarHandler;
import edu.chl.queercars.dbhandlers.ICarHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author johanssb
 */
public class InformationServlet extends HttpServlet {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("queercars_pu");
    ICarHandler carHandler = new CarHandler(emf);

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
        if (action == null) {
        } else if (action.equals("getDetailedCarTable")) {
            sendDetailedCarTable(response);
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

    private void sendDetailedCarTable(HttpServletResponse response) throws IOException {
        List<Car> allCars = carHandler.getAllCars();
        String tableHeader = "<table>";
        String tableFooter = "</table>";
        String output = tableHeader;
        for (Car car : allCars) {
            String imgTag = "<img src=images/\"" + car.getModel().getImgFileName() + "/>";
            String row = "<tr><td>" + car.getId() + "</td><td>" + car.getModel() + "</td><td>" + imgTag + "</td></tr>";
            output += row;
        }

        output += tableFooter;
        PrintWriter out = response.getWriter();
        out.println(output);
        out.close();
        System.out.println(output);
    }
}
