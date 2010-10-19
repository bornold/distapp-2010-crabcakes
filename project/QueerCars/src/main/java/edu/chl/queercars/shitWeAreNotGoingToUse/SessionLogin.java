/*
 * är det rätt, är det fel?
 */
package edu.chl.queercars.shitWeAreNotGoingToUse;

/*

JONAS MIKAEL BORNOLD

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

@WebServlet(name = "SessionLogin", urlPatterns = "/SessionLogin")
public class SessionLogin extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws IOException, ServletException {

	/*
	 *
	 * Hämtar och skapar en session
	 */

	HttpSession session = request.getSession();
	// SessionConnection är en egen klass som implimenterar HttpSessionBindingListener
	SessionConnection sessionConnection =
		(SessionConnection) session.getAttribute("sessionconnection");
	Connection connection = null;

	/*
	 * om det gick att hänta en session hämta dess databas connection
	 */
	if (sessionConnection != null) {
	    connection = sessionConnection.getConnection();
	} else {
	    //hitta på nägot att göra här
	}


	/*
	 * Om det inte redan finns en session
	 */
	if (connection == null) {
	    String userName = request.getParameter("username");
	    if (userName == null) {
		//TODO redirect to NEWUSER
		response.sendRedirect("queercars/queerclub.xhtml");

	    } else {
		/*
		 * Kollar om det finns en customer med användernamnet i databasen
		 *
		 * Skall man eller kan man gära sä här?
		 * Känns väldigt fel...
		 *
		 */
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("queercars_pu");
		EntityManager em = emf.createEntityManager();
		Customer retcust = em.find(Customer.class, userName);
		em.close();
		emf.close();

		if (retcust != null) {

		    /*
		     * Skapar ny session.
		     * Färstär inte varfär man behäver blanda in databasen här
		     * Finns det nägon bra anledning?
		     * Mäste man skriva in användarnamn och läsenord till databasen?
		     * Finns det nägot annat sätt att skapa en session?
		     */
		    try {
			connection = DriverManager.getConnection(
				"jdbc:derby://localhost:1527/queercars", "queercars", "manunderwood");
		    } catch (SQLException e) {
			System.err.println("Database not set up:\n\t" + e);
		    }
		    // lagra den i sessionen
		    sessionConnection = new SessionConnection();
		    sessionConnection.setConnection(connection);
		    session.setAttribute("sessionconnection", sessionConnection);

		    //TODO Inloggning lyckades
		    request.getRequestDispatcher("/WEB-INF/logginWelcome.xhtml").forward(request, response);

		} else {
		    //TODO Fel inloggningsuppgifter
		    response.sendRedirect("failure.xhtml");
		}
	    }
	} else {
	    String logout = request.getParameter("logout");
	    if (logout == null) {
		//TODO redan inloggad
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