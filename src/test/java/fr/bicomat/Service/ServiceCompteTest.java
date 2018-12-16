package fr.bicomat.Service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.bicomat.entities.Client;
import fr.bicomat.entities.Compte;
import fr.bicomat.entities.CompteClient;
import fr.bicomat.entities.InfoCompte;
import fr.bicomat.entities.Operation;
import fr.bicomat.entities.TypeCompte;
import fr.bicomat.entities.TypeOperation;
import fr.bicomat.entities.TypeVirement;
import fr.bicomat.entities.Virement;


@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional()
public class ServiceCompteTest {

	@Autowired
	private ServiceCompte compteService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private BanqueService banqueService;
	
	@Test
	public void testGetCompteClientById() {
	}

	@Test
	public void testSaveCompteClient() {
		// Creation d'un compte sans erreur
		Client client= clientService.getClientById(1);
		assertNotNull(client);		
		CompteClient cptClient = new CompteClient();
		cptClient.setClient(client);
		cptClient.setNumecompte(002);
		cptClient.setLibelle("TestCompte");
		cptClient.setTypeCompte(TypeCompte.LIV_A.getType());
		
		// client.getComptes().add(cptClient);
		
		compteService.saveCompteClient(cptClient);
		CompteClient cptClient2 = new CompteClient();
		cptClient2.setClient(client);
		cptClient2.setNumecompte(002);
		cptClient2.setLibelle("TestCompte");
		cptClient2.setTypeCompte(TypeCompte.COURANT.getType());
		compteService.saveCompteClient(cptClient2);
	}

	@Test
	public void testDeleteCompteClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCompteTierById() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveComptetTiers() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteCompteTiers() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetVirementById() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveVirement() {
		// Create Virement
		Client client= clientService.getClientById(1);
		
		CompteClient compteByCompteCrediteur = null;
		CompteClient compteByCompteDebiteur = null;
		// lecture des comptes 
		Iterator<Compte> it = client.getComptes().iterator();

	    while(it.hasNext()){

	    	Compte d = it.next();
	    	if (d.getIdcompte().equals(1))
	    	{
	    		compteByCompteCrediteur = (CompteClient) d;
	    	}

	    	if (d.getIdcompte().equals(2))
	    	{
	    		compteByCompteDebiteur = (CompteClient) d;
	    	}
	    }
		Date dateEcheance =  DateUtils.addDays(new Date(), 15);
		Virement virement = new Virement(client, compteByCompteCrediteur, compteByCompteDebiteur, new Date(), TypeVirement.PONCTUEL.getCode(), dateEcheance);
		
		compteService.saveVirement(virement);
		
	}

	@Test
	public void testDeleteVirement() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOperationById() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveOperation() {
		// Creation d'une operation
		Client client= clientService.getClientById(1);
		CompteClient compteByCompteCrediteur = null;
		CompteClient compteByCompteDebiteur = null;
		// lecture des comptes 
		Iterator<Compte> it = client.getComptes().iterator();

	    while(it.hasNext()){

	    	Compte d = it.next();
	    	if (d.getIdcompte().equals(1))
	    	{
	    		compteByCompteCrediteur = (CompteClient) d;
	    	}

	    	if (d.getIdcompte().equals(2))
	    	{
	    		compteByCompteDebiteur = (CompteClient) d;
	    	}
	    }
		
		
	//	CompteClient compteByCompteCrediteur = client.getComptes().iterator().next();
		//CompteClient compteByCompteCrediteur= compteService.getCompteClientById(1);
		Operation op = new Operation(compteByCompteCrediteur, 1, new Date(), 15.0,
				TypeOperation.CREDIT.getType());
		op.setLibelleOperation("Test Crédit");
		//op.setCompte(compteByCompteCrediteur);
		compteByCompteCrediteur.getOperations().add(op);
		///compteService.saveCompteClient(compteByCompteCrediteur);
		compteService.saveOperation(op);
		
		
		//CompteClient compteByCompteDebiteur= compteService.getCompteClientById(2);
		Operation op2 = new Operation(compteByCompteDebiteur, 1, new Date(), 15.0,
				TypeOperation.DEBIT.getType());
		op2.setLibelleOperation("Test Dédit");
		op.setCompte(compteByCompteDebiteur);
		// compteService.saveCompteClient(compteByCompteCrediteur);
		compteService.saveOperation(op2);
	}

	@Test
	public void testDeleteOperation() {
		fail("Not yet implemented");
	}

}
