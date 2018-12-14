package fr.bicomat.Auth.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fr.bicomat.Auth.entities.State;
import fr.bicomat.Auth.entities.UserProfile;
import fr.bicomat.Auth.entities.UserProfileType;
import fr.bicomat.Auth.entities.UserQuestion;
import fr.bicomat.Auth.entities.User_App;
import fr.bicomat.Auth.entities.dtoChangedPassword;
import fr.bicomat.Service.ClientService;
import fr.bicomat.Auth.dao.UserAppRepository;
import fr.bicomat.Auth.dao.UserProfileRepository;
import fr.bicomat.Auth.dao.UserQuestionRepository;


@Service
public class UserServiceImpl implements UserService {

		@Autowired
		private UserAppRepository userRepository;
		
		@Autowired
		private UserProfileRepository userProfilRepository;
		
		@Autowired
		private UserQuestionRepository userQuestionRepository;
		
		@Autowired
		private EmailService serviceEmail;
		
		@Autowired
		private ClientService serviceClient;
		
		@Override
		public Iterable<User_App> listAllUsers() {
			return userRepository.findAll();
		}

		@Override
		public User_App  getUserById(Integer id) {
			return userRepository.findById(id).get();
		}

		@Override
		public User_App saveUser(User_App user) {
			boolean sendEmail = user.getId()==0;
			
			User_App usrTemp = userRepository.save(user);
			
			if(sendEmail){
				serviceEmail.sendConfirmationEmail(user);
				serviceEmail.sendNewPassWordEmail(user);
			}
			return usrTemp;
		}

		@Override
		public void deleteUser(Integer id) {
			userRepository.deleteById(id);
		}

		@Override
		public User_App getUserByssoId(String ssoId) {
			System.out.println("find ssoid " + ssoId);
			System.out.println(ssoId);
			
			return userRepository.findBySsoId(ssoId);
		}

		@Override
		public User_App razTryPwd(User_App user) {
			user.setNbTry(0);
			return userRepository.save(user);
		}

		@Override
		public User_App resetPwd(User_App user) {
			user.setNbTry(0);
			user.setPassword(generadNewPassword());
			user.setState(State.PROVISIONAL.getState());
			serviceEmail.sendNewPassWordEmail(user);
			return userRepository.save(user);
		}

		@Override
		public User_App updateNewTry(User_App user) {
			user.setNbTry(user.getNbTry()+1);
			if (user.getNbTry()>3)
			{
				user.setState(State.LOCKED.getState());
			}
			return userRepository.save(user);
		}

		@Override
		public User_App changePwd(dtoChangedPassword dto) {
			User_App user = userRepository.getOne(dto.getIduser());
			user.setAnswer(dto.getAnswer());
			user.setState(State.ACTIVE.getState());
			user.setNbTry(0);
			user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
			user.setUserQuestion(userQuestionRepository.getOne(dto.getIdquestion()));
			return userRepository.save(user);
		}
		
		/**
		 * Effectue la génération d'un nouveau mot de passe. 
		 * @return
		 */
		private String generadNewPassword(){
			  return RandomStringUtils.randomAlphabetic(10); 
		}

		@Override
		public User_App deleteCompte(User_App user) {
			user.setState(State.DELETED.getState());
			return userRepository.save(user);
		}

		@Override
		public List<UserQuestion> getAllQuestion() {
			return userQuestionRepository.findAll();
		}

		@Override
		public User_App getUserByEmail(String email) {
			return userRepository.findByEmail(email);
		}

		@Override
		public boolean resetPwd(String ssoId, String numcarte, String reponse) {
			User_App user = userRepository.findBySsoId(ssoId);
			
			boolean result = user.getAnswer().equals(reponse);
			if (result)
			{
				UserProfile uprofil =  userProfilRepository.findByType( UserProfileType.CLIENT.getUserProfileType());
				if (user.getUserProfiles().contains(uprofil))
				{
					result = serviceClient.checkClient(user.getIdClient(), numcarte);
				}
			}
			
			if (result)
			{
				// Génération d'un nouveau mot de passe.
				resetPwd(user);
				return true;
			}
		
			return false;
		}
	}