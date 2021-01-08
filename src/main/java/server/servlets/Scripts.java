package server.servlets;

import com.github.javafaker.Faker;
import server.database.Hashing;
import server.database.SQLConnector;

import java.time.LocalDate;
import java.util.Random;

/**
 * @author Ribeyrolles Matthieu
 * 05/01/2021, 16:35
 */
public abstract class Scripts {
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private

  private static String generatePassword(int n) {
    String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!&#@*+-/|";
    StringBuilder salt = new StringBuilder();
    Random rnd = new Random();
    while (salt.length() < n) { // length of the random string.
      int index = (int) (rnd.nextFloat() * SALTCHARS.length());
      salt.append(SALTCHARS.charAt(index));
    }
    String saltStr = salt.toString();
    return saltStr;

  }

  private static int createRandomIntBetween(int start, int end) {
    return start + (int) Math.round(Math.random() * (end - start));
  }

  private static LocalDate createRandomDate(int startYear, int endYear) {
    int day = createRandomIntBetween(1, 28);
    int month = createRandomIntBetween(1, 12);
    int year = createRandomIntBetween(startYear, endYear);
    return LocalDate.of(year, month, day);
  }

  // public
  public static void createUsers(int n) {
    Faker faker = new Faker();
    for (int i = 0; i < n; i++) {
      String firstname = faker.name().firstName();
      String lastname = faker.name().lastName();

      String email = String.format("%s.%s@domain.com", firstname, lastname);

      try {
        SQLConnector.getInstance().doRequest(String.format(
          "INSERT INTO users(%s, %s, %s, %s, %s, %s) " +
          "VALUES('%s','%s','%s','%s','%s', '%s');",
          "email", "firstname", "lastname", "password", "birthdate", "profilePic",
          email, firstname, lastname, Hashing.getSaltedHash(generatePassword(50)), createRandomDate(1900, 2019),
          String.format("https://picsum.photos/id/%d/200/300", new Random().nextInt(1084))), true);
      } catch (Exception e) {
//        e.printStackTrace();
      }
    }
  }
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
