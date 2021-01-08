package server.servlets;

import server.database.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.security.spec.PKCS8EncodedKeySpec;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Ribeyrolles Matthieu
 * 03/01/2021, 14:19
 */
public class ProfileServlet extends HttpServlet implements FormsMethods, ServletMethods {
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  private void loadEventsFor(UserBean userBean, int id) throws SQLException {
    ResultSet set = SQLConnector.getInstance().doRequest(String.format("SELECT * FROM events WHERE owner = '%d'", id), false);

    while(set.next()) {
      EventBean event = new EventBean();
      event.setId(set.getInt("id"));
      event.setTitle(set.getString("title"));
      event.setDate(set.getDate("date"));
      event.setStart(set.getTime("start"));
      event.setEnd(set.getTime("end"));
      event.setIdPlace(set.getInt("id_place"));
      event.setContent(set.getString("content"));
      event.setImage(set.getString("image"));

      UserBean owner = new UserBean();
      ResultSet ownerSet = SQLConnector.getInstance().getUser(set.getInt("owner"));
      ownerSet.next();
      owner.setFirstname(ownerSet.getString("firstname"));
      owner.setLastname(ownerSet.getString("lastname"));
      owner.setProfilePic(ownerSet.getString("profilePic"));

      event.setOwner(owner);
      userBean.addEvent(event);
    }
  }
  // public

  private void loadPlaces(HttpServletRequest req) throws SQLException {
    ResultSet set = SQLConnector.getInstance().doRequest("SELECT * from places", false);
    HashSet<PlaceBean> places = new HashSet<>();

    while(set.next()) {
      PlaceBean placeBean = new PlaceBean();

      placeBean.setId(set.getInt("id"));
      placeBean.setName(set.getString("name"));
      placeBean.setAddress(set.getString("address"));

      places.add(placeBean);
    }

    req.getSession().setAttribute("places", places);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    final Object loggedAttribute = req.getSession().getAttribute("logged");

    if (loggedAttribute != null && Boolean.parseBoolean(loggedAttribute.toString())) {
      try {
        this.loadNotifications((UserBean) req.getSession().getAttribute("user"));
        this.loadEventsFor((UserBean) req.getSession().getAttribute("user"), ((UserBean) req.getSession().getAttribute("user")).getId());
        this.loadPlaces(req);
      } catch (SQLException sqlException) {
        sqlException.printStackTrace();
      }

      req.getRequestDispatcher("resources/views/pages/profile.jsp").forward(req, resp);
    }
    else resp.sendRedirect(req.getContextPath());
  }

  private void update(String table, String field, String value, int id) throws SQLException {
    SQLConnector.getInstance().doRequest(String.format(
      "UPDATE %s SET %s='%s' WHERE id=%s;",
      table, field, value, id), true);
  }

  private void updateProfile(HttpServletRequest req, Map<String, String[]> params) throws SQLException {
    final int ID = Integer.parseInt(req.getSession().getAttribute("id").toString());
    final UserBean user = (UserBean) req.getSession().getAttribute("user");

    try {
      if (req.getParameter("firstname").trim().length() != 0 && !req.getParameter("firstname").trim().equals(user.getFirstname())){
        user.setFirstname(req.getParameter("firstname").trim());
        update("users", "firstname", req.getParameter("firstname"), ID);
      }

      if (req.getParameter("lastname").trim().length() != 0 && ! req.getParameter("lastname").trim().equals(user.getLastname())){
        user.setLastname(req.getParameter("lastname").trim());
        update("users", "lastname", req.getParameter("lastname"), ID);
      }

      if (req.getParameter("password").trim().length() != 0 && ! Hashing.check(req.getParameter("password"), user.getPassword())){
        user.setPassword(Hashing.getSaltedHash(req.getParameter("password")));
        update("users", "password", Hashing.getSaltedHash(req.getParameter("password")), ID);
      }

      if (req.getParameter("email").trim().length() != 0 && ! req.getParameter("email").trim().equals(user.getEmail())){
        user.setEmail(req.getParameter("email").trim());
        update("users", "email", req.getParameter("email"), ID);
      }

      if (req.getParameter("date").trim().length() != 0 && ! req.getParameter("date").trim().equals(user.getBdate())){
        update("users", "birthdate", req.getParameter("date").trim(), ID);

        ResultSet rs = SQLConnector.getInstance().doRequest("SELECT birthdate FROm users WHERE id = " + user.getId(), false);
        rs.next();
        user.setBdate(rs.getDate("birthdate"));
      }
    } catch (Exception sqlException) {
      sqlException.printStackTrace();
    }
  }

  private void addEvent(HttpServletRequest req) throws ParseException, SQLException {
    SQLConnector.getInstance().doRequest(String.format(
      "INSERT INTO events(title, date, start, end, id_place, content, owner, image) " +
      "VALUES('%s', '%s', '%s', '%s', '%d', '%s', '%d', '%s');",
      req.getParameter("title"),
      req.getParameter("date-event"),
      req.getParameter("start"),
      req.getParameter("end"),
      Integer.parseInt(req.getParameter("place")),
      req.getParameter("content"),
      ((UserBean) req.getSession().getAttribute("user")).getId(),
      String.format("https://picsum.photos/id/%d/200/300", new Random().nextInt(1084))
    ), true);
  }



  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    if (req.getParameter("add-event") != null) {
      try {
        this.addEvent(req);
      } catch (ParseException | SQLException e) {
        e.printStackTrace();
      }
      resp.sendRedirect(req.getRequestURI());
    } else {
      try {
        this.updateProfile(req, req.getParameterMap());
        resp.sendRedirect(req.getRequestURI());
      } catch (Exception e /*| SQLException sqlException*/) {
        System.err.println("Unable to update account");
        resp.sendRedirect(req.getRequestURI());
      }
    }
  }

  /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
