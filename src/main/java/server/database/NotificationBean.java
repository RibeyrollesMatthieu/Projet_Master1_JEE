package server.database;

import java.util.Objects;

/**
 * @author Ribeyrolles Matthieu
 * 07/01/2021, 22:18
 */
public class NotificationBean {
  private String title;
  private String content;
  private UserBean concernedUser;
  private int id;

  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  // public

  public String getContent() {
    return content;
  }
  public String getTitle() {
    return title;
  }
  public UserBean getConcernedUser() {
    return concernedUser;
  }
  public int getId() {
    return id;
  }

  public void setConcernedUser(UserBean concernedUser) {
    this.concernedUser = concernedUser;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public void setId(int id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NotificationBean that = (NotificationBean) o;

    return this.id == that.getId();
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
