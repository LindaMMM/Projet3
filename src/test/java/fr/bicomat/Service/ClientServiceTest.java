package fr.bicomat.Service;

import static org.junit.Assert.*;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.bicomat.entities.Alerte;
import fr.bicomat.entities.Banque;
import fr.bicomat.entities.Client;
import fr.bicomat.entities.Conseiller;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ClientServiceTest {

	@Autowired
	private ClientService clientService;
	@Autowired
	private BanqueService banqueService;
	
	@Test
	public void testCheckClientIntegerString() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckClientStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetClientById() {
		/** Creation d'un client*/
		Conseiller conseiller = banqueService.getConseillerById(1);
		Banque bank = banqueService.getBanqueById(1);
		
		Client client = new Client(conseiller,"NomClient","prenomClient",new Date());
		client.getBanques().add(bank);
		
		clientService.saveClient(client);	
	}

	@Test
	public void testDeleteClient() {
		Conseiller conseiller = banqueService.getConseillerById(1);
		Banque bank = banqueService.getBanqueById(1);
		
		Client client = new Client(conseiller,"NomClient-del","prenomClient-del",new Date());
		client.getBanques().add(bank);
		
		client = clientService.saveClient(client);
		Integer id= client.getIdclient();
		assertTrue(clientService.deleteClient(id));
	}

	@Test
	public void testAjouterCompteAgencyClient() {
		fail("Not yet implemented");
	}
	@Test
	public void testAlerteById() {
		fail("Not yet implemented");
	}

	@Test
	public void testsaveAlerte() {
		fail("Not yet implemented");
	}

	@Test
	public void testdeleteAlerte() {
		fail("Not yet implemented");
		
	}

	@Test
	public void testGetCarteBancaireById() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveCarteBancaire() {
		fail("Not yet implemented");
	}

	@Test
	public void deleteCarteBancaire() {
		fail("Not yet implemented");
		
	}

}
