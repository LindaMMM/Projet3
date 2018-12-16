package fr.bicomat.Service;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.bicomat.entities.Banque;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ServiceBanqueTestAvecTransaction {
	@Autowired
	private BanqueService banqueService;
	
	@Test
	public void testDeleteBank() {
		Banque bank = new Banque("Banque delete");
		Banque bankupadte = banqueService.saveBanque(bank);
		assertFalse(bankupadte.getIdbanque() == null);
		Integer id =  bankupadte.getIdbanque();
		banqueService.deleteBank(id);
	
	}

}
