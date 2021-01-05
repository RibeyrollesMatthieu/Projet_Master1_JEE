package server.servlets;

import server.database.SQLConnector;
import server.database.UserBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Ribeyrolles Matthieu
 * 05/01/2021, 21:53
 */
public class CovidedServlet extends HttpServlet {
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  // public

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.sendRedirect(req.getRequestURI());
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    assert req.getSession().getAttribute("id") != null: "Cannot go further, id is null";

    UserBean user = (UserBean) req.getSession().getAttribute("user");
    if (! user.isCovided()) {
      System.out.println("coucoouuuu");
      try {
        SQLConnector.getInstance().doRequest(String.format(
          "UPDATE users SET covided = true WHERE id = %d",
          Integer.parseInt(req.getSession().getAttribute("id").toString())),
          true);

        user.setCovided(true);
      } catch (SQLException sqlException) {
        sqlException.printStackTrace();
      }
    }
  }

  /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
