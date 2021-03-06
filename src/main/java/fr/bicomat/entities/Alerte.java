package fr.bicomat.entities;
// default package
// Generated 8 déc. 2018 07:10:38 by Hibernate Tools 5.2.11.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Alerte generated by hbm2java
 */
@Entity
@Table(name = "alerte")
public class Alerte implements java.io.Serializable {

	private Integer idalerte;
	private Client client;
	private Date dateAlerte;
	private String titre;
	private String descriptif;
	private String typeEnvoi = TypeEnvoi.SMS.getType();

	public Alerte() {
	}

	public Alerte(Client client, Date dateAlerte, String titre, String typeEnvoi) {
		this.client = client;
		this.dateAlerte = dateAlerte;
		this.titre = titre;
		this.typeEnvoi = typeEnvoi;
	}

	public Alerte(Client client, Date dateAlerte, String titre, String descriptif, String typeEnvoi) {
		this.client = client;
		this.dateAlerte = dateAlerte;
		this.titre = titre;
		this.descriptif = descriptif;
		this.typeEnvoi = typeEnvoi;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idalerte", unique = true, nullable = false)
	public Integer getIdalerte() {
		return this.idalerte;
	}

	public void setIdalerte(Integer idalerte) {
		this.idalerte = idalerte;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_idclient", nullable = false)
	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dateAlerte", nullable = false, length = 10)
	public Date getDateAlerte() {
		return this.dateAlerte;
	}

	public void setDateAlerte(Date dateAlerte) {
		this.dateAlerte = dateAlerte;
	}

	@Column(name = "titre", nullable = false, length = 30)
	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	@Column(name = "descriptif", length = 100)
	public String getDescriptif() {
		return this.descriptif;
	}

	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	@Column(name = "typeEnvoi", nullable = false, length = 5)
	public String getTypeEnvoi() {
		return this.typeEnvoi;
	}

	public void setTypeEnvoi(String typeEnvoi) {
		this.typeEnvoi = typeEnvoi;
	}

}
