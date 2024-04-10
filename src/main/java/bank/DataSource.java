package bank;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.sql.Connection;

public class DataSource {

  public static Connection connect() {
    String dbFile = "jdbc:sqlite:resources/bank.db";
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(dbFile);
      // System.out.println("We are connected :)");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }

  public static Customer getCustomer(String userName) {
    String query = "select * from customers where username =? ";
    Customer customer = null;
    try (Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setString(1, userName);
      try (ResultSet variableResult = statement.executeQuery()) {
        customer = new Customer(
            variableResult.getInt("id"),
            variableResult.getString("name"),
            variableResult.getString("userName"),
            variableResult.getString("password"),
            variableResult.getInt("account_id"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return customer;
  }

  public static Account getAccount(int accountId) {
    String query = "select * from accounts where id =?";
    Account account = null;
    try (Connection connection = connect()) {
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, accountId);
      try (ResultSet resultSet = statement.executeQuery()) {
        account = new Account(
            resultSet.getInt("id"),
            resultSet.getString("type"),
            resultSet.getInt("balance"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return account;
  }

  public static void updateBalance(int accountId, double balance) {
    String query = "update accounts set balance = ? where id =?";
    try (Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setDouble(1, balance);
      statement.setInt(2, accountId);
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace(); 
    }
  }

  public static void main(String[] args) {
    Customer customer = getCustomer("twest8o@friendfeed.com");
    System.out.println(customer.getName());
    Account account = getAccount(customer.getAccountId());
    System.out.println(account.getAccountBalance());
  }
}
