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
  default void loadNotifications(UserBean currentUSer) throws SQLException {
    ResultSet set = SQLConnector.getInstance().doRequest(String.format(
      "SELECT * from notifications WHERE concernedUser = %d;", currentUSer.getId()), false);

    while(set.next()) {
      NotificationBean notificationBean = new NotificationBean();

      notificationBean.setTitle(set.getString("title"));
      notificationBean.setContent(set.getString("content"));
      notificationBean.setId(set.getInt("id"));
      notificationBean.setStatus(set.getString("status"));
      notificationBean.setConcernedUser(currentUSer);

      ResultSet tempSet = SQLConnector.getInstance().getUser(set.getInt("owner"));
      tempSet.next();
      UserBean ownerBean = new UserBean();
      ownerBean.setFirstname(tempSet.getString("firstname"));
      ownerBean.setLastname(tempSet.getString("lastname"));
      ownerBean.setId(tempSet.getInt("id"));

      notificationBean.setOwnerUser(ownerBean);

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
      userBean.setId(Integer.parseInt(req.getSession().getAttribute("id").toString()));
      userBean.setProfilePic(rs.getString("profilePic"));

      if (Integer.parseInt(req.getSession().getAttribute("id").toString()) == rs.getInt("id"))
        userBean.setPassword(rs.getString("password"));

      req.getSession().setAttribute("user", userBean);
    } catch (Exception e) {
      System.err.println("Unable to create user bean");
    }
  }
}
