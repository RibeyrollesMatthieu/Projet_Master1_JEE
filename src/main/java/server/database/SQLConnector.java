package server.database;

import java.sql.*;

/**
 * @author Ribeyrolles Matthieu
 * 29/12/2020, 22:37
 */
public class SQLConnector extends DbConnector {
  private final String RESET = "\033[0m";
  private final String BLUE_COLOR = "\033[0;34m";

  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  // public

  //TODO set in his parent too
  public ResultSet getUser(int id) throws SQLException {
    SQLConnector connector = new SQLConnector();
    connector.connect("projet_master1_jee", "root", "");

    ResultSet set = connector.doRequest(
      String.format("SELECT * FROM users WHERE id=%d;", id), false
    );

    return set;
  }

  @Override
  public ResultSet doRequest(String query, boolean changingValues) throws SQLException {
    ResultSet resultSet = null;
    Connection connection = null;

    try {
      connection = this.connect(this.getDatabaseName(), this.getDatabaseUserName(), this.getDatabaseUserPassword());
    } catch (NullPointerException nullPointerException) {
      System.err.println("Cannot do the request cause a connection has not been set yet");
    }

    assert connection != null : "Cannot execute he query cause connection is null.";
    Statement statement = connection.createStatement();

    if (changingValues) statement.executeUpdate(query);
    else resultSet = statement.executeQuery(query);

    return resultSet;
  }

  @Override
  public Connection connect(String databaseName, String databaseUserName, String databaseUserPassword) {
    this.setDatabaseName(databaseName);
    this.setDatabaseUserName(databaseUserName);
    this.setDatabaseUserPassword(databaseUserPassword);

    Connection connection = null;

    System.out.printf("%sConnecting to database..%s\n", this.BLUE_COLOR, this.RESET);

    try {
      Class.forName(this.getDriverName());
    } catch (ClassNotFoundException classNotFoundException) {
      System.err.println(classNotFoundException.getMessage());
    }

    try {
      String dbUrl = String.format(
        "jdbc:mysql://localhost/%s?useUnicode=true" +
        "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&" +
        "serverTimezone=UTC", databaseName);

      connection = DriverManager.getConnection(dbUrl, this.getDatabaseUserName(), this.getDatabaseUserPassword());

      System.out.printf("%sSuccessfully connected to database.%s\n", this.BLUE_COLOR, this.RESET);
    } catch (SQLException sqlException) {
      System.err.println(sqlException.getMessage());
    }

    return connection;
  }

  @Override
  protected void init() {
    this.setDriverName("com.mysql.cj.jdbc.Driver");
  }

   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public SQLConnector() { }
}
