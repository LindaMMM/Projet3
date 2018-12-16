package fr.bicomat.entities;

public enum EtatDecouvert {
	PAS_AUTORISER("Aucun"),
	DEMANDE_AUTORISER("Demande en cours"),
	AUTORISER("Autoriser");
	
	/**
	 * Etat de la demande. 
	 */
	private String etatDecouvert;
	

	/**
	 * Constructeur des etats de demande de découvert.
	 * @param etatDecouvert Nom du type.
	 */
	private EtatDecouvert(final String etatDecouvert){
		this.etatDecouvert = etatDecouvert;
	}
	
	/**
	 * Obtient l'etat de la demande.
	 * @return etat de la demande.
	 */
	public String getEtat(){
		return this.etatDecouvert;
	}
	

	@Override
	public String toString(){
		return this.etatDecouvert;
	}

	/**
	 * Nom de l'état de découvert.
	 * @return obtient le nom.
	 */
	public String getName(){
		return this.name();
	}
}
