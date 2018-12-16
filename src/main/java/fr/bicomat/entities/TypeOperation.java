package fr.bicomat.entities;

public enum TypeOperation {
	DEBIT("Débit"),
	CREDIT("Crédit");
	
	/**
	 * Nom du type d'opération. 
	 */
	private String type;
	
	/**
	 * Constructeur du type de virement.
	 * @param type Nom du type.
	 * @param frequence fréquence de l'appel.
	 */
	private TypeOperation(final String type){
		this.type = type;
	}
	
	/**
	 * Obtient le type d'opération.
	 * @return
	 */
	public String getType(){
		return this.type;
	}
	
	@Override
	public String toString(){
		return this.type;
	}

	/**
	 * Nom de type d'opération.
	 * @return obtient le nom.
	 */
	public String getName(){
		return this.name();
	}
}
