package server.servlets;

import server.database.SQLConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @author Ribeyrolles Matthieu
 * 29/12/2020, 23:16
 */
public class LoginServlet extends HttpServlet {
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  // public
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    SQLConnector connector = new SQLConnector();
    connector.connect("projet_master1_jee", "root", "");

    ResultSet resultSet = connector.doRequest("SELECT * FROM users");

    try {
      ResultSetMetaData metaData = resultSet.getMetaData();
      int colsNumber = metaData.getColumnCount();

      while(resultSet.next()) {
        for (int i = 1; i <= colsNumber; i++) {
          if (i > 1) System.out.println(", ");
          String colValue = resultSet.getString(i);
          System.out.printf("%s: %s\n", metaData.getColumnName(i), colValue);
        }
      }


    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }

    req.getRequestDispatcher("resources/views/login.jsp").forward(req, resp);
  }
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
