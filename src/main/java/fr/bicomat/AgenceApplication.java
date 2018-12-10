package fr.bicomat;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"fr.bicomat.dao","fr.bicomat.Auth.dao"})
@SpringBootApplication
public class AgenceApplication {

private static final Logger log = LoggerFactory.getLogger(AgenceApplication.class);
	
	

	public static void main(String[] args) {
		SpringApplication.run(AgenceApplication.class, args);
	}

	/*@Override
	public void run(String... args) throws Exception {
		
				
				//====================New Conseiller====================================
				
				//Conseiller c1=conseillerRepository.save(new Conseiller("NDIAYE","Moussa")) ;
				Conseiller c2=conseillerRepository.save(new Conseiller("DIOP", "Moussa")) ;
				
				//====================New Client Interne ====================================
				clientRepository.save(new Interne("BA", "Galadio", "gba@i.sn", "gba"," motpass",1999, "000910HS", "SGBS ROUM","774502940",c1));
				clientRepository.save(new Interne("fall", "Galadio", "gfall@i.sn", "gfall"," motpass",1999, "000910HS", "SGBS ROUM","774502940",c1));
		  		
				//====================New Client Tiers Link New Banque ====================================
				Tiers  tiers = new Tiers("SARR","Mouhamad","s.d@el.sn");
		        Tiers  tiers2 = new Tiers("DIENG","Laye","aadieng@elton.sn");
		        
		        Banque banque1=  new Banque("SGBS ROUME");
		        Banque banque2=  new Banque("BICIS ROUME");
		       
        tiers.getBanques().add(banque2);
        tiers.getBanques().add(banque1);
        tiers2.getBanques().add(banque2);
        banque1.getClients().add(tiers);
        banque2.getClients().add(tiers);
        banque2.getClients().add(tiers2);
        banqueRepository.save(banque1);
        banqueRepository.save(banque2);
        clientRepository.save(tiers);
        clientRepository.save(tiers2);
        
        //====================Link existing Client Tiers to Existing Banque ====================================
         
        Optional<Banque> Cherchebanque= banqueRepository.findById((long) 1);
        Optional<Client> ChercheClient= clientRepository.findById((long) 4);
        Banque bq=Cherchebanque.get();
        Client tiers1=ChercheClient.get();
        tiers1.getBanques().add(bq);
        bq.getClients().add(tiers1);
        clientRepository.save(tiers1);

      tiers1.removeBanque(bq);//ne marche pas
      																				
	}*/
	
	
}
