package fr.bicomat.Auth.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.bicomat.Auth.entities.User_App;

/**
 * Interface des creation de Users. 
 * @author linda
 *
 */
public interface UserAppRepository extends JpaRepository<User_App, Integer> {

	/**
	 * Obtient la liste des users à partir de son nom de famille.
	 * @param lastName nom de famille.
	 * @return liste des users.
	 */
    List<User_App> findByLastName(String lastName);
    
    /**
	 * Obtient l'utilisateur à partir de son login.
	 * @param ssoId Login de l'utilisateur.
	 * @return un utilisateur.
	 */
    User_App findBySsoId(String ssoId);
    
    /**
	 * Obtient l'utilisateur à partir de son email.
	 * @param email email de l'utilisalisateur.
	 * @return un utilisateur.
	 */
   User_App findByEmail(String email);
    
}
