package fr.bicomat.entities;

/**
 * Definition des types de virements.
 * @author linda
 *
 */
public enum TypeVirement {
	PONCTUEL("Ponctuel",0,"1"),
	PERMANENT_MENSUEL("Permanent mensuel",1,"M"),
	PERMANENT_TRIMESTRIEL("Permanent trimestriel", 3,"T"),
	PERMANENT_SEMESTRIEL("Permanent semestriel", 6,"S"),
	PERMANENT_ANNUEL("Permanent annuel", 12,"A");
	
	/**
	 * Nom du type de virement. 
	 */
	private String typeVirement;
	
	/**
	 * Fréquence en mois. 
	 */
	private int frequence;
	
	/**
	 * Fréquence en mois. 
	 */
	private String code;
	
	/**
	 * Constructeur du type de virement.
	 * @param type Nom du type.
	 * @param frequence fréquence de l'appel.
	 */
	private TypeVirement(final String type,final int frequence, final String code){
		this.typeVirement = type;
		this.frequence = frequence;
		this.code = code;
	}
	
	/**
	 * Obtient le type de virement.
	 * @return
	 */
	public String getType(){
		return this.typeVirement;
	}
	
	/**
	 * Obtient le code type de virement.
	 * @return
	 */
	public String getCode(){
		return this.code;
	}
	
	/**
	 * Obtient le type la fréquence de virement.
	 * @return temps en mois.
	 */
	public int getFrequence(){
		return this.frequence;
	}

	@Override
	public String toString(){
		return this.typeVirement;
	}

	/**
	 * Nom de type de virement.
	 * @return obtient le nom.
	 */
	public String getName(){
		return this.name();
	}

	/**
	 * D'une chaine Obtient le type de virement
	 * @param text correspondant au type
	 * @return Le type trouvée.
	 */
	public static TypeVirement fromString(String text) {
	    for (TypeVirement b : TypeVirement.values()) {
	      if (b.code.equalsIgnoreCase(text)) {
	        return b;
	      }
	    }
	    return null;
	  }
}
