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
import java.text.SimpleDateFormat;
import java.util.Date;
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

  private void update(String table, String field, String value, int id) throws SQLException {
    SQLConnector.getInstance().doRequest(String.format(
      "UPDATE %s SET %s='%s' WHERE id=%s;",
      table, field, value, id), true);
  }

  private void updateProfile(HttpServletRequest req, Map<String, String[]> params) throws SQLException {
    final int ID = (int) req.getSession().getAttribute("id");
    final UserBean user = (UserBean) req.getSession().getAttribute("user");

    try {
      if (! params.get("firstname")[0].equals(user.getFirstname())){
        user.setFirstname(params.get("firstname")[0]);
        update("users", "firstname", params.get("firstname")[0], ID);
      }

      if (! params.get("lastname")[0].equals(user.getLastname())){
        user.setLastname(params.get("lastname")[0]);
        update("users", "lastname", params.get("lastname")[0], ID);
      }

      if (! Hashing.check(params.get("password")[0], user.getPassword())){
        user.setPassword(Hashing.getSaltedHash(params.get("password")[0]));
        update("users", "password", Hashing.getSaltedHash(params.get("password")[0]), ID);
      }

      if (! params.get("email")[0].equals(user.getEmail())){
        user.setEmail(params.get("email")[0]);
        update("users", "email", params.get("email")[0], ID);
      }

      if (! params.get("date")[0].equals(user.getBdate().toString())){
        String pattern = "yyyy-MM-dd";
        user.setBdate(new SimpleDateFormat(pattern).parse(params.get("date")[0])); //FIXME get only year month and day
        update("users", "birthdate", params.get("date")[0], ID);
      }
    } catch (Exception sqlException) {
      sqlException.printStackTrace();
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (this.isFormCorrectlyWritten(req)) {
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
