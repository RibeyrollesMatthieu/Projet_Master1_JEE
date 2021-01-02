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

  protected String getDatabaseUserPassword() { return databaseUserPassword; }
  protected String getDatabaseName() { return databaseName; }
  protected String getDatabaseUserName() { return databaseUserName; }
  public String getDriverName() {return driverName; }

  protected void setDatabaseName(String databaseName) { this.databaseName = databaseName; }
  protected void setDatabaseUserPassword(String databaseUserPassword) { this.databaseUserPassword = databaseUserPassword; }
  protected void setDatabaseUserName(String databaseUserName) { this.databaseUserName = databaseUserName; }
  public void setDriverName(String driverName) { this.driverName = driverName; }

  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  protected abstract void init();
  // public
  public Connection connect() {
    try {
      return this.connect(this.databaseName, this.databaseUserName, this.databaseUserPassword);
    } catch (NullPointerException nullPointerException) {
      System.err.println("Cannot connect to the database. You must do a connection with args first.");
    }

    return null;
  }
  public abstract Connection connect(String database, String user, String password);
  public abstract ResultSet doRequest(String query, boolean changingValues) throws SQLException;
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public DbConnector() { this.init(); }
}
