package fr.bicomat.Auth.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.bicomat.Auth.entities.User_App;
import fr.bicomat.config.MvcConfig;
import fr.bicomat.config.WebSecurityConfig;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAppRepositoryTest {

	@Autowired
	private UserAppRepository userRepository;
	
	@Test
	public void test() {
		
		 //get all products, list should only have one
	    Iterable<User_App>  users = userRepository.findAll();
	
	    int count = 0;
	    for (User_App u : users) {
	        count++;
	    }
	
	    assertEquals(count, 1);
	    
		User_App user = new User_App();
		user.setFirstName("Name");
		user.setLastName("Name");
		user.setEmail("Name");
		user.setPassword("Name");
		user.setSsoId("lo");
    	user.setId(2L);

    	userRepository.save(user);
    	
    	User_App user2 = new User_App();
    	user2.setFirstName("Nom3");
    	user2.setLastName("LOLO");
    	user2.setEmail("Name@mm");
    	user2.setPassword("mmmm");
    	user2.setSsoId("po");
    	user2.setId(1L);

    	// userRepository.deleteAll();
    	userRepository.save(user);
    	assertNotNull(user.getId()); //not null after save

	    //fetch from DB
    	User_App fetchedUser = userRepository.findById(user.getId()).get();
	
	    //should not be null
	    assertNotNull(fetchedUser);
	
	    //should equal
	    assertEquals(user.getId(), fetchedUser.getId());
	    assertEquals(user.getLastName(), fetchedUser.getLastName());
	
	    //update description and save
	    fetchedUser.setLastName("New Description");
	    userRepository.save(fetchedUser);
	
	    //get from Aerospike, should be updated
	    User_App fetchedUpdatedProduct = userRepository.findById(fetchedUser.getId()).get();
	    assertEquals(fetchedUser.getLastName(), fetchedUpdatedProduct.getLastName());
	
	    //verify count of products in DB
	    long productCount = userRepository.count();
	    assertEquals(productCount, 2);
	
	   
	}

}
