package fr.bicomat.Auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.bicomat.Auth.entities.UserQuestion;

public interface UserQuestionRepository extends JpaRepository<UserQuestion, Integer>{

}
