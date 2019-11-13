package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.util.SQLPropertiesUtil;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogInitServlet
 */
@WebServlet("/LogInitServlet")
public class LogInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void init() {
	   try {
	      String fileName = getInitParameter( "log4j.init.file" );
	      System.out.println( "CorisWEB reading logging configuration from: " + fileName + ".properties"); // Filename = logging.properties
	      if( fileName != null ) {
	        java.util.ResourceBundle bundle = ResourceBundle.getBundle( "gov.utcourts.coriscommon.properties." + fileName );
	        java.util.Properties log4jProps = SQLPropertiesUtil.getPropertiesFromResourceBundle( bundle );
	        org.apache.log4j.PropertyConfigurator.configure( log4jProps );
	      }
	      else {
	        System.out.println("log4j.init.file not defined.");
	      }
	   } catch( Throwable t ) {
	      System.out.println( "Error configuring logging system: " + t );
	   }
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public LogInitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
