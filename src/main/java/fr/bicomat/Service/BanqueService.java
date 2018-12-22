package fr.bicomat.Service;


import java.util.List;

import fr.bicomat.Auth.entities.User_App;
import fr.bicomat.entities.Banque;
import fr.bicomat.entities.CarteBancaire;
import fr.bicomat.entities.Client;
import fr.bicomat.entities.Conseiller;
import fr.bicomat.entities.InfoCompte;

public interface BanqueService {
	
	/**
	 * Obtient un banque à partir de son identifiant.
	 * @param id identifiant du banque.
	 * @return banque trouvé.
	 */
	public Banque getBanqueById(Integer id);

	/**
	 * Enregistre une banque.
	 * @param bank objet banque.
	 * @return la banque mise à jour.
	 */
	public Banque saveBanque(Banque bank) ;

	/**
	 * Suppprime une bank à partir de son id.
	 * @param id identifiant du identifiant.
	 */
	public boolean deleteBank(Integer id);


	/**
	 * Obtient une conseillier à partir de son identifiant.
	 * @param id identifiant de le conseillier.
	 * @return Conseillier trouvé.
	 */
	public Conseiller getConseillerById(Integer id);

	/**
	 * Enregistre une conseillier.
	 * @param conseiller objet Conseillier.
	 * @return la conseillier mise à jour.
	 */
	public Conseiller saveConseillier(Conseiller conseiller) ;

	/**
	 * Suppprime une conseillier à partir de son id.
	 * @param id identifiant du identifiant.
	 */
	public boolean deleteConseillier(Integer id);
	
	/**
	 * Ajout d'un compte agence.
	 * @param conseiller la conseillier
	 * @param email email de la conseillier
	 * @param login login de la conseiller
	 * @return nouveau compte Agency
	 */
	User_App AjouterCompteAgencyConseillier(Conseiller conseiller, String email, String login)throws IllegalArgumentException;
	 

	/**
	 * Obtient une Info Compte à partir de son identifiant.
	 * @param id identifiant de le InfoCompte.
	 * @return InfoCompte trouvé.
	 */
	public InfoCompte getInfoCompteById(Integer id);

	/**
	 * Enregistre une InfoCompte.
	 * @param infoCompte objet InfoCompte.
	 * @return la conseillier mise à jour.
	 */
	public InfoCompte saveInfoCompte(InfoCompte infoCompte) ;

	/**
	 * Suppprime une infoCompte à partir de son id.
	 * @param id identifiant du infoCompte.
	 */
	public boolean deleteInfoCompte(Integer id);
	/*
	 * ADIENG
	 */
	
	public List<Client> getClientByNom(String nom) ;
	public CarteBancaire getCarteNumcarte (String numcarte);
	public List<Client> getClientByCarte(CarteBancaire carte);
	public CarteBancaire getCarteByClient(Client client);
	
}
