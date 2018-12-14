package fr.bicomat.Service;

import org.springframework.stereotype.Service;

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

}
