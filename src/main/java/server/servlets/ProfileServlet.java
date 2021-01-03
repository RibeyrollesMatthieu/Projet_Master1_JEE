package server.servlets;

import server.database.DbConnector;
import server.database.Hashing;
import server.database.SQLConnector;
import server.database.UserBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Stack;

/**
 * @author Ribeyrolles Matthieu
 * 03/01/2021, 14:19
 */
public class ProfileServlet extends HttpServlet implements FormsMethods {
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  // public

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    final Object loggedAttribute = req.getSession().getAttribute("logged");

    if (loggedAttribute != null && Boolean.parseBoolean(loggedAttribute.toString())) req.getRequestDispatcher("resources/views/pages/profile.jsp").forward(req, resp);
    else resp.sendRedirect(req.getContextPath());
  }

  private void update(DbConnector connector, String table, String field, String value, int id) throws SQLException {
    connector.doRequest(String.format(
      "UPDATE %s SET %s='%s' WHERE id=%s;",
      table, field, value, id), true);
  }

  private void updateProfile(HttpServletRequest req, Map<String, String[]> params) {
    final int ID = (int) req.getSession().getAttribute("id");
    final UserBean user = (UserBean) req.getSession().getAttribute("user");

    SQLConnector connector = new SQLConnector();
    connector.connect("projet_master1_jee", "root", "");

    try {
      if (! params.get("firstname")[0].equals(user.getFirstname()))
        update(connector , "users", "firstname", params.get("firstname")[0], ID);

      if (! params.get("lastname")[0].equals(user.getLastname()))
        update(connector , "users", "lastname", params.get("lastname")[0], ID);

      if (! Hashing.check(params.get("password")[0], user.getPassword()))
        update(connector , "users", "password", params.get("password")[0], ID);

      if (! params.get("email")[0].equals(user.getLastname()))
        update(connector , "users", "email", params.get("email")[0], ID);

      if (! params.get("date")[0].equals(user.getLastname()))
        update(connector , "users", "birthdate", params.get("date")[0], ID);
    } catch (Exception sqlException) {
      sqlException.printStackTrace();
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (this.isFormCorrectlyWritten(req, resp)) {
      try {
        this.updateProfile(req, req.getParameterMap());
        resp.sendRedirect(req.getRequestURI());
      } catch (Exception e /*| SQLException sqlException*/) {
        System.err.println("Unable to update account");
        resp.sendRedirect(req.getRequestURI());
      }
    } else {
      resp.sendRedirect(req.getRequestURI());
    }
  }

  /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
