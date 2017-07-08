package de.unipassau.webeng.persistence.repository;


import de.unipassau.webeng.persistence.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
