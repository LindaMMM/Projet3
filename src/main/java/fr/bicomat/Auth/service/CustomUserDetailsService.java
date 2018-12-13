package fr.bicomat.Auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.bicomat.Auth.entities.State;
import fr.bicomat.Auth.entities.UserProfile;
import fr.bicomat.Auth.entities.User_App;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserService userService;
	
	
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String ssoId)
			throws UsernameNotFoundException {
		User_App user = userService.getUserByssoId(ssoId);
		System.out.println("User : "+user);
		if(user==null){
			System.out.println("User not found");
			throw new UsernameNotFoundException("Username not found");
		}
		
		UserDetails userReturn = null;
		try
		{
		if (user.getState().equals("Provisional")) {
			
			String passwordCrypt = new BCryptPasswordEncoder().encode(user.getPassword());
			userReturn = new org.springframework.security.core.userdetails.User(user.getSsoId(), passwordCrypt, 
					true , true, true, true, getGrantedAuthorities(user));

		}
		else {
			userReturn = new org.springframework.security.core.userdetails.User(user.getSsoId(), user.getPassword(), 
					user.getState().equals("Active") , true, true, true, getGrantedAuthorities(user));
		}
		}
		catch(Exception ex) {
			userService.updateNewTry(user);
			return null;
		}
		
		userService.razTryPwd(user);
		return userReturn;
	}

	
	private List<GrantedAuthority> getGrantedAuthorities(User_App user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if (user.getState().equals(State.ACTIVE.getState()))
		{
		for(UserProfile userProfile : user.getUserProfiles()){
			System.out.println("UserProfile : "+userProfile);
			authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getType()));
		}
		}
		else
		{
			authorities.add(new SimpleGrantedAuthority("ROLE_PROVISIONAL"));
		}
		System.out.print("authorities :"+authorities);
		return authorities;
	}
	
}
