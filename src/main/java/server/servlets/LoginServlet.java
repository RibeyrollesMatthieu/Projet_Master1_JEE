package server.servlets;

import server.database.Hashing;
import server.database.SQLConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author Ribeyrolles Matthieu
 * 03/01/2021, 21:14
 */
public class LoginServlet extends HttpServlet implements FormsMethods, ServletMethods {
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  private ResultSet connect(HttpServletRequest req, String email) throws SQLException {
    assert email != null: "Email cannot be null";

    ResultSet set = SQLConnector.getInstance().doRequest(String.format("SELECT password, id FROM users WHERE email='%s';", email), false);

    System.out.printf("%sfound user in database%s\n", "\u001B[32m", "\u001B[0m");

    set.next();

    return set;
  }
  // public

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    final Object loggedAttribute = req.getSession().getAttribute("logged");

    if (loggedAttribute != null && Boolean.parseBoolean(loggedAttribute.toString())) resp.sendRedirect(req.getContextPath());
    else {
      HashMap<String, Integer> usersColumnsLength = new HashMap<>();

      try {
        ResultSet set = SQLConnector.getInstance().getAllColumnsFor("users");

        while(set.next()) {
          for (int i = 1; i <= set.getMetaData().getColumnCount(); i++) {
            usersColumnsLength.put(set.getString(i), SQLConnector.getInstance().getAllowedSizeForColumnField("users", set.getString(i)));
          }
        }

        req.getSession().setAttribute("usersColumnsLength", usersColumnsLength);
      } catch (SQLException sqlException) {
        sqlException.printStackTrace();
      }

      req.getRequestDispatcher("resources/views/connection/login.jsp").forward(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (this.isFormCorrectlyWritten(req)) {
      try {
        ResultSet set = this.connect(req, req.getParameter("email"));

        if (Hashing.check(req.getParameter("password"), set.getString("password"))) {

          req.getSession().setAttribute("id", set.getInt("id"));
          req.getSession().setAttribute("logged", true);
          this.createUserBean(req);

          resp.sendRedirect(req.getContextPath());
        } else {
          System.err.println("Password incorrect");
          resp.sendRedirect(req.getRequestURI());
        }
      } catch (Exception e) {
        e.printStackTrace();
        resp.sendRedirect(req.getRequestURI());
      }
    } else {
      req.getRequestDispatcher("resources/views/connection/register.jsp").forward(req, resp);
    }
  }

  /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
