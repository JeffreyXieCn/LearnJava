package com.in28minutes.learning.jpa.jpain10steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.in28minutes.learning.jpa.jpain10steps.entity.User;
import com.in28minutes.learning.jpa.jpain10steps.service.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserRepositoryCommandLineRunner implements CommandLineRunner {
  @Autowired private UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {
    User user = new User("Jill", "Admin");
    userRepository.save(user);
    log.info(">>>>New user is created: " + user);

    log.info(">>>>User with ID one: " + userRepository.findById(1L));
    log.info(">>>>All users: " + userRepository.findAll());
  }
}
