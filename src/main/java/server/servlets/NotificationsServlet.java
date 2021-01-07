package server.servlets;

import server.database.NotificationBean;
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
 * 07/01/2021, 22:22
 */
public class NotificationsServlet extends HttpServlet {
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private

  private void loadNotifications(UserBean currentUSer, int id) throws SQLException {
    ResultSet set = SQLConnector.getInstance().doRequest(String.format(
      "SELECT * from notifications WHERE owner = %d " +
      "OR concernedUser = %d;", id, id), false);

    while(set.next()) {
      NotificationBean notificationBean = new NotificationBean();

      notificationBean.setTitle(set.getString("title"));
      notificationBean.setContent(set.getString("content"));
      notificationBean.setId(set.getInt("id"));

      ResultSet tempsSet = SQLConnector.getInstance().getUser(set.getInt("concernedUser"));
      tempsSet.next();
      UserBean userBean = new UserBean();
      userBean.setFirstname(tempsSet.getString("firstname"));
      userBean.setLastname(tempsSet.getString("lastname"));
      userBean.setCovided(tempsSet.getBoolean("covided"));
      userBean.setId(tempsSet.getInt("id"));

      notificationBean.setConcernedUser(userBean);

      currentUSer.addNotification(notificationBean);
    }
  }

  private void eraseNotif(UserBean currentUser, int id) {
    try {
      SQLConnector.getInstance().doRequest("DELETE FROM notifications WHERE id = " + id, true);

      currentUser.removeNotification(id);
    } catch (Exception e) {
      System.err.println("An error has occurred while trying to erase a notification.");
    }
  }
  // public

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    final Object loggedAttribute = req.getSession().getAttribute("logged");

    if (loggedAttribute != null && Boolean.parseBoolean(loggedAttribute.toString())) {

      assert req.getSession().getAttribute("id") != null : "Cannot look at friends because id is null";

      try {
        if (req.getParameter("erase") != null) {
          this.eraseNotif((UserBean) req.getSession().getAttribute("user"), Integer.parseInt(req.getParameter("erase")));
        } else {
          this.loadNotifications(
            (UserBean) req.getSession().getAttribute("user"),
            Integer.parseInt(req.getSession().getAttribute("id").toString())
          );

          req.getRequestDispatcher("resources/views/pages/notifications.jsp").forward(req, resp);
        }
      } catch (SQLException e) {
        System.err.println("an error has occurew when loading the notifications");
      }
    }

    else resp.sendRedirect(req.getContextPath());
}
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
