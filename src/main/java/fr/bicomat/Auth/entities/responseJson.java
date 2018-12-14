package fr.bicomat.Auth.entities;

public class responseJson implements java.io.Serializable {

	/**
	 * Version de la sérialisation.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Code retour
	 */
	private Integer code;
	
	/**
	 * Message à transmettre au client.
	 */
	private String message;

	
	/**
	 * Obtient le code retour.
	 */
	public Integer getCode() {
		return this.code;
	}
	
	/**
	 * Obtient le message à transmettre au client.
	 */
	public String getMessage() {
		return this.message;
	}
	
	/**
	 * Obtient le code retour.
	 */
	public void setCode(Integer code) {
		this.code = code;
	}
	
	/**
	 * Message à transmettre au client.
	 */
	public void getMessage(String message) {
		this.message = message;
	}

	
}
