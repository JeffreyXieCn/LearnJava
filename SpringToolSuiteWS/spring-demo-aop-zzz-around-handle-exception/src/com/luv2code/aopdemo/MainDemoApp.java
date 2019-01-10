package com.luv2code.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

  public static void main(String[] args) {
    // read Spring config java class
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(DemoConfig.class);

    // get the bean from spring container
    AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);

    // call the business method
    theAccountDAO.addAccount(new Account("Madhu", "Platinum"), true);
    theAccountDAO.doWork();

    // call the accountdao getter/setter methods
    theAccountDAO.setName("foobar");
    theAccountDAO.setServiceCode("silver");
    String name = theAccountDAO.getName();
    String code = theAccountDAO.getServiceCode();

    MembershipDAO membershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
    membershipDAO.addSillyMember();
    membershipDAO.goToSleep();

    // do it again!
    //    System.out.println("\nLet's call it again!\n");
    //    theAccountDAO.addAccount();
    //    membershipDAO.addSillyMember();

    // close the context
    context.close();
  }
}
