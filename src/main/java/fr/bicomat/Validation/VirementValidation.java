package fr.bicomat.Validation;

import java.util.Date;

import fr.bicomat.entities.CompteClient;
import fr.bicomat.entities.TypeCompte;
import fr.bicomat.entities.TypeVirement;
import fr.bicomat.entities.Virement;

public class VirementValidation {
	/**
	 * Virement à traiter.
	 */
	private Virement virement;
	
	/**
	 * Constructeur d'un virement.
	 * @param virement virement à traiter.
	 */
	public VirementValidation(Virement virement) {
		this.virement = virement; 
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
		TypeCompte typecompte = TypeCompte.fromString(virement.getCompteByCompteDebiteur().getTypeCompte());
		if (typecompte.getCategorie().equals("T"))
		{
			// On ne peut pas avoir virement débiteur sur un compte Tiers.
			throw new IllegalArgumentException("Un compte Tier ne peut pas être débité.");
		}
		testTypeVirement();
	}
	
	
	/**
	 * Test des conditions pour un compte courant.
	 * @throws IllegalArgumentException En cas de non respect des régles.
	 */
	@SuppressWarnings("deprecation")
	private void testTypeVirement() throws IllegalArgumentException {
		if (virement.getTypeVirement().equals(TypeVirement.PONCTUEL.getType())){
			// test si le compte ne sera pas débiteur.
			CompteClient compte = (CompteClient) virement.getCompteByCompteCrediteur();
			if (compte.getSolde()-virement.getMontant() < 0) {
				throw new IllegalArgumentException("Un Compte ne peut pas être débiteur après un virement");
			}
		}
		else {
			// test des 15 jours avant 1er échéance
			Date date15 = new Date();
			date15.setDate(date15.getDate()+15);
			if (virement.getDateEcheance().compareTo(date15)>0){
				throw new IllegalArgumentException("Un virement doit être positionner dans 15 jours minimum.");
			}
		}
	}
	
	
}
