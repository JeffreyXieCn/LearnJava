package com.in28minutes.learning.jpa.jpain10steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.in28minutes.learning.jpa.jpain10steps.entity.User;
import com.in28minutes.learning.jpa.jpain10steps.service.UserDAOService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserDaoServiceCommandLineRunner implements CommandLineRunner {
  @Autowired private UserDAOService userDaoService;

  @Override
  public void run(String... args) throws Exception {
    User user = new User("Jack", "Admin");
    long userId = userDaoService.insert(user);
    log.info(">>>>New user is created: " + user);
  }
}
