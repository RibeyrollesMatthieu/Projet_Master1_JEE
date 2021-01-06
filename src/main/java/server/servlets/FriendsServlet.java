package server.servlets;

import server.database.PendingBean;
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

      ResultSet setOfFriends = SQLConnector.getInstance().getFriendsOf(Integer.parseInt(req.getSession().getAttribute("id").toString()));
      ResultSet setOfPendingsISent = SQLConnector.getInstance().getPendingInvitesISent(Integer.parseInt(req.getSession().getAttribute("id").toString()));
      ResultSet setOfPendingsIReceived = SQLConnector.getInstance().getPendingsWeSentMe(Integer.parseInt(req.getSession().getAttribute("id").toString()));

      try {
        UserBean user = (UserBean) req.getSession().getAttribute("user");

        user.getFriends().clear();
        user.getPending().clear();

        //TODO: change this method to when we detect a changment or nah
        while(setOfFriends.next()) {
          UserBean friend = new UserBean();
          ResultSet friendSet = SQLConnector.getInstance().getUser(setOfFriends.getInt(1));
          friendSet.next();

          friend.setId(friendSet.getInt("id"));
          friend.setFirstname(friendSet.getString("firstname"));
          friend.setLastname(friendSet.getString("lastname"));
          friend.setCovided(friendSet.getBoolean("covided"));
          friend.setEmail(friendSet.getString("email"));

          user.addFriend(friend);
        }

        while (setOfPendingsISent.next()) {
          PendingBean pending = new PendingBean();
          ResultSet pendingSet = SQLConnector.getInstance().getUser(setOfPendingsISent.getInt(1));
          pendingSet.next();

          pending.setId(pendingSet.getInt("id"));
          pending.setFirstname(pendingSet.getString("firstname"));
          pending.setLastname(pendingSet.getString("lastname"));
          pending.setEmail(pendingSet.getString("email"));
          pending.setRequestSentFromCurrentUser(true);

          user.addPending(pending);
        }

        while (setOfPendingsIReceived.next()) {
          PendingBean pending = new PendingBean();
          ResultSet pendingSet = SQLConnector.getInstance().getUser(setOfPendingsIReceived.getInt(1));
          pendingSet.next();

          pending.setId(pendingSet.getInt("id"));
          pending.setFirstname(pendingSet.getString("firstname"));
          pending.setLastname(pendingSet.getString("lastname"));
          pending.setEmail(pendingSet.getString("email"));
          pending.setRequestSentFromCurrentUser(false);

          user.addPending(pending);
        }
      } catch (SQLException sqlException) {
//        sqlException.printStackTrace();
      }

      req.getRequestDispatcher("resources/views/pages/friends.jsp").forward(req, resp);
    }
    else resp.sendRedirect(req.getContextPath());
  }

   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
