package fr.bicomat.dao;

import static org.assertj.core.api.Assertions.registerCustomDateFormat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.bicomat.Service.IBanqueService;
import fr.bicomat.Tasks.ScheduledTasksOperation;
import fr.bicomat.config.CompteException;
import fr.bicomat.entities.Client;
import fr.bicomat.entities.Compte;
import fr.bicomat.entities.Operation;
import fr.bicomat.entities.OperationId;
import fr.bicomat.entities.OperationTemp;
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
	public void test() throws CompteException, Exception {
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
      
      //Vérifie que le virement est créé
        assertNotNull(virement.getIdvirement());
        System.out.println("Quantity: " + cptdeb.getIdcompte());
     //  ibanqueService.loadOperationTemp(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2018-12-15 10:00"), 1000, "P", cptdeb.getIdcompte(),virement.getIdvirement(),cptcred.getIdcompte());
   
      // ScheduledTasksOperation  schedo= new ScheduledTasksOperation();
       //schedo.OperationPeriodique();
   
       
     // new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2018-12-15 10:00"));
//ibanqueService.getOperationEchue(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2018-12-15 10:00"));

/*for(OperationTemp op : ibanqueService.getOperationEchue(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2018-12-15 10:00")) ) {
System.out.println("Id: " +  op.getIdop());
System.out.println("Name: " + op.getNumoperation());
System.out.println("Price: " + op.getDateechance());
System.out.println("Quantity: " + op.getDateechance());

System.out.println("================================");
}
*/
	}


	


}
