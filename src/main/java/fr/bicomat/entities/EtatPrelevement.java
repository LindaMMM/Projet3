package fr.bicomat.entities;

public enum EtatPrelevement {
	ACTIF("Actif","A"),
	DEMANDE_OPPOSITION("Demande opposition","D"),
	ANNULER("Annuler","T");
	
	/**
	 * Etat de la demande. 
	 */
	private String etat;
	
	/**
	 * code Etat de la demande. 
	 */
	private String code;
	

	/**
	 * Constructeur des etats de demande de découvert.
	 * @param etatDecouvert Nom du type.
	 */
	private EtatPrelevement(final String etatDecouvert, final String code){
		this.etat = etatDecouvert;
		this.code = code;
	}
	
	/**
	 * Obtient l'etat de la demande.
	 * @return etat de la demande.
	 */
	public String getEtat(){
		return this.etat;
	}
	
	/**
	 * Obtient le code l'état du compte.
	 * @return code etat.
	 */
	public String getCode(){
		return this.code;
	}

	@Override
	public String toString(){
		return this.etat;
	}

	/**
	 * Nom de l'état de découvert.
	 * @return obtient le nom.
	 */
	public String getName(){
		return this.name();
	}
}
