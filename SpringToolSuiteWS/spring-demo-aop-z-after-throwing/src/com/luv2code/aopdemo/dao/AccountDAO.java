package com.luv2code.aopdemo.dao;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;
import com.luv2code.aopdemo.Account;

@Component
public class AccountDAO {

  private String name;

  private String serviceCode;

  public void addAccount(Account theAccount, boolean vipFlag) {
    System.out.println(getClass() + ": Doing my DB work: adding an account");
  }

  // add a new method: findAccount()
  public List<Account> findAccounts(boolean tripWire) {
    // for academic purpose ... simulate an exception
    if (tripWire) {
      throw new RuntimeException("No soup for you!!!");
    }

    return Arrays.asList(
        new Account[] {
          new Account("John", "Silver"),
          new Account("Madhu", "Platinum"),
          new Account("Luca", "Gold")
        });
  }

  public boolean doWork() {
    System.out.println(getClass() + ": doWork()");
    return false;
  }

  public String getName() {
    System.out.println(getClass() + ": in getName()");
    return name;
  }

  public void setName(String name) {
    System.out.println(getClass() + ": in setName()");
    this.name = name;
  }

  public String getServiceCode() {
    System.out.println(getClass() + ": in getServiceCode()");
    return serviceCode;
  }

  public void setServiceCode(String serviceCode) {
    System.out.println(getClass() + ": in setServiceCode()");
    this.serviceCode = serviceCode;
  }
}
