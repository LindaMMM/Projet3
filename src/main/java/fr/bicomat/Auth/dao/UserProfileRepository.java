package fr.bicomat.Auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.bicomat.Auth.entities.UserProfile;
import fr.bicomat.Auth.entities.User_App;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer>{

   /**
    * Obtient un type de profil.
    * @param type type recherche.
    * @return un usertype.
    */
	UserProfile findByType (String type);
}
