package fr.bicomat.Auth.service;

import fr.bicomat.Auth.entities.User_App;

/**
 * Service des gestion des users. 
 * @author linda
 *
 */
public interface UserService {

	/**
	 * Obtient la liste des users.
	 * @return 
	 */
	public Iterable<User_App> listAllUsers();

	/**
	 * Obtient un user à partir de son identifiant.
	 * @param id identifiant du user.
	 * @return user trouvé.
	 */
	public User_App getUserById(Long id);

	/**
	 * Enregistre un user.
	 * @param user objet user_app.
	 * @return le user mise à jour.
	 */
	public User_App saveUser(User_App user) ;

	/**
	 * Suppprime un user à partir de son id.
	 * @param id identifiant du user.
	 */
	public void deleteUser(Long id);

	User_App findBySso(String ssoId);
	
}
