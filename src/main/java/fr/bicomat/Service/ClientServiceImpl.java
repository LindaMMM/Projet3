package fr.bicomat.Service;

import java.util.Date;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.bicomat.Auth.entities.User_App;
import fr.bicomat.Auth.service.UserService;
import fr.bicomat.dao.AlerteRepository;
import fr.bicomat.dao.CarteBancaireRepository;
import fr.bicomat.dao.ClientRepository;
import fr.bicomat.entities.Alerte;
import fr.bicomat.entities.CarteBancaire;
import fr.bicomat.entities.Client;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	CarteBancaireRepository carteRepository;
	
	@Autowired
	AlerteRepository alerteRepository;
	
	@Autowired
	UserService serviceUser;
	
	/**
	 * Date du jour.
	 */
	private Date dateNow = new Date();
	
	@Override
	public boolean checkClient(Integer idClient, String numCard) {
		CarteBancaire cart =  carteRepository.findByNumcarte(numCard);
		
		return cart.getClient().getIdclient().equals(idClient);
	}

	@Override
	public boolean checkClient(String nomClient, String numCard) {
		CarteBancaire cart =  carteRepository.findByNumcarte(numCard);
		
		return cart.getClient().getNomClient().equals(nomClient);
	}

	@Override
	public Client getClientById(Integer id) {
		
		return clientRepository.getOne(id);
	}

	@Override
	public Client saveClient(Client client) {
		if (client.getIdclient()==null)
		{
			// Nouveau Client. 
			client.setAnneeArrive(new Date());
		}
		
		return clientRepository.saveAndFlush(client);
	}

	@Override
	public boolean deleteClient(Integer id) {
		clientRepository.deleteById(id);
		return true;
	}

	@Override
	public User_App AjouterCompteAgencyClient(Client client, String login) throws IllegalArgumentException {
		if (client.getNumagency().isEmpty()||client.getIdclient() == null) {
			throw new IllegalArgumentException("le client n'a pas souscript l'option agency");
		}
		
		return serviceUser.addUserClient(client,login);
	}

	@Override
	public Alerte getAlerteById(Integer id) {
		return alerteRepository.getOne(id);
	}

	@Override
	public Alerte saveAlerte(Alerte alerte) throws IllegalArgumentException {
		if (alerte.getIdalerte() == null)
		{
			// Nouvelle alerte 
			// vérification de la date
			if(alerte.getDateAlerte().compareTo(dateNow) <= 0)
			{
				throw new IllegalArgumentException("La date doit être supérieur à la date du jour");
			}
		}
		return alerteRepository.saveAndFlush(alerte);
	}

	@Override
	public boolean deleteAlerte(Integer id) {
		alerteRepository.deleteById(id);
		return true;	
	}

	@Override
	public CarteBancaire getCarteBancaireById(Integer id) {
		
		return carteRepository.getOne(id);
	}

	@Override
	public CarteBancaire saveCarteBancaire(CarteBancaire carte) {
		if (carte.getIdCarte() == null)
		{
			if (carteRepository.findByNumcarte(carte.getNumcarte()) != null)
			{
				throw new IllegalArgumentException("cette carte est déjà référencée");
			}
		}
		
		return carteRepository.saveAndFlush(carte);
	}

	@Override
	public boolean deleteCarteBancaire(Integer id) {
		carteRepository.deleteById(id);
		return true;
	}
	
	@Override
	public Set<Alerte> GetAlertetApplicable(Date date) {
		return alerteRepository.findByDateAlerte(date);
	}
}
