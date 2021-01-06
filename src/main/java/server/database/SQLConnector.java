package server.database;

import java.sql.*;

/**
 * @author Ribeyrolles Matthieu
 * 29/12/2020, 22:37
 */
public class SQLConnector extends DbConnector {
  private volatile static SQLConnector instance;

  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  // public

  //TODO set in his parent too
  public ResultSet getUser(int id) throws SQLException {
    return SQLConnector.getInstance().doRequest(
      String.format("SELECT * FROM users WHERE id=%d;", id), false
    );
  }

  @Override
  public ResultSet getAllColumnsFor(String table) throws SQLException {
    //FIXME returns nothin. Works using (datatbase()) but returns all the tables columns
    return SQLConnector.getInstance().doRequest(String.format(
      "SELECT column_name FROM information_schema.columns " +
        "WHERE table_schema = database() AND table_name = '%s'", table)
      , false);
  }

  @Override
  public ResultSet getFriendsOf(int id, String status) {
    //FIXME does it check on purpose the fact that after 48, there's no more 47 (CRESC IDS)
    try {
      ResultSet set = SQLConnector.getInstance().doRequest(String.format(
        "SELECT _to FROM friendship " +
        "WHERE _from = %d " +
        "AND status = '%s';", id, status.toUpperCase()), false);

      return set;
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }

    return null;
  }

  @Override
  public int getAllowedSizeForColumnField(String table, String column) {
    try {
      ResultSet set = SQLConnector.getInstance().doRequest(String.format(
        "SELECT COLUMN_NAME, CHARACTER_MAXIMUM_LENGTH " +
          "FROM information_schema.columns " +
          "WHERE table_schema=database() AND " +
          "table_name='%s' AND " +
          "column_name='%s';"
        , table, column), false);
      set.next();

      //FIXME bigInt in table, not int
      return set.getInt(2);

    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }

    return -1;
  }

  @Override
  public ResultSet doRequest(String query, boolean changingValues) throws SQLException {
    ResultSet resultSet = null;

//    try {
//      SQLConnector.getInstance().connect();
//    } catch (NullPointerException nullPointerException) {
//      System.err.println("Cannot do the request cause a connection has not been set yet");
//    }

    assert SQLConnector.getInstance().getConnection() != null : "Cannot execute he query cause connection is null.";
    Statement statement = SQLConnector.getInstance().getConnection().createStatement();

    if (changingValues) statement.executeUpdate(query);
    else resultSet = statement.executeQuery(query);

    return resultSet;
  }

  @Override
  public void connect(String databaseName, String databaseUserName, String databaseUserPassword) {
    SQLConnector.getInstance().setDatabaseName(databaseName);
    SQLConnector.getInstance().setDatabaseUserName(databaseUserName);
    SQLConnector.getInstance().setDatabaseUserPassword(databaseUserPassword);

    System.out.printf("%sConnecting to database..%s\n", "\033[0;34m", "\033[0m");

    try {
      Class.forName(SQLConnector.getInstance().getDriverName());
    } catch (ClassNotFoundException classNotFoundException) {
      System.err.println(classNotFoundException.getMessage());
    }

    try {
      String dbUrl = String.format(
        "jdbc:mysql://localhost/%s?useUnicode=true" +
        "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&" +
        "serverTimezone=UTC", databaseName);

      SQLConnector.getInstance().setConnection(DriverManager.getConnection(dbUrl, SQLConnector.getInstance().getDatabaseUserName(), SQLConnector.getInstance().getDatabaseUserPassword()));

      System.out.printf("%sSuccessfully connected to database.%s\n", "\u001B[32m", "\033[0m");
    } catch (SQLException sqlException) {
      System.err.println(sqlException.getMessage());
    }
  }

  @Override
  protected void init() {
    SQLConnector.getInstance().setDriverName("com.mysql.cj.jdbc.Driver");
  }

  public static SQLConnector getInstance() {
    if (instance == null) {
      synchronized (SQLConnector.class) {
        if (instance == null) {
          instance = new SQLConnector();
          instance.setDriverName("com.mysql.cj.jdbc.Driver");
          instance.connect("projet_master1_jee", "root", "");
        }
      }
    }

    return instance;
  }

   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  private SQLConnector() {

  }
}
