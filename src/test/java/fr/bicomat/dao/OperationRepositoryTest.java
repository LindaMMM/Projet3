package fr.bicomat.dao;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.bicomat.Service.IBanqueService;
import fr.bicomat.config.CompteException;
import fr.bicomat.entities.Client;
import fr.bicomat.entities.Compte;
import fr.bicomat.entities.Operation;
import fr.bicomat.entities.OperationId;
import fr.bicomat.entities.Virement;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationRepositoryTest {
	@Autowired
    private VirementRepository virementRepository;
	@Autowired
    private OperationRepository operationRepository;
	@Autowired
	private IBanqueService ibanqueService;
	@Test
	public void test() throws CompteException {
		//Creation de virement
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate = LocalDate.now();
		Client client =ibanqueService.getClientById(1);
		Compte cptdeb =ibanqueService.getCompteById(1);
		Compte cptcred=ibanqueService.getCompteById(2);


      Virement virement= new Virement();
      virement.setTypeVirement("P");
      virement.setClient(client);
      virement.setCompteByCompteCrediteur(cptcred);
      virement.setCompteByCompteDebiteur(cptdeb);
      virement.setDateCreation(dtf.format(localDate));
      virement.setDateEcheance("30/12/2018");
      ibanqueService.saveOrUpdate(virement);
      Operation operationDeb = new Operation();
      operationDeb.setCompte(cptdeb);
      operationDeb.setMontant(1000);
      operationDeb.setNumOperation(1);
      operationDeb.setDateOperation(new Date(2018, 11, 10));
     ibanqueService.ajouterVirement( cptdeb.getIdcompte(),new Date("30/12/2018") , cptcred.getIdcompte(), 1000,"P",cptdeb,cptcred);


	
	}

}
