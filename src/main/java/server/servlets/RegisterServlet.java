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
public class RegisterServlet extends HttpServlet implements FormsMethods {

  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private

  private int createAccount(String email, String firstname, String lastname, String password, String bdate) throws SQLException {
    SQLConnector connector = new SQLConnector();
    connector.connect("projet_master1_jee", "root", "");

    Hashing hashing = new Hashing();

    connector.doRequest(
      String.format("INSERT INTO users(%s, %s, %s, %s, %s) VALUES('%s','%s','%s','%s','%s');",
        "email", "firstname", "lastname", "password", "birthdate",
        email, firstname, lastname, hashing.hash(password), bdate), true);

    System.out.print("Successfully created new user\n");

    ResultSet set = connector.doRequest(
      String.format("SELECT id FROM users WHERE email='%s';", email), false
    );

    set.next();

    return set.getInt(1);
  }

  private void createUserBean(HttpServletRequest req) {
    SQLConnector connector = new SQLConnector();
    connector.connect("projet_master1_jee", "root", "");

    try {
      final ResultSet rs = connector.getUser((Integer) req.getSession().getAttribute("id"));
      UserBean userBean = new UserBean();

      rs.next();
      userBean.setEmail(rs.getString("email"));
      userBean.setFirstname(rs.getString("firstname"));
      userBean.setLastname(rs.getString("lastname"));
      userBean.setBdate(rs.getDate("birthdate"));
      userBean.setPassword(rs.getString("password"));

      req.getSession().setAttribute("user", userBean);
    } catch (Exception e) {
      System.err.println("Unable to create user bean");
    }
  }

  // public

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    final Object loggedAttribute = req.getSession().getAttribute("logged");

    if (loggedAttribute != null && Boolean.parseBoolean(loggedAttribute.toString())) resp.sendRedirect(req.getContextPath());
    else req.getRequestDispatcher("resources/views/connection/register.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (this.isFormCorrectlyWritten(req, resp)) {
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

        resp.sendRedirect(req.getContextPath());
      } catch (SQLException sqlException) {
        System.err.println("Unable to create account");
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