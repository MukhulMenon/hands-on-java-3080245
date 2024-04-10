package bank;

import bank.exceptions.AmountException;

public class Account {
  private int accountId;
  private String type;
  private double accountBalance;

  public Account(int accountId, String type, int accountBalance) {
    setAccountId(accountId);
    setType(type);
    setAccountBalance(accountBalance);
  }

  public int getAccountId() {
    return this.accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getAccountBalance() {
    return this.accountBalance;
  }

  public void setAccountBalance(double accountBalance) {
    this.accountBalance = accountBalance;
  }

  public void deposit(double amount) throws AmountException {
    if (amount <= 1) {
      throw new AmountException("Minimum deposit is 1");
    } else {
      double newBalance = accountBalance + amount;
      setAccountBalance(newBalance);
      DataSource.updateBalance(accountId, newBalance);
    }

  }

  public void withdraw(double amount) throws AmountException {
    if (amount < 0) {
      throw new AmountException("Amount should be more than 0");
    } else if (amount > getAccountBalance()) {
      throw new AmountException("You do not have sufficent funds");
    } else {
      double newBalance = accountBalance - amount;
      setAccountBalance(newBalance);
      DataSource.updateBalance(accountId, newBalance);
    }
  }
}