package fr.bicomat.Auth.entities;

/**
 * structure d'objet pour le changement de mot passe.
 * @author linda
 *
 */
public class dtoChangedPassword {
	   private Integer iduser;

	   private Integer idquestion;

	   private String answer;

	   private String password;
	   
	   public Integer getIduser(){
		   return iduser;
	   }

	   public Integer getIdquestion() {
		   return idquestion;
	   }

	   public String getAnswer() {
		   return answer;
	   }

	   public String getPassword(){
		   return password;
	   }
	   
	   public void setIduser(Integer iduser){
		   this.iduser = iduser;
	   }

	   public void setIdquestion(Integer idquestion) {
		   this.idquestion = idquestion;
	   }

	   public void getAnswer(String answer) {
		   this.answer = answer;
	   }

	   public void getPassword(String password){
		   this.password = password;
	   }


}
