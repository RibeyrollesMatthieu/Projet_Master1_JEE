package server.database;

import java.util.Date;

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

  // private
  // public
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
