package server.database;

import java.util.Objects;

/**
 * @author Ribeyrolles Matthieu
 * 06/01/2021, 22:20
 */
public class PendingBean extends UserBean {
  private boolean requestSentFromCurrentUser;

  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters

  public boolean isRequestSentFromCurrentUser() {
    return requestSentFromCurrentUser;
  }

  // setters

  public void setRequestSentFromCurrentUser(boolean requestSentFromCurrentUser) {
    this.requestSentFromCurrentUser = requestSentFromCurrentUser;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (o instanceof PendingBean) {
      PendingBean that = (PendingBean) o;
      return that.getEmail().equals(this.getEmail());
    }

    if (o instanceof UserBean) {
      UserBean that = (UserBean) o;
      System.out.println("I am in the good place");
      return that.getEmail().equals(this.getEmail());
    }

    return false;
  }

  @Override
  public String toString() {
    return "PendingBean{" +
      "firstname='" + this.getFirstname() + '\'' +
      ", lastname='" + this.getLastname() + '\'' +
      ", email='" + this.getEmail() + '\'' +
      ", password='" + this.getPassword() + '\'' +
      ", bdate=" + this.getBdate() +
      '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode());
  }

  // private
  // public
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
