package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
  private static List<User> users = new ArrayList<>();
  private static int usersCount = 0;

  static {
    users.add(new User(++usersCount, "Adam", new Date()));
    users.add(new User(++usersCount, "Eve", new Date()));
    users.add(new User(++usersCount, "Jack", new Date()));
  }

  public List<User> findAll() {
    return users;
  }

  public User save(User user) {
    if (user.getId() == null) {
      user.setId(++usersCount);
    }
    users.add(user);
    return user;
  }

  public User findOne(int id) {
    for (User user : users) {
      if (user.getId() == id) {
        return user;
      }
    }

    return null;
  }

  public User deleteById(int id) {
    Iterator<User> ite = users.iterator();
    while (ite.hasNext()) {
      User user = ite.next();
      if (user.getId() == id) {
        ite.remove();
        return user;
      }
    }

    return null;
  }
}
