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
import java.util.Enumeration;
import java.util.regex.Pattern;

/**
 * @author Ribeyrolles Matthieu
 * 29/12/2020, 23:16
 */
public class RegisterServlet extends HttpServlet {

  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  private boolean isEmailGood(String email) {
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
      "[a-zA-Z0-9_+&*-]+)*@" +
      "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
      "A-Z]{2,7}$";

    Pattern pat = Pattern.compile(emailRegex);
    if (email == null) return false;
    return pat.matcher(email).matches();
  }

  private boolean isRegisteringOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Enumeration<String> params = req.getParameterNames();
    while (params.hasMoreElements()) {
      String param = params.nextElement();
      if (req.getParameter(param).trim().length() == 0 || req.getParameter(param) == null) return false;
    }

    if (! req.getParameter("password").equals(req.getParameter("confirm-password"))) return false;
    if (! isEmailGood(req.getParameter("email"))) return false;

    return true;
  }

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

  // public

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    final Object loggedAttribute = req.getSession().getAttribute("logged");

    if (loggedAttribute != null && Boolean.parseBoolean(loggedAttribute.toString())) resp.sendRedirect(req.getRequestURI().replace("register", ""));
    else req.getRequestDispatcher("resources/views/connection/register.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (this.isRegisteringOk(req, resp)) {
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
