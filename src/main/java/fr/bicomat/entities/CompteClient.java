package fr.bicomat.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "compteClient")
public class CompteClient extends Compte implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
