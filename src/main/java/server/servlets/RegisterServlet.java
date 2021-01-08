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
 * 29/12/2020, 23:16
 */
public class RegisterServlet extends HttpServlet implements FormsMethods, ServletMethods {

  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private

  private int createAccount(String email, String firstname, String lastname, String password, String bdate) throws Exception {
    SQLConnector.getInstance().doRequest(
      String.format("INSERT INTO users(%s, %s, %s, %s, %s) VALUES('%s','%s','%s','%s','%s');",
        "email", "firstname", "lastname", "password", "birthdate",
        email, firstname, lastname, Hashing.getSaltedHash(password), bdate), true);

    System.out.printf("%sSuccessfully created new user%s\n", "\u001B[32m", "\u001B[0m");

    ResultSet set = SQLConnector.getInstance().doRequest(
      String.format("SELECT id FROM users WHERE email='%s';", email), false
    );

    set.next();

    return set.getInt(1);
  }

  // public

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    final Object loggedAttribute = req.getSession().getAttribute("logged");

//    Scripts.createUsers(100);

    if (loggedAttribute != null && Boolean.parseBoolean(loggedAttribute.toString())) resp.sendRedirect(req.getContextPath());
    else req.getRequestDispatcher("resources/views/connection/register.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (this.isFormCorrectlyWritten(req)) {
      try {
        int id = createAccount(
          req.getParameter("email"),
          req.getParameter("firstname"),
          req.getParameter("lastname"),
          req.getParameter("password"),
          req.getParameter("date")
        );
        req.getSession().setAttribute("id", id);
        req.getSession().setAttribute("logged", true);

        this.createUserBean(req);
        this.loadNotifications((UserBean) req.getSession().getAttribute("user"), Integer.parseInt(req.getSession().getAttribute("id").toString()));

        resp.sendRedirect(req.getContextPath());
      } catch (SQLException sqlException) {
        System.err.println("Unable to create account");
        resp.sendRedirect(req.getRequestURI());
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      req.getRequestDispatcher("resources/views/connection/register.jsp").forward(req, resp);
    }
  }

  /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
