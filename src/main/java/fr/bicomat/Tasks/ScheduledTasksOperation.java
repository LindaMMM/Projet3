package fr.bicomat.Tasks;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import fr.bicomat.Auth.service.EmailService;
import fr.bicomat.Service.ClientService;
import fr.bicomat.Service.CompteService;
import fr.bicomat.Service.ReportService;
import fr.bicomat.config.CompteException;
import fr.bicomat.entities.Alerte;
import fr.bicomat.entities.Compte;
import fr.bicomat.entities.CompteClient;
import fr.bicomat.entities.Prelevement;
import fr.bicomat.entities.TypeEnvoi;
import fr.bicomat.entities.TypeVirement;
import fr.bicomat.entities.Virement;


@Component
public class ScheduledTasksOperation {

	@Autowired
	CompteService compteService;

	@Autowired
	ClientService clientService;

	@Autowired 
	EmailService emailService;

	/**
	 * Instance de log pour la gestion des tâches. 
	 */
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasksOperation.class);

	// @Scheduled(fixedRate = 5000)
	@Scheduled(cron="0 0 00 * * *")
	public void OperationPeriodique() throws ParseException {

		log.debug("Lancement de la tâche Journalinière");
		Date date = new Date();
		lancementVirement(date);
		lancementPrelevement(date);
		alerte(date);
	}
	/**
	 * Traitement des lancements de virements.
	 * @param date Date à prendre en compte
	 */
	private void lancementVirement(Date date) {
		log.debug("Lancement des virements");
		Set<Virement> listvirement = compteService.GetVirementApplicable(date); 
		Iterator<Virement> it = listvirement.iterator();
		while(it.hasNext()){

			Virement v = it.next();
			try{
				compteService.saveOperation(v);

			}catch(IllegalArgumentException ex) {
				log.error(ex.getMessage());
			}
			catch(Exception ex) {
				log.error(ex.getMessage());					
			}

			try{
				// Calcul de la prochaine échéance
				Date dnext = nextvirement(v);
				if (dnext != null )
				{
					v.setDateCreation(dnext);
					compteService.saveVirement(v);
				}

			}catch(IllegalArgumentException ex) {
				log.error(ex.getMessage(),ex);
			}
			catch(Exception ex) {
				log.error(ex.getMessage(),ex);					
			}
		}

	}

	/**
	 * Calcul du prochain virement.
	 * @param v Virement.
	 * @return Null si pas de prochain virement.
	 */
	private Date nextvirement(Virement v) {
		TypeVirement type = TypeVirement.fromString(v.getTypeVirement());
		if (type.getFrequence()!=0) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(v.getDateCreation());   
			cal.add(Calendar.MONTH, type.getFrequence());
			if (v.getDateFinPrelevement().before(cal.getTime()))
			{
				return cal.getTime();
			}
		}
		return null;
	}

	/**
	 * Calcul du prochain Prélévement.
	 * @param p Prélévement.
	 * @return Null si pas de prochain prélévement.
	 */
	private Date nextPrelevement(Prelevement p) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(p.getDateCreation());   
		cal.add(Calendar.MONTH, 1);
		return cal.getTime();
	}

	/**
	 * Lancement des prélévements  
	 * @param date Dat d'application du prélévements.
	 */
	private void lancementPrelevement(Date date) {
		log.debug("Lancement des Alertes");
		Set<Prelevement> listPrelevement = compteService.GetPrelevementApplicable(date);
		Iterator<Prelevement> it = listPrelevement.iterator();
		while(it.hasNext()){

			Prelevement p = it.next();
			try{
				compteService.saveOperation(p);

			}catch(IllegalArgumentException ex) {
				log.error(ex.getMessage(), ex);
			}
			catch(Exception ex) {
				log.error(ex.getMessage(),ex);					
			}

			try{
				// Calcul de la prochaine échéance
				Date dnext = nextPrelevement(p);
				if (dnext != null )
				{
					p.setDateCreation(dnext);
					compteService.savePrelevement(p);
				}

			}catch(IllegalArgumentException ex) {
				log.error(ex.getMessage(),ex);
			}
			catch(Exception ex) {
				log.error(ex.getMessage(),ex);					
			}
		}
	}

	/**
	 * Envoi des Alertes à la date du jour. 
	 * @param date date de traitement.
	 */
	private void alerte(Date date) {
		log.debug("Lancement des Alertes");
		Set<Alerte> listAlertes = clientService.GetAlertetApplicable(date);

		Iterator<Alerte> it = listAlertes.iterator();
		while(it.hasNext()){

			Alerte a = it.next();
			try{
				if(a.getTypeEnvoi().equals(TypeEnvoi.EMAIL.getType()))
				{
					emailService.sendAlerte(a);
				}
				else
				{
					log.debug("Pas de service de SMS");
				}

			}catch(IllegalArgumentException ex) {
				log.error(ex.getMessage());
			}
			catch(Exception ex) {
				log.error(ex.getMessage());					
			}
		}

	}

}