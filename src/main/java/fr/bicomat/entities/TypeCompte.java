package fr.bicomat.entities;

public enum TypeCompte {
	LIV_A("Livret A","-LIV-","E"),
	LDD("Livret développement durable","LDD","E"),
	LIL("Livret d'investisement local","LIL","E"),
	CDS("Compte Dêtop Solidarité","CDS","E"),
	CTIER("Compte Tiers","CDS","T"),
	COURANT("Courant","CCB","C");
	
	/**
	 * Libelle Compte. 
	 */
	private String libelle;
	
	/**
	 * Nom du type de virement. 
	 */
	private String type;
	
	/**
	 * Nom du type de virement. 
	 * "E" : Epargne.
	 * "C" : Compte Courant.
	 * "T" : Compte Tier.
	 */
	private String categorie;
	
	
	/**
	 * Constructeur du type de Client.
	 * @param type Nom du type.
	 */
	private TypeCompte(final String libelle,final String type, final String categorie){
		this.type = type;
		this.libelle = libelle;
		this.categorie = categorie;
	}
	
	
	/**
	 * Obtient le libelle de type de compte.
	 * @return
	 */
	public String getLibelle(){
		return this.libelle;
	}
	
	/**
	 * Obtient le catégorie.
	 * @return
	 */
	public String getCategorie(){
		return this.categorie;
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
	
	/**
	 * D'une chaine Obtient le type de compte
	 * @param text correspondant au type
	 * @return Le type trouvée.
	 */
	public static TypeCompte fromString(String text) {
	    for (TypeCompte b : TypeCompte.values()) {
	      if (b.type.equalsIgnoreCase(text)) {
	        return b;
	      }
	    }
	    return null;
	  }
}
