package fr.bicomat.Auth.entities;

public enum UserProfileType {
	CLIENT("CLIENT"),
	AGENT("AGENT"),
	ADMIN("ADMIN");
	
	String userProfileType;
	
	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	
}
