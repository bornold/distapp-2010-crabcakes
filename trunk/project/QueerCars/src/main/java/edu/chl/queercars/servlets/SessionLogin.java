/*
 * Är det rätt, är det fel?
 */
package edu.chl.queercars.servlets;

/*

JONAS MIKAEL FUCKIN' BORNOLD

 */
import edu.chl.queercars.Customer;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

@WebServlet(name = "SessionLogin", urlPatterns = "/SessionLogin")
public class SessionLogin extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws IOException, ServletException {

	HttpSession session = request.getSession();
	SessionConnection sessionConnection = (SessionConnection) session.getAttribute("sessionconnection");
	Connection connection = null;
	if (sessionConnection != null) {
	    connection = sessionConnection.getConnection();
	}
	if (connection == null) {
	    String userName = request.getParameter("username");
	    if (userName == null) {

		//TODO redirect to NEW USER
		response.sendRedirect("queercars/queerclub.xhtml");


	    } else {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("queercars_pu");
		EntityManager em = emf.createEntityManager();
		Customer retcust = em.find(Customer.class, userName);
		em.close();
		emf.close();
		if (retcust != null) {
		    // create the connection
		    try {
			connection = DriverManager.getConnection(
				"jdbc:derby://localhost:1527/queercars", "queercars", "manunderwood");
		    } catch (SQLException e) {
			System.err.println("Database not set up:\n\t" + e);
		    }
		    // store the connection
		    sessionConnection = new SessionConnection();
		    sessionConnection.setConnection(connection);
		    session.setAttribute("sessionconnection", sessionConnection);

		    //TODO Rätt inloggningsuppfifter
		    request.getRequestDispatcher("/WEB-INF/logginWelcome.xhtml").forward(request, response);

		} else {
		    //TODO Fel inloggningsuppgifter
		    response.setContentType("loginError");
		    response.sendRedirect("failure.xhtml");
		}
	    }
	} else {
	    String logout = request.getParameter("logout");
	    if (logout == null) {
		//TODO Redirect to LOGGED IN
		response.sendRedirect("index.xhtml");

	    } else {
		// close the connection and remove it from the session
		try {
		    connection.close();
		} catch (SQLException ignore) {
		}
		session.removeAttribute("sessionconnection");
		//TODO Logged out
		request.getRequestDispatcher("/WEB-INF/loggout.xhtml").forward(request, response);

	    }
	}
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws IOException, ServletException {
	doGet(request, response);
    }
}

class SessionConnection implements HttpSessionBindingListener {

    Connection connection;

    public SessionConnection() {
	connection = null;
    }

    public SessionConnection(Connection connection) {
	this.connection = connection;
    }

    public Connection getConnection() {
	return connection;
    }

    public void setConnection(Connection connection) {
	this.connection = connection;
    }

    public void valueBound(HttpSessionBindingEvent event) {
	if (connection != null) {
	    System.out.println("Binding a valid connection");
	} else {
	    System.out.println("Binding a null connection");
	}
    }

    public void valueUnbound(HttpSessionBindingEvent event) {
	if (connection != null) {
	    System.out.println("Closing the bound connection as the session expires");
	    try {
		connection.close();
	    } catch (SQLException ignore) {
	    }
	}
    }
}
