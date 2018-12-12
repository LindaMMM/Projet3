package fr.bicomat.Tasks;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import fr.bicomat.Service.IBanqueService;
import fr.bicomat.config.CompteException;
import fr.bicomat.entities.Compte;
import fr.bicomat.entities.OperationTemp;

@Component

public class ScheduledTasksOperation {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasksOperation.class);
   
	@Autowired
	IBanqueService ibanqueService;
   // @Scheduled(fixedRate = 5000)
	  @Scheduled(cron="0 0 00 * * *")
      public void OperationPeriodique() throws ParseException {
    
    	
			//dates = new SimpleDateFormat("yyyy-MM-dd").parse("2018-12-15");
		
			Date date = new Date();
	        String strDateFormat = "yyyy-MM-dd";
	        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	        String  formattedDate= dateFormat.format(date);
	        Date dates = new SimpleDateFormat("yyyy-MM-dd").parse(formattedDate);
	        
		
				for(OperationTemp op :  ibanqueService.getOperationEchue(dates) ) {
				     if (op.getDateechance()==dates) {
				    	 Compte cptdeb =ibanqueService.getCompteById(op.getIdcomptedeb());
				 		Compte cptcred=ibanqueService.getCompteById(op.getIdcomptecred());
				 		
				    	 try {
							ibanqueService.ajouterVirement(op.getIdcomptedeb(), dates, op.getIdcomptecred(), op.getMontant(),
									 op.getTypeoperation(), cptdeb, cptcred,op.getIdop());
							
						} catch (CompteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    	 
				     }
					}
		 
     
    }
}