package de.unipassau.webeng.application.service;

import de.unipassau.webeng.persistence.entities.User;

import java.util.List;

public interface UserService {
    List<User> userList();

    User findOne(String username);

    User addUser(User user);

    String deleteUser(String username);
}
