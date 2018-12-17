package fr.bicomat.Validation;



import java.util.Date;

import org.hibernate.type.DateType;

import fr.bicomat.entities.CompteClient;
import fr.bicomat.entities.CompteTiers;
import fr.bicomat.entities.Operation;

public class CompteTierValidation {
	
	/**
	 * Compte Tiers à valider avant modification.
	 */
	private CompteTiers comptetiers;
	
	/**
	 * Constructeur du comptetiers.
	 * @param comptetiers compte
	 */
	public CompteTierValidation(CompteTiers comptetiers) {
	 	this.comptetiers = comptetiers;
	}
	
	
	/**
	 * Validation avant la mise à jour en base. 
	 * @throws IllegalArgumentException si toutes les régles ne sont pas respecter
	 */
	public void validUpdate() throws IllegalArgumentException  {
		if(!comptetiers.getCodeActivation().isEmpty()) {
			test24heure();
			testCode();
			comptetiers.setActif(true);
		}
	}
	
	/**
	 * Validation avant L'ajout en base. 
	 * @throws IllegalArgumentException si toutes les régles ne sont pas respecter
	 */
	public void validInsert() throws IllegalArgumentException  {
		comptetiers.setActif(false);
		comptetiers.setDateCreation(new Date());
		comptetiers.setCodeActivation("");
	}
	
	/**
	 * Test si la demande à plus de 24 heures
	 * @throws IllegalArgumentException refus d'activer un compte de moins de 24 heures.
	 */
	@SuppressWarnings("deprecation")
	private void test24heure() throws IllegalArgumentException{
		
		Date hier = new Date();
		hier.setDate(hier.getDate()-1);
		if (comptetiers.getDateCreation().compareTo(hier)< 0) {
			throw new IllegalArgumentException("Le compte tier ne peut pas être activé");
		}
		
	}
	/**
	 * Test du code d'activation
	 * @throws IllegalArgumentException si le code n'est pas conforme à la taille attendue.
	 */
	private void testCode() throws IllegalArgumentException{
		if (comptetiers.getCodeActivation().length()!=8) {
			throw new IllegalArgumentException("Le code d'activation n'est pas conforme");
		}
	}
}
