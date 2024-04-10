package bank;

public class Customer {
  private int id;
  private String name;
  private String userName;
  private String password;
  private int accountId;

  public Customer(int id, String name, String userName, String password, int accountId) {
    setId(id);
    setName(name);
    setUserName(userName);
    setPassword(password);
    setAccountId(accountId);
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUserName() {
    return this.userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getAccountId() {
    return this.accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

}
