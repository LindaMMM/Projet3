package fr.bicomat.Auth.service;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.bicomat.Auth.dao.UserAppRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

	@Autowired
	private UserAppRepository userRepository;
	@Test
	public void testListAllUsers() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUserById() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindBySso() {
		fail("Not yet implemented");
	}

	@Test
	public void testRazTryPwd() {
		fail("Not yet implemented");
	}

	@Test
	public void testResetPwd() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateNewTry() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangePwd() {
		fail("Not yet implemented");
	}

}
