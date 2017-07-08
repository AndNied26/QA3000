package de.unipassau.webeng.application.service;

import de.unipassau.webeng.persistence.entities.Role;
import de.unipassau.webeng.persistence.entities.User;
import de.unipassau.webeng.persistence.repository.RoleRepository;
import de.unipassau.webeng.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> userList() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(String username) {
        return userRepository.findOne(username);
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public String deleteUser(String username) {
        userRepository.delete(username);
        return "{'message':'User deleted successfully.'}";
    }
}