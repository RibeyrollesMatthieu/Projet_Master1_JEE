package server.database;

import java.util.HashSet;

/**
 * @author Ribeyrolles Matthieu
 * 07/01/2021, 17:07
 */
public class FriendsSearchBean {
  private HashSet<UserBean> searchResultList;
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters

  public HashSet<UserBean> getSearchResultList() {
    return searchResultList;
  }

  public void addUser(UserBean userBean) { this.searchResultList.add(userBean); }

  // setters
  // private
  // public
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public FriendsSearchBean() {
    this.searchResultList = new HashSet<>();
  }
}
