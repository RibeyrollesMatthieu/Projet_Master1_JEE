package server.database;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Ribeyrolles Matthieu
 * 03/01/2021, 15:12
 */
public class UserBean {
  private String firstname, lastname, email, password;
  private Date bdate;
  private ArrayList<Integer> friendsIds;
  private ArrayList<Integer> pendingIds;

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

  public ArrayList<Integer> getFriendsIds() { return friendsIds; }

  public ArrayList<Integer> getPendingIds() { return pendingIds; }

  // setters


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

  public void setFriendsIds(ArrayList<Integer> friendsIds) { this.friendsIds = friendsIds; }

  public void setPendingIds(ArrayList<Integer> pendingIds) { this.pendingIds = pendingIds; }

  public void addFriendId(int i) {
    this.friendsIds.add(i);
  }

  public void addPendingId(int i) { this.pendingIds.add(i); }

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
    this.friendsIds = new ArrayList<>();
    this.pendingIds = new ArrayList<>();
  }
}
