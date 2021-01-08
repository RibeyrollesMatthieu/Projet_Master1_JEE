package server.servlets;

import server.database.SQLConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Ribeyrolles Matthieu
 * 08/01/2021, 19:56
 */
public class PlacesServlet extends HttpServlet {
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  private void addPlace(HttpServletRequest req) throws SQLException {
    SQLConnector.getInstance().doRequest(String.format(
      "INSERT INTO places(name, address) VALUES('%s', '%s')", req.getParameter("name"), req.getParameter("address")
    ), true);
  }
  // public

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.sendRedirect(req.getContextPath());
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      System.out.println("JE BAISE TA MERE CONNARD");

      this.addPlace(req);
      System.out.println("JE BAISE TA MERE CONNASSE");
      resp.sendRedirect(req.getRequestURI());
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }
  }

  /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
