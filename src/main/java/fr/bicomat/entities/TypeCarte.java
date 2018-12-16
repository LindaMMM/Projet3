package fr.bicomat.entities;

public enum TypeCarte {
	CARTE_BLEU("Carte Blue"),
	MASTERCARD("MasterCard"),
	GOLD("GOLD"),
	INFINITY("INFINITY");
	/**
	 * Nom du type de virement. 
	 */
	private String type;
	
	
	/**
	 * Constructeur du type de Client.
	 * @param type Nom du type.
	 */
	private TypeCarte(final String type){
		this.type = type;
	}
	
	/**
	 * Obtient le type de client.
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
	 * Nom de type de virement.
	 * @return obtient le nom.
	 */
	public String getName(){
		return this.name();
	}
}
