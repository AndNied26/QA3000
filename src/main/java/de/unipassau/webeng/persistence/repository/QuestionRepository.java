package de.unipassau.webeng.persistence.repository;


import de.unipassau.webeng.persistence.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
