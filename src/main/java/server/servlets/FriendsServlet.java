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

      ResultSet set = SQLConnector.getInstance().getFriendsOf(Integer.parseInt(req.getSession().getAttribute("id").toString()), "F");
      ResultSet set2 = SQLConnector.getInstance().getFriendsOf(Integer.parseInt(req.getSession().getAttribute("id").toString()), "P");

      try {
        UserBean user = (UserBean) req.getSession().getAttribute("user");

        user.getFriends().clear();
        user.getPending().clear();

        //TODO: change this method to when we detect a changment or nah
        while(set.next()) {
          UserBean friend = new UserBean();
          ResultSet friendSet = SQLConnector.getInstance().getUser(set.getInt(1));
          friendSet.next();

          friend.setFirstname(friendSet.getString("firstname"));
          friend.setLastname(friendSet.getString("lastname"));
          friend.setEmail(friendSet.getString("email"));
          friend.setBdate(friendSet.getDate("birthdate"));
          friend.setCovided(friendSet.getBoolean("covided"));
          friend.setPassword("What a bad idea");

          user.addFriend(friend);
        }

        while (set2.next()) {
          UserBean friend = new UserBean();
          ResultSet friendSet = SQLConnector.getInstance().getUser(set2.getInt(1));
          friendSet.next();

          friend.setFirstname(friendSet.getString("firstname"));
          friend.setLastname(friendSet.getString("lastname"));
          friend.setEmail(friendSet.getString("email"));
          friend.setBdate(friendSet.getDate("birthdate"));
          friend.setCovided(friendSet.getBoolean("covided"));
          friend.setPassword("What a bad idea");

          user.addPending(friend);
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
