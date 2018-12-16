package fr.bicomat.Validation;

import fr.bicomat.entities.CompteClient;


public class CompteClientValidation {

	public static void checkNewCompteClient(CompteClient compteCl) throws IllegalArgumentException {
		compteCl.getClient().getComptes();
		/********************************
		/* todo test compte */
			

	} 
	
	public static void checkUpdateCompteClient(CompteClient compteCl) throws IllegalArgumentException {
		
	} 
	
	public static double CalculCloture(CompteClient compteCl) {
		return 0.0;
	}
}
