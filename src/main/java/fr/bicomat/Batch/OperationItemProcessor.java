package fr.bicomat.Batch;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import fr.bicomat.Service.IBanqueService;
import fr.bicomat.Service.IBanqueServiceImpl;
import fr.bicomat.config.CompteException;
import fr.bicomat.entities.Operation;
import fr.bicomat.entities.Virement;

/*public class OperationItemProcessor implements  ItemProcessor<Operation, Operation> {

    private static final Logger log = LoggerFactory.getLogger(OperationItemProcessor.class);

    @Override
    public Operation process(final Operation operation) throws Exception {
    	Virement virement= new Virement();
 
        Date date = new Date ();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
        String strDate = dateFormat.format(date);  
     
    	if (virement.getDateEcheance()== strDate) {
    		
     
        }
    	final IBanqueServiceImpl transformedPerson = new IBanqueServiceImpl();


      return
    }

}
*/