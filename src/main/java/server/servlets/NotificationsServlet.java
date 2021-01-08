package server.servlets;

import server.database.SQLConnector;
import server.database.UserBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Ribeyrolles Matthieu
 * 07/01/2021, 22:22
 */
public class NotificationsServlet extends HttpServlet implements ServletMethods {
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private

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
        this.loadNotifications((UserBean) req.getSession().getAttribute("user"));
      } catch (SQLException sqlException) {
        sqlException.printStackTrace();
      }
      if (req.getParameter("erase") != null) {
        this.eraseNotif((UserBean) req.getSession().getAttribute("user"), Integer.parseInt(req.getParameter("erase")));
      } else {
        req.getRequestDispatcher("resources/views/pages/notifications.jsp").forward(req, resp);
      }
    }

    else resp.sendRedirect(req.getContextPath());
}
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
