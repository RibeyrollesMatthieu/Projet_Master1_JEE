package server.servlets;

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
 * 05/01/2021, 21:53
 */
public class CovidedServlet extends HttpServlet {
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  // public

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.sendRedirect(req.getRequestURI());
  }

  private ResultSet getRandomCovidedPeople(HttpServletRequest req) throws SQLException {
    return SQLConnector.getInstance().doRequest(String.format(
      "SELECT owner FROM events e INNER JOIN (SELECT date, start, end, id_place FROM events WHERE owner = %d) AS data " +
      "ON e.date = data.date WHERE e.id_place = data.id_place AND e.start < data.end AND e.end > data.start AND e.owner <> %d",

      ((UserBean) req.getSession().getAttribute("user")).getId(), ((UserBean) req.getSession().getAttribute("user")).getId()
    ), false);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    assert req.getSession().getAttribute("id") != null: "Cannot go further, id is null";

    UserBean user = (UserBean) req.getSession().getAttribute("user");
    System.out.println(user.isCovided());

    if (! user.isCovided()) {
      try {
        SQLConnector.getInstance().doRequest(String.format(
          "UPDATE users SET covided = true WHERE id = %d",
          Integer.parseInt(req.getSession().getAttribute("id").toString())),
          true);

        user.setCovided(true);

        ResultSet set = this.getRandomCovidedPeople(req);
        NotifcationsSender.sendCovidedMessageToRandoms(set);
        NotifcationsSender.sendCovidedMessage((UserBean) req.getSession().getAttribute("user"));
      } catch (SQLException sqlException) {
        sqlException.printStackTrace();
      }
    }
  }

  /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
