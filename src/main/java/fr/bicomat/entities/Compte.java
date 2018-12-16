package fr.bicomat.entities;
// default package
// Generated 8 déc. 2018 07:10:38 by Hibernate Tools 5.2.11.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.InheritanceType;


@Entity
@Table(name = "compte")
@DiscriminatorColumn(name = "categorie")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Compte implements java.io.Serializable {

	/**
	 * Identifient de serialisation.
	 */
	private static final long serialVersionUID = 1L;
	protected Integer idcompte;
	protected Client client;
	protected String libelle;
	protected int numecompte;
	protected String etatCompte = EtatCompte.OUVERT.getCode();
	private String typeCompte = TypeCompte.LIV_A.getType() ;

	//private String typeCompte;
	protected InfoCompte infoCompte;
	
	/*protected Set<Operation> operations = new HashSet<Operation>(0);
	protected Set<Virement> virementsForCompteCrediteur = new HashSet<Virement>(0);
	protected Set<Virement> virementsForCompteDebiteur = new HashSet<Virement>(0);
*/
	protected Set<Operation> operations = new HashSet<Operation>(0);
	protected Set<Virement> virementsForCompteCrediteur = new HashSet<Virement>(0);
	protected Set<Virement> virementsForCompteDebiteur = new HashSet<Virement>(0);
	public Compte() {
	}

	public Compte(Client client, int numecompte) {
		this.client = client;
		this.numecompte = numecompte;
	}

	/*public Compte(Client client, InfoCompte infoCompte, int numecompte, double solde, String typeCompte,
			Set<CompteTiers> compteTierses, Set<Operation> operations, Set<Virement> virementsForCompteCrediteur,
			Set<Virement> virementsForCompteDebiteur) {
		this.client = client;
		this.infoCompte = infoCompte;
		this.numecompte = numecompte;
		this.solde = solde;
		this.typeCompte = typeCompte;
		this.compteTierses = compteTierses;
		this.operations = operations;
		this.virementsForCompteCrediteur = virementsForCompteCrediteur;
		this.virementsForCompteDebiteur = virementsForCompteDebiteur;
	}
*/
	@Id
	@GeneratedValue(strategy = IDENTITY)
	
	@Column(name = "idcompte", unique = true, updatable = false, nullable = false)
	public Integer getIdcompte() {
		return this.idcompte;
	}

	public void setIdcompte(Integer idcompte) {
		this.idcompte = idcompte;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_idclient", nullable = false)
	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "infoCompte_idinfoCompte")
	public InfoCompte getInfoCompte() {
		return this.infoCompte;
	}

	public void setInfoCompte(InfoCompte infoCompte) {
		this.infoCompte = infoCompte;
	}
	
	@Column(name = "typeCompte", nullable = false, length = 45)
	public String getTypeCompte() {
		return this.typeCompte;
	}

	public void setTypeCompte(String typeCompte) {
		this.typeCompte = typeCompte;
	}
	
	@Column(name = "numecompte", nullable = false)
	public int getNumecompte() {
		return this.numecompte;
	}

	public void setNumecompte(int numecompte) {
		this.numecompte = numecompte;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "compte")
	public Set<Operation> getOperations() {
		return this.operations;
	}

	public void setOperations(Set<Operation> operations) {
		this.operations = operations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "compteByCompteCrediteur")
	public Set<Virement> getVirementsForCompteCrediteur() {
		return this.virementsForCompteCrediteur;
	}

	public void setVirementsForCompteCrediteur(Set<Virement> virementsForCompteCrediteur) {
		this.virementsForCompteCrediteur = virementsForCompteCrediteur;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "compteByCompteDebiteur")
	public Set<Virement> getVirementsForCompteDebiteur() {
		return this.virementsForCompteDebiteur;
	}

	public void setVirementsForCompteDebiteur(Set<Virement> virementsForCompteDebiteur) {
		this.virementsForCompteDebiteur = virementsForCompteDebiteur;
	}
	@Column(name = "etatCompte", nullable = false, length = 1)
	public String getEtatCompte() {
		return this.etatCompte;
	}

	public void setEtatCompte(String etatCompte) {
		this.etatCompte = etatCompte;
	}
	
	@Column(name = "libelle", nullable = false, length = 45)
	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	

}
