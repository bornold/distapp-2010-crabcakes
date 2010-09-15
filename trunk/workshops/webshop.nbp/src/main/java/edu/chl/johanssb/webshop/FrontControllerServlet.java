/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.johanssb.webshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.mail.internet.*;
import javax.mail.event.*;

/**
 *
 * @author flipmo
 */
@WebServlet(name = "FrontControllerServlet", urlPatterns = {"/FrontControllerServlet"})
public class FrontControllerServlet extends HttpServlet {

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

        //Create a session to store the shopping cart.
        HttpSession session = request.getSession();

        //If cart is uninitialized, create a new empty cart.
        if (session.getAttribute("items") == null) {
            session.setAttribute("items", new ArrayList<String>());
        }

        String action = request.getParameter("action");
        if (action.equals("shop")) {
            //Load shop page.
            request.getRequestDispatcher("WEB-INF/jsp/shop.jspx").forward(request, response);
        } else if (action.equals("viewCart")) {
            //Load cart page.
            request.getRequestDispatcher("WEB-INF/jsp/viewCart.jspx").forward(request, response);
        } else if (action.equals("pay")) {
            //Load payment page.
            request.getRequestDispatcher("WEB-INF/jsp/pay.jspx").forward(request, response);
        } else if (action.equals("exit")) {
            try {
                Properties props = new Properties();
                props.put("mail.smtp.host", "mail.chalmers.se");
                Session mailSession = Session.getDefaultInstance(props, null);
                Message message = new MimeMessage(mailSession);
                message.setFrom(new InternetAddress("bjorn.johansson@gmail.com"));
                message.setRecipient(Message.RecipientType.TO, new InternetAddress("johanssb@student.chalmers.se"));
                message.setSubject("Your order of exotic fruits");
                
                String messageBody = "You have ordered:\n";
                for (String s : (ArrayList<String>)session.getAttribute("items")) {
                    messageBody = messageBody + s + "\n";
                }
                messageBody = messageBody + "GIMME ALL YOUR MONEYS";
                message.setText(messageBody);
                System.out.println(messageBody);
                Transport.send(message);

            } catch (MessagingException ex) {
                System.out.println(ex);
            }
            session.invalidate();
            //Load exit page.
            request.getRequestDispatcher("WEB-INF/jsp/exit.jspx").forward(request, response);
        } else if (action.equals("addItem")) {
            ArrayList<String> items = (ArrayList<String>) session.getAttribute("items");
            items.add(request.getParameter("item"));
            session.setAttribute("items", items);
            session.setAttribute("numItems", items.size());
            //Go back to shop more!
            request.getRequestDispatcher("WEB-INF/jsp/shop.jspx").forward(request, response);
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
