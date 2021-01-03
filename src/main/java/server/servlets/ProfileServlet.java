package server.servlets;

import server.database.SQLConnector;
import server.database.UserBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ribeyrolles Matthieu
 * 03/01/2021, 14:19
 */
public class ProfileServlet extends HttpServlet {
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  private ResultSet getUser(int id) throws SQLException {
    SQLConnector connector = new SQLConnector();
    connector.connect("projet_master1_jee", "root", "");

    ResultSet set = connector.doRequest(
      String.format("SELECT * FROM users WHERE id=%d;", id), false
    );

    return set;
  }
  // public

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (req.getSession().getAttribute("id") == null) resp.sendRedirect(req.getContextPath());
    else {
      try {
        final ResultSet set = getUser((Integer) req.getSession().getAttribute("id"));
        UserBean userBean = new UserBean();

        set.next();
        userBean.setEmail(set.getString("email"));
        userBean.setFirstname(set.getString("firstname"));
        userBean.setLastname(set.getString("lastname"));
        userBean.setBdate(set.getDate("birthdate"));

        req.setAttribute("user", userBean);
        req.getRequestDispatcher("resources/views/pages/profile.jsp").forward(req, resp);
      } catch (Exception e) {
        System.err.println("Unable to read user profile page.");
      }

    }
  }

   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
