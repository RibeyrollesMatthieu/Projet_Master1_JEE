package server.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

/**
 * @author Ribeyrolles Matthieu
 * 03/01/2021, 15:12
 */
public class UserBean {
  private String firstname, lastname, email, password;
  private Date bdate;
  private boolean isCovided;
  private HashSet<UserBean> friends;
  private HashSet<Integer> friendsIds;
  private HashSet<Integer> pendingIds;

  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public Date getBdate() {
    return bdate;
  }

  public boolean isCovided() { return isCovided; }

  public HashSet<Integer> getFriendsIds() { return friendsIds; }
  public HashSet<UserBean> getFriends() { return friends; }
  public HashSet<Integer> getPendingIds() { return pendingIds; }

  // settersHashSet

  public void setBdate(Date bdate) {
    this.bdate = bdate;
  }
  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }
  public void setLastname(String lastname) {
    this.lastname = lastname;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public void addFriendId(int i) {
    this.friendsIds.add(i);
  }
  public void setCovided(boolean covided) { isCovided = covided; }
  public void addPendingId(int i) { this.pendingIds.add(i); }
  public void addFriend(UserBean userBean) { this.friends.add(userBean); }

  // private
  // public

  @Override
  public String toString() {
    return "UserBean{" +
      "firstname='" + firstname + '\'' +
      ", lastname='" + lastname + '\'' +
      ", email='" + email + '\'' +
      ", password='" + password + '\'' +
      ", bdate=" + bdate +
      '}';
  }


   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public UserBean() {
    this.friendsIds = new HashSet<>();
    this.pendingIds = new HashSet<>();
    this.friends = new HashSet<>();
  }
}
