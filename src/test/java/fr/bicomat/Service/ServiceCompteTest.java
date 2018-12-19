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
import fr.bicomat.entities.CompteTiers;
import fr.bicomat.entities.EtatCompte;
import fr.bicomat.entities.Operation;
import fr.bicomat.entities.TypeCompte;
import fr.bicomat.entities.TypeOperation;
import fr.bicomat.entities.TypeVirement;
import fr.bicomat.entities.Virement;


@RunWith(SpringRunner.class)
@SpringBootTest

public class ServiceCompteTest {

	@Autowired
	private CompteService compteService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private BanqueService banqueService;

	@Test
	public void testGetCompteClientById() {
		assertNotNull(compteService.getCompteClientById(1));
	}

	@Test
	public void testSaveCompteClient() {
		// Creation d'un compte sans erreur
		Client client= clientService.getClientById(1);
		assertNotNull(client);		
		CompteClient cptClient = new CompteClient();
		cptClient.setClient(client);
		cptClient.setNumecompte("02");
		cptClient.setSolde(10);
		cptClient.setLibelle("TestCompte");
		cptClient.setTypeCompte(TypeCompte.LIV_A.getType());	
		compteService.saveCompteClient(cptClient);

		CompteClient cptClient2 = new CompteClient();
		cptClient2.setClient(client);
		cptClient2.setNumecompte("002");
		cptClient2.setLibelle("TestCompte");
		cptClient2.setTypeCompte(TypeCompte.COURANT.getType());
		compteService.saveCompteClient(cptClient2);

		/* Avec Erreur doublon de compte d'epargne*/
		try {
			cptClient2 = new CompteClient();
			cptClient2.setClient(client);
			cptClient2.setNumecompte("003");
			cptClient2.setSolde(15);
			cptClient2.setLibelle("CompteDoublon");
			cptClient2.setTypeCompte(TypeCompte.LIV_A.getType());
			compteService.saveCompteClient(cptClient2);
			fail("On aurai du avoir une exeption sur la création du compte");

		}catch(IllegalArgumentException ex) {
			assertTrue(ex.getMessage().contains("2 comptes du"));
		}

		/* Avec Erreur compte d'epargne sans solde*/
		try {
			cptClient2 = new CompteClient();
			cptClient2.setClient(client);
			cptClient2.setNumecompte("004");
			cptClient2.setLibelle("CompteDoublon");
			cptClient2.setTypeCompte(TypeCompte.LIL.getType());
			compteService.saveCompteClient(cptClient2);
			fail("On aurai du avoir une exeption sur la création du compte");


		}catch(IllegalArgumentException ex) {
			assertTrue(ex.getMessage().contains("solde ne correspond "));
		}

		/* Avec Erreur compte d'epargne avec solde supérieur au valeur autorisé*/
		try {
			cptClient2 = new CompteClient();
			cptClient2.setClient(client);
			cptClient2.setNumecompte("004");
			cptClient2.setSolde(25000);
			cptClient2.setLibelle("CompteDoublon");
			cptClient2.setTypeCompte(TypeCompte.LDD.getType());
			compteService.saveCompteClient(cptClient2);
			fail("On aurai du avoir une exeption sur la création du compte");
		}catch(IllegalArgumentException ex) {
			assertTrue(ex.getMessage().contains("solde ne correspond "));
		}
	}


	@Test
	public void testDemandeClotureCompteClient() {
		// Cloture avec opération d'intéret
		CompteClient cptClient = compteService.getCompteClientById(1);
		String etatcompte = cptClient.getEtatCompte(); 
		compteService.DmdClotureCompteClient(1);
		cptClient = compteService.getCompteClientById(1);
		assertTrue(cptClient.getEtatCompte().equals(EtatCompte.DEMANDE_FERMETURE.getEtat()));
		assertFalse(cptClient.getEtatCompte().equals(etatcompte));
	}
	
	@Test
	public void testClotureCompteClient() {
		// Cloture avec opération d'intéret
		CompteClient cptClient = compteService.getCompteClientById(1);
		Double soldeIntial = cptClient.getSolde();
		compteService.ClotureCompteClient(cptClient.getIdcompte());
		cptClient = compteService.getCompteClientById(1);
		assertFalse(cptClient.getSolde() == soldeIntial);
		
		// Cloture sans opération d'intérêt
		cptClient =compteService.getCompteClientById(3);
		soldeIntial = cptClient.getSolde();
		compteService.ClotureCompteClient(cptClient.getIdcompte());
		cptClient = compteService.getCompteClientById(1);
		assertTrue(cptClient.getSolde()==soldeIntial);
	}
	
	@Test
	public void testGetCompteTierById() {
		assertNotNull(compteService.getCompteTierById(2));
	}

	@Test
	public void testSaveComptetTiers() {
		// Cloture sans opération d'intérêt
		CompteTiers cptTier = new CompteTiers();
		cptTier.setLibelle("Test_Compte");
		cptTier.setNumecompte("000014213213");
		compteService.saveComptetTiers(cptTier);
	}

	@Test
	public void testDeleteCompteTiers() {
		assertTrue(compteService.deleteCompteTiers(2));
	}

	@Test
	public void testGetVirementById() {
		assertNotNull(compteService.getVirementById(1l));
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


		Operation op = new Operation(compteByCompteCrediteur, 1, new Date(), 15.0,
				TypeOperation.CREDIT.getType());
		op.setLibelleOperation("Test Crédit");
		compteByCompteCrediteur.getOperations().add(op);
		compteService.saveOperation(op);


		Operation op2 = new Operation(compteByCompteDebiteur, 1, new Date(), 15.0,
				TypeOperation.DEBIT.getType());
		op2.setLibelleOperation("Test Dédit");
		op.setCompte(compteByCompteDebiteur);
		compteService.saveOperation(op2);
	}

	@Test
	public void testDeleteOperation() {
		assertTrue(compteService.deleteOperation(1l));
	}

}
