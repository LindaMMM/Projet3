package fr.bicomat.Auth.service;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.bicomat.Auth.entities.UserProfile;
import fr.bicomat.Auth.entities.UserProfileType;
import fr.bicomat.Auth.entities.User_App;
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
		
		@Override
		public Iterable<User_App> listAllUsers() {
			return userRepository.findAll();
		}

		@Override
		public User_App  getUserById(Long id) {
			return userRepository.findById(id).get();
		}

		@Override
		public User_App saveUser(User_App user) {
			return userRepository.save(user);
		}

		@Override
		public void deleteUser(Long id) {
			userRepository.deleteById(id);
		}

		@Override
		public User_App findBySso(String ssoId) {
			System.out.println("find ssoid " + ssoId);
			System.out.println(ssoId);
			
			return userRepository.findBySsoId(ssoId);
		}
	}