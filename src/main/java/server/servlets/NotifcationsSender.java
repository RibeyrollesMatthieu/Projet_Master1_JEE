package server.servlets;

import server.database.SQLConnector;
import server.database.UserBean;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ribeyrolles Matthieu
 * 08/01/2021, 11:40
 */
public abstract class NotifcationsSender {
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  private static void insertNotif(String title, String content, int from, int to, String status) throws SQLException {
    SQLConnector.getInstance().doRequest(String.format(
      "INSERT INTO notifications(title, content, concernedUser, owner, status) " +
        "VALUES('%s', '%s', '%d', '%d', '%s')", title, content, from, to, status
    ), true);
  }

  public static void sendCovidedMessage(UserBean currentUser) throws SQLException {
    for (UserBean userBean : currentUser.getFriends()) {
      sendCovidedMessage(true, userBean.getId());
    }
  }

  public static void sendCovidedMessageToRandoms(ResultSet set) throws SQLException {
    while(set.next()) {
      sendCovidedMessage(false, set.getInt("owner"));
    }
  }


  private static void sendCovidedMessage(boolean isFromFriend, int id) throws SQLException {
    String title;
    String content;

    if (isFromFriend) {
      title = "A friend is covided!";
      content = "A friend of yours has declared being covided. We please you to stay at home and tell everyone you have been in contact these past days to take care.";
    } else {
      title = "Someone is covided!";
      content = "Someone you may have been in contact with has declared being covided. We please you to stay at home and tell everyone you have been in contact these past days to take care.";
    }

    insertNotif(title, content, id, 1, "covidedAlert");
  }

  // public
  public static void sendDeletedFriend(int from, int to) throws SQLException {
    ResultSet fromNameSet = SQLConnector.getInstance().doRequest("SELECT firstname, lastname FROM users WHERE id = " + from, false);
    fromNameSet.next();

    String title = String.format("%s %s deleted you from his/her friendlist :(", fromNameSet.getString("firstname"), fromNameSet.getString("lastname").toUpperCase());
    String content = String.format(
      "%s deleted you from his/her friendlist. You are no longer friends, and will not see each other recent events.",
      fromNameSet.getString("firstname")
    );

    insertNotif(title, content, to, from, "");

    ResultSet toNameSet = SQLConnector.getInstance().doRequest("SELECT firstname, lastname FROM users WHERE id = " + to, false);
    toNameSet.next();

    title = String.format("You deleted %s from your friendlist.", toNameSet.getString("firstname"));
    content = String.format(
      "You deleted from %s %s from your friendlist. You are no longer friends, and will not see each other recent events.",
      toNameSet.getString("firstname"), toNameSet.getString("lastname")
    );

    insertNotif(title, content, from ,to, "");
  }

  public static void sendAcceptedFriendRequestNotification(int from, int to) throws SQLException {
    ResultSet fromNameSet = SQLConnector.getInstance().doRequest("SELECT firstname, lastname FROM users WHERE id = " + from, false);
    fromNameSet.next();

    String title = String.format("%s %s accepted your friend request!", fromNameSet.getString("firstname"), fromNameSet.getString("lastname").toUpperCase());
    String content = String.format(
      "%s accepted your friend request. You are now friends, and are able to see each other recent events.",
      fromNameSet.getString("firstname")
    );

    insertNotif(title, content, to, from, "");

    ResultSet toNameSet = SQLConnector.getInstance().doRequest("SELECT firstname, lastname FROM users WHERE id = " + to, false);
    toNameSet.next();

    title = "You accepted the friend request.";
    content = String.format(
      "You accepted the friend request from %s %s. You are now friends, and are able to see each other recent events.",
      toNameSet.getString("firstname"), toNameSet.getString("lastname")
    );

    insertNotif(title, content, from ,to, "");
  }

  public static void sendDeclinedFriendRequestNotification(int from, int to) throws SQLException {
    ResultSet fromNameSet = SQLConnector.getInstance().doRequest("SELECT firstname, lastname FROM users WHERE id = " + from, false);
    fromNameSet.next();

    String title = String.format("%s %s declined your friend request!", fromNameSet.getString("firstname"), fromNameSet.getString("lastname").toUpperCase());
    String content = String.format(
      "%s declined your friend request. You can still send another friend request if you want to.",
      fromNameSet.getString("firstname")
    );

    insertNotif(title, content, to, from, "");

    ResultSet toNameSet = SQLConnector.getInstance().doRequest("SELECT firstname, lastname FROM users WHERE id = " + to, false);
    toNameSet.next();

    title = "You declined the friend request.";
    content = String.format(
      "You declined the friend request from %s %s. Note that this person can still send you another one.",
      toNameSet.getString("firstname"), toNameSet.getString("lastname")
    );

    insertNotif(title, content, from ,to, "");
  }

  public static void sendFriendRequestNotification(int from, int to) throws SQLException {
    ResultSet fromNameSet = SQLConnector.getInstance().doRequest("SELECT firstname, lastname FROM users WHERE id = " + from, false);
    fromNameSet.next();

    String title = String.format("%s %s sent you a friend request!", fromNameSet.getString("firstname"), fromNameSet.getString("lastname").toUpperCase());
    String content = String.format(
      "%s sent you a friend request. You now have to decide if you re gonna accept it or decline it. It s up to you!",
      fromNameSet.getString("firstname")
    );

    insertNotif(title, content, to, from, "acceptableRequest");

    ResultSet toNameSet = SQLConnector.getInstance().doRequest("SELECT firstname, lastname FROM users WHERE id = " + to, false);
    toNameSet.next();

    title = "A friend request has been sent.";
    content = String.format(
      "You sent a friend request to %s %s. You can still cancel it, or just wait for an answer :)",
      toNameSet.getString("firstname"), toNameSet.getString("lastname")
    );

    insertNotif(title, content, from, to, "sentRequest");
  }

   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
