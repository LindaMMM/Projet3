package fr.bicomat.Validation;

import java.time.LocalDate;
import java.time.Period;
import java.util.Iterator;

import fr.bicomat.entities.Compte;
import fr.bicomat.entities.CompteClient;
import fr.bicomat.entities.EtatDecouvert;
import fr.bicomat.entities.InfoCompte;
import fr.bicomat.entities.TypeCompte;

/**
 * Classe de validation 
 */
public class CompteClientValidation {

	/**
	 * référence du compte client.
	 */
	private CompteClient compteclient;
	
	/**
	 * Montant des intérêts.
	 */
	private double montantInteret;
	
	/**
	 * Montant de impots calculer.
	 */
	private double montantImpot;
	
	/**
	 * Obtient le montant des interet lors de la cloture d'un compte.
	 * @return le somme de interêts.
	 */
	public double getMontantInteret()
	{
		return montantInteret;
	}
	
	/**
	 * Obtient le montant des impôts lors de la cloture.
	 * @return Somme pour les impôt.
	 */
	public double getMontantImpot() {
		return montantImpot;
	}
	
	
	
	/**
	 * Constructreur d'un compte client.
	 * @param compteclient Compte client.
	 */
	public CompteClientValidation(CompteClient compteclient) {
		this.compteclient = compteclient;
	}

	/**
	 * Validation avant la mise à jour en base. 
	 * @throws IllegalArgumentException si toutes les régles ne sont pas respecter
	 */
	public void validUpdate() throws IllegalArgumentException  {
		TypeCompte typecpt = TypeCompte.fromString(compteclient.getTypeCompte());
		if (typecpt.getCategorie().equals("E")){
			this.testMinMaxSolde();
		}
		else {
			this.testDecouvert();
		}
	}
	
	/**
	 * Validation avant L'ajout en base. 
	 * @throws IllegalArgumentException si toutes les régles ne sont pas respecter
	 */
	public void validInsert() throws IllegalArgumentException  {
		TypeCompte typecpt = TypeCompte.fromString(compteclient.getTypeCompte());
		if (typecpt.getCategorie().equals("E")){
			this.testUniciteCompte(typecpt);
			
			this.testMinMaxSolde();
		}
	}

	/**
	 * Effectue la cloture d'un compte.
	 */
	public void calculerCloture() throws IllegalArgumentException {

		TypeCompte typecpt = TypeCompte.fromString(compteclient.getTypeCompte());
		if (typecpt.getCategorie().equals("E")) {
			if (compteclient.getInfoCompte()!=null) {
				 InfoCompte info = compteclient.getInfoCompte();
				 LocalDate now = LocalDate.now();
				 LocalDate janvier =LocalDate.of(now.getYear(), 1, 1);
				 
				Period period = Period.between(janvier,now);
				int duree = period.getMonths();
				this.montantInteret = compteclient.getSolde() * info.getTauxInteret() / 100 * duree;
				this.montantImpot = this.montantInteret  * 21 / 100 ;
			}
			else {
				throw new IllegalArgumentException("Erreur pas d'information au compte sur ce compte ");
			}	
		}	
	}

	/**
	 * Test unicité du compte.
	 * @param typecpt type de compte. 
	 * @throws IllegalArgument si le client a déjà un compte de ce type
	 */
	private void testUniciteCompte(TypeCompte typecpt) throws IllegalArgumentException {
		// un client ne peut pas avoir 2 comptes d'epargne de la même catégorie
		if (compteclient.getClient()!=null && compteclient.getClient().getComptes()!=null )
		{
			boolean compteExist = false;
			Iterator<Compte> it = compteclient.getClient().getComptes().iterator();
			while(it.hasNext() || !compteExist){
			
				Compte d = it.next();
				compteExist = d.getTypeCompte().equals(typecpt.getType());				
			}
			if (compteExist)
				throw new IllegalArgumentException("On ne peut pas avoir 2 comptes du même type " + typecpt.getLibelle());
		}
	}

	/**
	 * Test du min et max du solde.
	 * throws IllegalArgumentException si le solde ne correspond pas au besoin min-max du compte
	 */
	private void testMinMaxSolde() throws IllegalArgumentException {
		
		if (compteclient.getInfoCompte()!=null)
		{
			InfoCompte info = compteclient.getInfoCompte();
			if (compteclient.getSolde() < info.getMontantPlafond() || compteclient.getSolde() > info.getMontantMinimum()) {
				throw new IllegalArgumentException("Le solde ne correspond pas à la réglementation de ce compte ");
			}
		}
		else {
			throw new IllegalArgumentException("Erreur pas d'information au compte sur ce compte ");
		}
	}
	
	/**
	 * Test du min et max du solde.
	 * throws IllegalArgumentException si le solde ne correspond pas au besoin min-max du compte
	 */
	private void testDecouvert() throws IllegalArgumentException {
		
		if (compteclient.getSolde() > 0)
		{
			return ;	
		}
		
		if (compteclient.getEtatDecouvert().equals(EtatDecouvert.AUTORISER.getEtat())) {
			if (compteclient.getSolde() +  compteclient.getMontantDecouvert() > 0){
				return;
			}
		}
		throw new IllegalArgumentException("Le compte ne peut pas être à découvert.");
	}
}
