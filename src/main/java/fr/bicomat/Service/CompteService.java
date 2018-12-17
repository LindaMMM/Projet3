package fr.bicomat.Service;

import java.util.Date;
import java.util.Set;

import fr.bicomat.entities.Alerte;
import fr.bicomat.entities.CompteClient;
import fr.bicomat.entities.CompteTiers;
import fr.bicomat.entities.Operation;
import fr.bicomat.entities.Prelevement;
import fr.bicomat.entities.Virement;

public interface CompteService {
	
	/**
	 * Obtient le compte client.
	 * @param id identifiant du compt.
	 * @return CompteClient trouvé.
	 */
	public CompteClient getCompteClientById(Integer id);

	/**
	 * Enregistre un compteClient.
	 * @param compteClient objet CompteClient.
	 * @return le compet client mise à jour.
	 */
	public CompteClient saveCompteClient(CompteClient compteClient) throws IllegalArgumentException;

	/**
	 * Suppprime un compte client à partir de son id.
	 * @param id identifiant du compteClient.
	 */
	public boolean deleteCompteClient(Integer id);
	
	/**
	 * Demande de cloture d'un compte client
	 * @param id Identifiant du code client.
	 * @return true si demande de cloture est effectué.
	 */
	public boolean DmdClotureCompteClient(Integer id);
	
	/**
	 * Cloture d'un compte client.
	 * @param id identitiant du compte client.
	 * @return true si cloture est effectuée.
	 */
	public boolean ClotureCompteClient(Integer id);
	
	/**
	 * Demande de découvert d'un compte client.
	 * @param id identifiant d'un compte client.
	 * @return True si la demande est effective.
	 */
	public boolean DmdDecouvertCompteClient(Integer id, double montant);
	
	/**
	 * Autorisation de découvert.
	 * @param id identifiant du compte.
	 * @return True si l'autorisation est effectuée.
	 */
	public boolean AutDecouvertCompteClient(Integer id);
	
	/**
	 * Refus de découvert.
	 * @param id identifiant du compte.
	 * @return True si le refus est effectuée.
	 */
	public boolean RefusDecouvertCompteClient(Integer id);
	
	/**
	 * Demande d'activation d'un compte-tiers
	 * @param id identifiant d'un compte tiers.
	 * @return True si le compte est actif.
	 */
	public boolean DemandeActivationCompteTiers(Integer id, String code);
	
	
	/**
	 * Activation d'un compte tiers
	 * @param id identifiant d'un compte tier
	 * @return true si le compte est activé.
	 */
	public boolean ActivationCompteTier(Integer id);
	
	
	
	/**
	 * Obtient le compte tiers.
	 * @param id identifiant du compte tiers.
	 * @return CompteTier trouvé.
	 */
	public CompteTiers getCompteTierById(Integer id) ;

	/**
	 * Enregistre un compteTiers.
	 * @param compteTiers objet CompteClient.
	 * @return le compte tiers mise à jour.
	 */
	public CompteTiers saveComptetTiers(CompteTiers compteClient) throws IllegalArgumentException ;

	/**
	 * Suppprime un compte Tiers à partir de son id.
	 * @param id identifiant du compteClient.
	 */
	public boolean deleteCompteTiers(Integer id);
	

	/**
	 * Obtient le virement.
	 * @param id identifiant du virement.
	 * @return virement trouvé.
	 */
	public Virement getVirementById(Long id);

	/**
	 * Enregistre un Virement.
	 * @param virement objet virement.
	 * @return le virement mise à jour.
	 */
	public Virement saveVirement(Virement virement) throws IllegalArgumentException ;

	/**
	 * Enregistre les opération d'un Virement.
	 * @param virement objet virement.
	 * @return le virement mise à jour.
	 */
	public Virement saveOperation(Virement virement) throws IllegalArgumentException ;
	
	/**
	 * Suppprime un virement à partir de son id.
	 * @param id identifiant du virement.
	 */
	public boolean deleteVirement(Long id);
	
	/**
	 * Signature d'un virement.
	 * @param id identifiant du virement.
	 */
	public boolean signatureVirement(Long id);

	/**
	 * Obtient une opérations.
	 * @param id identifiant d'une opération.
	 * @return operation trouvé.
	 */
	public Operation getOperationById(Long id);

	/**
	 * Enregistre une operation.
	 * @param operation objet virement.
	 * @return l'operation mise à jour.
	 */
	public Operation saveOperation(Operation operation) throws IllegalArgumentException ;

	/**
	 * Suppprime un virement à partir de son id.
	 * @param id identifiant du virement.
	 */
	public boolean deleteOperation(Long id);

	/**
	 * Effectue une oppositions sur une opération.
	 * @param id identifiant de l'opération.
	 * @return True si opposition effectué.
	 */
	public boolean OppositionSurOperation(Long id);
	
	/**
	 * Permet de pointer une opération
	 * @param id Identifiant Operation.
	 * @return True si aucun pb.
	 */
	public boolean PointerSurOperation(Long id);
	
	
	/**
	 * Obtient un Prelevement.
	 * @param id identifiant d'un Prelevement.
	 * @return Prelevement trouvé.
	 */
	public Prelevement getPrelevementById(Long id);

	/**
	 * Enregistre un Prelevement.
	 * @param operation objet Prelevement.
	 * @return l'operation mise à jour.
	 */
	public Prelevement savePrelevement(Prelevement prelevement) throws IllegalArgumentException ;

	/**
	 * Suppprime un Prelevement à partir de son id.
	 * @param id identifiant du Prelevement.
	 */
	public boolean deletePrelevement(Long id);

	/**
	 * Effectue une oppositions sur un Prelevement.
	 * @param id identifiant du Prelevement.
	 * @return True si opposition effectué.
	 */
	public boolean OppositionSurPrelevement(Long id);

	/**
	 * Recherche de la liste des virements à appliquer.
	 * @param date Date d'applications.
	 * @return Liste de virement.
	 */
	 Set<Virement> GetVirementApplicable(Date date);

	 /**
	  * Recherche la liste des prelevements à appliquer. 
	  * @param date date d'application.
	  * @return liste des prélévements
	  */
	 Set<Prelevement> GetPrelevementApplicable(Date date);

	 /**
	  * Creation d'opération à partir d'un prélévement
	  * @param p Prélevement
	  */
	 void saveOperation(Prelevement p);

}
