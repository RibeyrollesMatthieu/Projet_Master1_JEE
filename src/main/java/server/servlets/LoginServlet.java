package server.servlets;

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

    SQLConnector connector = new SQLConnector();
    connector.connect("projet_master1_jee", "root", "");

    ResultSet set = connector.doRequest(String.format("SELECT password, id FROM users WHERE email='%s'", email), false);

    System.out.print("found user in database\n");

    set.next();

    return set;
  }
  // public

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    final Object loggedAttribute = req.getSession().getAttribute("logged");

    if (loggedAttribute != null && Boolean.parseBoolean(loggedAttribute.toString())) resp.sendRedirect(req.getContextPath());
    else req.getRequestDispatcher("resources/views/connection/login.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (this.isFormCorrectlyWritten(req, resp)) {
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
