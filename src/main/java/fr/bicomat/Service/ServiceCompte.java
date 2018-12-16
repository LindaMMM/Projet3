package fr.bicomat.Service;

import fr.bicomat.entities.CompteClient;
import fr.bicomat.entities.CompteTiers;
import fr.bicomat.entities.Operation;
import fr.bicomat.entities.Virement;

public interface ServiceCompte {
	
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
	 * @return CompteTier trouvé.
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
	
	
}
