package server.servlets;

import server.database.SQLConnector;
import server.database.UserBean;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

/**
 * @author Ribeyrolles Matthieu
 * 03/01/2021, 23:44
 */
public interface ServletMethods {
  default void createUserBean(HttpServletRequest req) {
    try {
      final ResultSet rs = SQLConnector.getInstance().getUser((Integer) req.getSession().getAttribute("id"));
      UserBean userBean = new UserBean();

      rs.next();
      userBean.setEmail(rs.getString("email"));
      userBean.setFirstname(rs.getString("firstname"));
      userBean.setLastname(rs.getString("lastname"));
      userBean.setBdate(rs.getDate("birthdate"));
      userBean.setPassword(rs.getString("password"));

      req.getSession().setAttribute("user", userBean);
    } catch (Exception e) {
      System.err.println("Unable to create user bean");
    }
  }
}
