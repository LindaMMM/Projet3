package fr.bicomat.Auth.entities;

/**
 * structure d'objet pour le changement de mot passe.
 * @author linda
 *
 */
public class dtoResetPassword {
	   private String ssoId;

	   private String answer;

	   private String numCard;
	   
	   public String getSsoId() {
		   return ssoId;
	   }

	   public String getAnswer() {
		   return answer;
	   }

	   public String getNumCard(){
		   return numCard;
	   }
	

	   public void setSsoId(String ssoId) {
		   this.ssoId = ssoId;
	   }

	   public void getAnswer(String answer) {
		   this.answer = answer;
	   }

	   public void getNumCard(String numCard){
		   this.numCard = numCard;
	   }


}
