package fr.bicomat.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue(value = "CLIENT")
public class CompteClient extends Compte implements java.io.Serializable {

	/**
	 * Identifiant de sérialisation 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Etat du découvert
	 */
	String etatDecouvert = EtatDecouvert.PAS_AUTORISER.getEtat();
	
	/**
	 * Solde du compte.
	 */
	private double solde = 0;
	
	/**
	 * Montant du découvert
	 */
	double montantDecouvert = 0 ;
	
	
	@Column(name = "montantDecouvert", nullable = false, precision = 22, scale = 0)
	public double getMontantDecouvert() {
		return this.montantDecouvert;
	}

	public void setMontantDecouvert(double montantDecouvert) {
		this.montantDecouvert = montantDecouvert;
	}
	
	@Column(name = "etatDecouvert", length = 8)
	public String getEtatDecouvert() {
		return this.etatDecouvert;
	}

	public void setEtatDecouvert(String etatDecouvert) {
		this.etatDecouvert = etatDecouvert;
	}
	
	@Column(name = "solde", nullable = false, precision = 22, scale = 0)
	public double getSolde() {
		return this.solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

}
