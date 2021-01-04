package server.database;

import java.sql.*;

/**
 * @author Ribeyrolles Matthieu
 * 29/12/2020, 22:42
 */
public abstract class DbConnector {
  private String databaseName;
  private String databaseUserName;
  private String databaseUserPassword;
  private String driverName;
  private Connection connection;

  protected String getDatabaseUserPassword() { return databaseUserPassword; }
  protected String getDatabaseName() { return databaseName; }
  protected String getDatabaseUserName() { return databaseUserName; }
  protected Connection getConnection() { return connection; }
  protected String getDriverName() {return driverName; }

  protected void setDatabaseName(String databaseName) { this.databaseName = databaseName; }
  protected void setDatabaseUserPassword(String databaseUserPassword) { this.databaseUserPassword = databaseUserPassword; }
  protected void setDatabaseUserName(String databaseUserName) { this.databaseUserName = databaseUserName; }
  protected void setDriverName(String driverName) { this.driverName = driverName; }
  protected void setConnection(Connection connection) { this.connection = connection; }
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  protected abstract void init();
  // public
  public final void connect() {
    try {
      this.connect(this.databaseName, this.databaseUserName, this.databaseUserPassword);
    } catch (NullPointerException nullPointerException) {
      System.err.println("Cannot connect to the database. You must do a connection with args first.");
    }
  }

  public abstract ResultSet getAllColumnsFor(String table) throws SQLException;
  public abstract ResultSet getFriendsOf(int id);
  public abstract void connect(String database, String user, String password);
  public abstract int getAllowedSizeForColumnField(String table, String column);

  public abstract ResultSet doRequest(String query, boolean changingValues) throws SQLException;
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public DbConnector() { }
}
