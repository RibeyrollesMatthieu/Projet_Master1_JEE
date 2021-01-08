package server.database;

import java.util.Objects;

/**
 * @author Ribeyrolles Matthieu
 * 08/01/2021, 20:02
 */
public class PlaceBean {
  private String name, address;
  private int id;

  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters

  public String getName() {
    return name;
  }
  public String getAddress() {
    return address;
  }
  public int getId() {
    return id;
  }

  // setters

  public void setName(String name) {
    this.name = name;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public void setId(int id) {
    this.id = id;
  }

  // private
  // public

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PlaceBean placeBean = (PlaceBean) o;
    return id == placeBean.id;
  }

  @Override
  public String toString() {
    return String.format("%s   ~~   %s", this.name.replace(',', ' '), this.address.replace(',', ' '));
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
