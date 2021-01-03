package server.database;

import java.util.Date;

/**
 * @author Ribeyrolles Matthieu
 * 03/01/2021, 15:12
 */
public class UserBean {
  private String firstname, lastname, email, password;
  private Date bdate;
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
  }
}
