package server.servlets;

import server.database.NotificationBean;
import server.database.SQLConnector;
import server.database.UserBean;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ribeyrolles Matthieu
 * 03/01/2021, 23:44
 */
public interface ServletMethods {
  default void loadNotifications(UserBean currentUSer, int id) throws SQLException {
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

  default void createUserBean(HttpServletRequest req) {
    try {
      final ResultSet rs = SQLConnector.getInstance().getUser((Integer) req.getSession().getAttribute("id"));
      UserBean userBean = new UserBean();

      rs.next();
      userBean.setEmail(rs.getString("email"));
      userBean.setFirstname(rs.getString("firstname"));
      userBean.setLastname(rs.getString("lastname"));
      userBean.setBdate(rs.getDate("birthdate"));
      userBean.setCovided(rs.getBoolean("covided"));
      if (Integer.parseInt(req.getSession().getAttribute("id").toString()) == rs.getInt("id"))
        userBean.setPassword(rs.getString("password"));

      req.getSession().setAttribute("user", userBean);
    } catch (Exception e) {
      System.err.println("Unable to create user bean");
    }
  }
}
