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
  //TODO:ajax this request (from the caller i guess)

  //TODO: prevent to send to ourself a request
  private void sendFriendRequest(int id1, int id2) {
    //TODO check if both users exist? or exception is enough
    try {
      SQLConnector.getInstance().doRequest(String.format("INSERT into friendship(_from, _to, status) VALUES(%d, %d, 'P');", id1, id2), true);
      SQLConnector.getInstance().doRequest(String.format("INSERT into friendship(_from, _to, status) VALUES(%d, %d, 'P');", id2, id1), true);
    } catch (SQLException sqlException) {
      System.err.println("An error has occurred while sending the friend request.");
//      sqlException.printStackTrace();
    }
  }

  private void declineRequest(int id1, int id2) {
    try {
      SQLConnector.getInstance().doRequest(String.format("DELETE FROM friendship WHERE _from = %d AND _to = %d AND status = 'P'", id2, id1), true);
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
//      System.err.println("An error has occured while trying to cancel the friend request you sent.");
    }
  }

  private void cancelRequest(int id1, int id2) {
    try {
      SQLConnector.getInstance().doRequest(String.format("DELETE FROM friendship WHERE _from = %d AND _to = %d AND status = 'P'", id1, id2), true);
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
//      System.err.println("An error has occured while trying to cancel the friend request you sent.");
    }
  }

  private void acceptFriendRequest(int id1, int id2) {
    try {
      SQLConnector.getInstance().doRequest(String.format(
        "UPDATE friendship " +
        "SET status = 'F' " +
        "WHERE _from = %d AND _to = %d", id2, id1), true);

      SQLConnector.getInstance().doRequest(String.format("INSERT into friendship(_from, _to, status) VALUES(%d, %d, 'F');", id1, id2), true);
    } catch (SQLException sqlException) {
      System.err.println("An error has occured while trying to accept the friend request");
    }
  }

  private void removeFriend(int id1, int id2) {
    try {
      SQLConnector.getInstance().doRequest(String.format("DELETE FROM friendship WHERE _from = %d AND _to = %d", id1, id2), true);
      SQLConnector.getInstance().doRequest(String.format("DELETE FROM friendship WHERE _from = %d AND _to = %d", id2, id1), true);
    } catch (SQLException sqlException) {
      System.err.println("An error has occured while trying to remove a friend.");
    }
  }
  // public

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (req.getParameter("add") != null) {
      this.sendFriendRequest(Integer.parseInt(req.getSession().getAttribute("id").toString()), Integer.parseInt(req.getParameter("add")));
    }

    if (req.getParameter("delete") != null) {
      this.removeFriend(Integer.parseInt(req.getSession().getAttribute("id").toString()), Integer.parseInt(req.getParameter("delete")));
    }

    if (req.getParameter("accept") != null) {
      this.acceptFriendRequest(Integer.parseInt(req.getSession().getAttribute("id").toString()), Integer.parseInt(req.getParameter("accept")));
    }

    if (req.getParameter("cancel") != null) {
      this.cancelRequest(Integer.parseInt(req.getSession().getAttribute("id").toString()), Integer.parseInt(req.getParameter("cancel")));
    }

    if (req.getParameter("decline") != null) {
      this.declineRequest(Integer.parseInt(req.getSession().getAttribute("id").toString()), Integer.parseInt(req.getParameter("decline")));
    }

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
