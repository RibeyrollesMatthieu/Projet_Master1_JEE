package server.servlets;

import server.database.DbConnector;
import server.database.SQLConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ribeyrolles Matthieu
 * 04/01/2021, 19:23
 */
public class FriendsServlet extends HttpServlet {
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

    if (loggedAttribute != null && Boolean.parseBoolean(loggedAttribute.toString())) {

      assert req.getSession().getAttribute("id") != null : "Cannot look at friends because id is null";

      ResultSet set = SQLConnector.getInstance().getFriendsOf(Integer.parseInt(req.getSession().getAttribute("id").toString()));

      System.out.printf("Ceci est le set: %s\n", set);
      try {
        System.out.println("on rentre dans le try");
        while(set.next()) {
          System.out.println("on rentre dans le while");
          for (int i = 1; i <= set.getMetaData().getColumnCount(); i++) {
            System.out.println(set.getString(i));
          }
        }
      } catch (SQLException sqlException) {
        sqlException.printStackTrace();
      }

      req.getRequestDispatcher("resources/views/pages/friends.jsp").forward(req, resp);
    }
    else resp.sendRedirect(req.getContextPath());
  }

   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
