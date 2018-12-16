package fr.bicomat.Service;

import org.springframework.stereotype.Service;

import fr.bicomat.Auth.entities.User_App;
import fr.bicomat.entities.Alerte;
import fr.bicomat.entities.CarteBancaire;
import fr.bicomat.entities.Client;
import fr.bicomat.entities.Conseiller;

/**
 * Interface de la gestion du client.
 * @author linda
 *
 */
public interface ClientService {
	
	/**
	 *  Permet la vérification de que le client existe.
	 * @param idClient Identifiant du client.
	 * @param numCard numéro de la carte
	 * @return true si le client existe.
	 */
	boolean checkClient(final Integer idClient, final String numCard);
	
	/**
	 *  Permet la vérification de que le client existe.
	 * @param nomClient Nom du client.
	 * @param numCard numéro de la carte
	 * @return true si le client existe.
	 */
	boolean checkClient(final String nomClient, final String numCard);
	
	/**
	 * Obtient un Client à partir de son identifiant.
	 * @param id identifiant de le conseillier.
	 * @return Client trouvé.
	 */
	public Client getClientById(Integer id);

	/**
	 * Enregistre un client.
	 * @param client objet Client.
	 * @return le client mise à jour.
	 */
	public Client saveClient(Client client) ;

	/**
	 * Suppprime une client à partir de son id.
	 * @param id identifiant du client.
	 */
	public boolean deleteClient(Integer id);
	
	
	/**
	 * Ajouter un compte Agency.
	 * @param client donnée du client.
	 * @param login nom du login.
	 * @return Utilisateur de l'application0
	 * @throws IllegalArgumentException si le client n'a pas souscript l'option.
	 * Si le client à déjà un compte.
	 */
	User_App AjouterCompteAgencyClient(Client client,String login) throws IllegalArgumentException;
	
	
	/**
	 * Obtient une Alerte à partir de son identifiant.
	 * @param id identifiant de l'alerte.
	 * @return Alerte trouvé.
	 */
	public Alerte getAlerteById(Integer id);

	/**
	 * Enregistre un alerte.
	 * @param alerte objet alerte.
	 * @return le alerte mise à jour.
	 * @throws IllegalArgumentException si la date est inf à aujourd'hui
	 */
	public Alerte saveAlerte(Alerte alerte) throws IllegalArgumentException;

	/**
	 * Suppprime une alerte à partir de son id.
	 * @param id identifiant du alerte.
	 */
	public boolean deleteAlerte(Integer id);
	
	/**
	 * Obtient une Carte bancaire à partir de son identifiant.
	 * @param id identifiant de le  Carte bancaire.
	 * @return  Carte bancaire trouvé.
	 */
	public CarteBancaire getCarteBancaireById(Integer id);

	/**
	 * Enregistre un  Carte bancaire.
	 * @param carteBancaire objet  Carte bancaire.
	 * @return le  Carte bancaire mise à jour.
	 */
	public CarteBancaire saveCarteBancaire(CarteBancaire carteBancaire) ;

	/**
	 * Suppprime une  Carte bancaire à partir de son id.
	 * @param id identifiant du  Carte bancaire.
	 */
	public boolean deleteCarteBancaire(Integer id);
	
	

}
