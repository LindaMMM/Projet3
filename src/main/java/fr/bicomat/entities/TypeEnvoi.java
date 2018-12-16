package fr.bicomat.entities;

public enum TypeEnvoi {
	SMS("sms"),
	EMAIL("email");
	
	/**
	 * Nom du type d'envoi. 
	 */
	private String typeEnvoi;
	
	
	
	/**
	 * Constructeur du type de virement.
	 * @param type Nom du type.
	 * @param frequence fréquence de l'appel.
	 */
	private TypeEnvoi(final String type){
		this.typeEnvoi = type;
	}
	
	/**
	 * Obtient le type de virement.
	 * @return
	 */
	public String getType(){
		return this.typeEnvoi;
	}
	

	@Override
	public String toString(){
		return this.typeEnvoi;
	}

	/**
	 * Nom de type de virement.
	 * @return obtient le nom.
	 */
	public String getName(){
		return this.name();
	}
}
