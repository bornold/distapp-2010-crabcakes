/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.johanssb.bwa;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author flipmo
 */
@WebServlet(name="FormServlet", urlPatterns={"/FormServlet"})
public class FormServlet extends HttpServlet {
   
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

        String submitButton = request.getParameter("submitButton");
        String input = request.getParameter("input");
        String volvo = request.getParameter("volvo");
        String saab = request.getParameter("saab");
        String porsche = request.getParameter("porsche");
        String cars = request.getParameter("cars");
        String dropCars = request.getParameter("dropCars");

        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FormServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FormServlet at " + request.getContextPath () + "</h1>");
            out.println("<p>Wall-o-Text:</p>");
            out.println("<p>Incoming data (submitButton): " + submitButton + "</p>");
            out.println("<p>Incoming data (input): " + input + "</p>");
            out.println("<p>Incoming data (volvo): " + volvo + "</p>");
            out.println("<p>Incoming data (saab): " + saab + "</p>");
            out.println("<p>Incoming data (porsche): " + porsche + "</p>");
            out.println("<p>Incoming data (cars): " + cars + "</p>");
            out.println("<p>Incoming data (dropCars): " + dropCars + "</p>");
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

}
