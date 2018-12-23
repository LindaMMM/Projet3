package fr.bicomat.Validation;

import fr.bicomat.entities.Compte;
import fr.bicomat.entities.CompteClient;
import fr.bicomat.entities.Operation;

/**
 * Validatin d'un operation
 * @author linda
 *
 */
public class OperationValidation {
	/**
	 * Opération à valider.
	 */
	private Operation operation;
	
	/**
	 * Constructeur de la validation d'un opération.
	 * @param operation opération à valider.
	 */
	public OperationValidation(Operation operation) {
		this.operation = operation;
	}
	
	/**
	 * Validation avant la mise à jour en base. 
	 * @throws IllegalArgumentException si toutes les régles ne sont pas respecter
	 */
	public void validUpdate() throws IllegalArgumentException  {
		
	}
	
	/**
	 * Validation avant L'ajout en base. 
	 * @throws IllegalArgumentException si toutes les régles ne sont pas respecter
	 */
	public void validInsert() throws IllegalArgumentException  {
		this.testCompteClient();
	}

	/**
	 *  Test le compte client.
	 */
	private void testCompteClient() {
		Compte compteOp = operation.getCompte();
		if (compteOp instanceof CompteClient) {
			CompteClientValidation validCompte = new CompteClientValidation((CompteClient)compteOp);
			validCompte.validUpdate();
		}
		else{
			throw new IllegalArgumentException("Un opération sur un compte tier n'est pas être prise en compte"); 
		}
	}
}
