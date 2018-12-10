package fr.bicomat.Auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.bicomat.Auth.entities.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer>{

}
