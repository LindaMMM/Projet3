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

	/**
	 * Touver un utilisateur à partir de son login.
	 * @param ssoId Login de l'utilisateur
	 * @return User trouvée.
	 */
	User_App findBySso(String ssoId);
	
	/**
	 *  Mise à jour du utilisateurs pour supprimer le nombre d'essai.
	 * @param user Utilisateur trouvée.
	 * @return User mise à jour.
	 */
	User_App razTryPwd (User_App user);
	
	/**
	 * change le mot passe avec un mot de passe temporaire.
	 * @param user Utilisateur trouvée.
	 * @return user mise à jour.
	 */
	User_App resetPwd (User_App user);
	
	/**
	 * Un nouvel essai avec erreur.
	 * @param user Utilisateur trouvée.
	 * @return user mise à jour.
	 */
	User_App updateNewTry (User_App user);

	/**
	 * permet de changer le mot de passe.
	 * @param user Utilisateur trouvée.
	 * @return user mise à jour.
	 */
	User_App changePwd (User_App user);
	
	/**
	 * permet de désactiver un compte.
	 * @param user Utilisateur trouvée.
	 * @return user mise à jour.
	 */
	User_App deleteCompte (User_App user);
	
}
