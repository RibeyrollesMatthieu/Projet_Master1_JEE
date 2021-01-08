package server.database;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

/**
 * @author Ribeyrolles Matthieu
 * 08/01/2021, 15:48
 */
public class EventBean {
  private String title, content, image;
  private Date date;
  private Time start, end;
  private int id, idPlace;
  private UserBean owner;

  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters

  public void setTitle(String title) {
    this.title = title;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public void setDate(Date date) {
    this.date = date;
  }
  public void setStart(Time start) {
    this.start = start;
  }
  public void setEnd(Time end) {
    this.end = end;
  }
  public void setId(int id) {
    this.id = id;
  }
  public void setIdPlace(int idPlace) {
    this.idPlace = idPlace;
  }
  public void setImage(String image) {
    this.image = image;
  }
  public void setOwner(UserBean owner) {
    this.owner = owner;
  }

  public String getTitle() {
    return title;
  }
  public String getContent() {
    return content;
  }
  public Date getDate() {
    return date;
  }
  public Date getStart() {
    return start;
  }
  public Date getEnd() {
    return end;
  }
  public int getId() {
    return id;
  }
  public int getIdPlace() {
    return idPlace;
  }
  public String getImage() {
    return image;
  }
  public UserBean getOwner() {
    return owner;
  }

  // private
  // public

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EventBean eventBean = (EventBean) o;
    return this.id == eventBean.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "EventBean{" +
      "title='" + title + '\'' +
      ", content='" + content + '\'' +
      ", image='" + image + '\'' +
      ", date=" + date +
      ", start=" + start +
      ", end=" + end +
      ", id=" + id +
      ", idPlace=" + idPlace +
      ", owner=" + owner +
      '}';
  }

  /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
