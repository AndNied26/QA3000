package de.unipassau.webeng.persistence.repository;


import de.unipassau.webeng.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

}
