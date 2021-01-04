package server.servlets;

import server.database.SQLConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.regex.Pattern;

/**
 * @author Ribeyrolles Matthieu
 * 03/01/2021, 16:59
 */
public interface FormsMethods {

  default boolean isFieldTooLargeForDataBase(String field, String value) {
    SQLConnector connector = new SQLConnector();
    connector.connect("projet_master1_jee", "root", "");

    try {
      ResultSet set = connector.doRequest(String.format(
        "SELECT COLUMN_NAME, CHARACTER_MAXIMUM_LENGTH " +
        "FROM information_schema.columns " +
        "WHERE table_schema=DATABASE() AND " +
        "table_name='users' AND " +
        "COLUMN_NAME='%s';"
        , field), false);
      set.next();
      final int maxValue = set.getInt(2);

      if (value.length() > maxValue) return false;

    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }

    return true;
  }

  default boolean isFormCorrectlyWritten(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Enumeration<String> params = req.getParameterNames();
    while (params.hasMoreElements()) {
      String param = params.nextElement();
      if (req.getParameter(param).trim().length() == 0 || req.getParameter(param) == null) {
        System.err.printf("Param %s might be null or empty.%n", param);
        return false;
      }

      if (! isFieldTooLargeForDataBase(param, req.getParameter(param))) {
        System.err.printf("Param %s exceed the allowed size%n", param);
        return false;
      }
    }

    if (req.getParameterMap().containsKey("confirm-password")) {
      if (! req.getParameter("password").equals(req.getParameter("confirm-password"))) {
        System.err.println("Passwords ain't the same.");
        return false;
      }
    }

    if (! isEmailGood(req.getParameter("email"))) {
      System.err.println("Email is not correct.");
      return false;
    }

    return true;
  }

  default boolean isEmailGood(String email) {
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
      "[a-zA-Z0-9_+&*-]+)*@" +
      "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
      "A-Z]{2,7}$";

    Pattern pat = Pattern.compile(emailRegex);
    if (email == null) return false;
    return pat.matcher(email).matches();
  }
}
