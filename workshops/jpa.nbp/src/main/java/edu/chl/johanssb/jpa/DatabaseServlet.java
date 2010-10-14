/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.johanssb.jpa;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author flipmo
 */
@WebServlet(name = "DatabaseServlet", urlPatterns = {"/DatabaseServlet"})
public class DatabaseServlet extends HttpServlet {

    //IDatabase db = new DatabaseMockup();
    IDatabase db = new DatabaseImpl();

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
        if (action.equals("saveNew")) {
            String prodName = request.getParameter("productName");
            String prodCat = request.getParameter("productCategory");
            String prodPrice = request.getParameter("productPrice");
            db.addProduct(new Product(prodName, prodCat, Double.valueOf(prodPrice)));
            buildTableResponse(response);
        } else if (action.equals("update")) {
            String prodId = request.getParameter("productId");
            String prodName = request.getParameter("productName");
            String prodCat = request.getParameter("productCategory");
            String prodPrice = request.getParameter("productPrice");
            Product updated = new Product(prodName, prodCat, Double.valueOf(prodPrice));
            updated.setId(Long.valueOf(prodId));
            db.updateProduct(updated);
            buildTableResponse(response);
        } else if (action.equals("remove")) {
            String prodId = request.getParameter("productId");
            db.removeProduct(Long.valueOf(prodId));
            buildTableResponse(response);
        } else if (action.equals("getAll")) {
            buildTableResponse(response);
        }
    }

    private void buildTableResponse(HttpServletResponse response) throws IOException{
        List<Product> allP = db.getAllProducts();
        String tableHeader = "<table><tr><td>id</td><td>name</td><td>category</td><td>price</td><td></td><td></td></tr>";

        String tableFooter = "</table>";

        String completeTable = tableHeader;

        for (Product product : allP) {
            String buttons = "</td><td>" + "<input type=\"submit\" class=\"editButton\" id=\"" + product.getId() + "\" value=\"edit\"/>" + "</td><td>"
                    + "<input type=\"submit\" class=\"deleteButton\" id=\"" + product.getId() + "\" value=\"delete\"/>";

            String s = "<tr><td>" + product.getId() + "</td><td>" + product.getName()
                    + "</td><td>" + product.getCat() + "</td><td>" + product.getPrice() + buttons + "</td></tr>";
            completeTable += s;
        }
        completeTable += tableFooter;
        PrintWriter out = response.getWriter();
        out.println(completeTable);
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
