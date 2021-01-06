package server.database;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;

/**
 * @author Ribeyrolles Matthieu
 * 03/01/2021, 15:12
 */
public class UserBean {
  private String firstname, lastname, email, password;
  private Date bdate;
  private int id;
  private boolean isCovided;
  private HashSet<UserBean> friends;
  private HashSet<PendingBean> pending;

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

  public int getId() {
    return id;
  }

  public Date getBdate() {
    return bdate;
  }

  public boolean isCovided() { return isCovided; }

  public HashSet<UserBean> getFriends() { return friends; }

  public HashSet<PendingBean> getPending() { return pending; }
  // settersHashSet


  public void setId(int id) {
    this.id = id;
  }

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
  public void setCovided(boolean covided) { isCovided = covided; }
  public void addFriend(UserBean userBean) { this.friends.add(userBean); }
  public void addPending(PendingBean pendingBean) { this.pending.add(pendingBean); }

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

  @Override
  public int hashCode() {
    return Objects.hash(email);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserBean)) return false;

    System.out.println(this.email);
    System.out.println(((UserBean) o).getEmail());

    return this.email.equals(((UserBean) o).getEmail());
  }

  /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public UserBean() {
    this.friends = new HashSet<>();
    this.pending = new HashSet<>();
  }
}
