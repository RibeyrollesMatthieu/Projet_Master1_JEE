package server.servlets;

import server.database.SQLConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Ribeyrolles Matthieu
 * 04/01/2021, 19:43
 */
public class RelationServlet extends HttpServlet {
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  //TODO ajax this request (from the caller i guess)
  private void sendFriendRequest(int from, int to) {
    //TODO chekc if both users exist
    try {
      SQLConnector.getInstance().connect("projet_master1_jee", "root", "");
      SQLConnector.getInstance().doRequest(String.format(
        "INSERT into friendship(_from, _to, status) VALUES(%d, %d, 'P');", from, to
        ), true);

      SQLConnector.getInstance().doRequest(String.format(
        "INSERT into friendship(_from, _to, status) VALUES(%d, %d, 'P');", to, from
      ), true);
    } catch (SQLException sqlException) {
      System.err.println("An error has occurred while sending the friend request.");
//      sqlException.printStackTrace();
    }
  }
  // public

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    assert  req.getParameter("from") != null &&
            req.getParameter("to") != null &&
            req.getParameter("status") != null: "cannot add a friend cause missing information";

    if (req.getParameter("status").toUpperCase().equals("P"))
      this.sendFriendRequest(Integer.parseInt(req.getParameter("from")), Integer.parseInt(req.getParameter("to")));

    resp.sendRedirect(req.getContextPath() + "/friends");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.sendRedirect(req.getContextPath());
  }

   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
