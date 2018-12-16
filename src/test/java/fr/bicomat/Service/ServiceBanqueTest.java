package fr.bicomat.Service;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.bicomat.entities.Banque;
import fr.bicomat.entities.Conseiller;
@RunWith(SpringRunner.class)
@SpringBootTest

public class ServiceBanqueTest {

	@Autowired
	private BanqueService banqueService;
	
	@Test
	public void testGetBanqueById() {
		/** Création d'un banque */
		Banque bank = new Banque("Banque 1 ");
		
		Banque bankupadte = banqueService.saveBanque(bank);
		assertFalse(bankupadte.getIdbanque() == null);
		Integer id =  bankupadte.getIdbanque();
		
		Banque bankGet = banqueService.getBanqueById(id);
		assertNotNull(bankGet);
	}



	@Test
	public void testGetConseillerById() {
		Conseiller conseil = new Conseiller("nomConseil","PrenomConseil");
	

		Conseiller conseilupadte = banqueService.saveConseillier(conseil);
		assertFalse(conseilupadte.getIdconseil()==null);
		Integer id =  conseilupadte.getIdconseil();
		Conseiller conseilGet = banqueService.getConseillerById(id);
		assertNotNull(conseilGet);
	}


	@Test
	public void testDeleteConseillier() {
		Conseiller conseillier = new Conseiller("Todelete","Todelet");
		
		Conseiller conseilupadte = banqueService.saveConseillier(conseillier);
		assertFalse(conseilupadte.getIdconseil() == null);
		Integer id =  conseilupadte.getIdconseil();
		banqueService.deleteConseillier(id);
	}

	@Test
	public void testAjouterCompteAgencyConseillier() {
		
		Conseiller conseillier = banqueService.getConseillerById(1);
	
		assertNotNull(banqueService.AjouterCompteAgencyConseillier(conseillier,"email@agency.com","conseiller"));
	}

}
