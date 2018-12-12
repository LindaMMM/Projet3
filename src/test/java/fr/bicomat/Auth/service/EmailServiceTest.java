package fr.bicomat.Auth.service;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EmailServiceTest {

	@Autowired
	private EmailService emailService;

 
	 @Test
	    public void testSendEmail() {
	      
	    }
}
