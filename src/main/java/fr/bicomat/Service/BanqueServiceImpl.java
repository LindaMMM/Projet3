package fr.bicomat.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.bicomat.Auth.entities.User_App;
import fr.bicomat.Auth.service.UserService;
import fr.bicomat.dao.BanqueRepository;
import fr.bicomat.dao.CarteBancaireRepository;
import fr.bicomat.dao.ClientRepository;
import fr.bicomat.dao.ConseillerRepository;
import fr.bicomat.dao.InfoCompteRepository;
import fr.bicomat.entities.Banque;
import fr.bicomat.entities.CarteBancaire;
import fr.bicomat.entities.Client;
import fr.bicomat.entities.Conseiller;
import fr.bicomat.entities.InfoCompte;

@Service
@Transactional(rollbackOn = {Exception.class})
public class BanqueServiceImpl implements  BanqueService{

	@Autowired
	BanqueRepository banqueRepository;
	
	@Autowired
	ConseillerRepository conseillerRepository;
	
	@Autowired
	InfoCompteRepository infoCompteRepository;
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	UserService serviceUser;
	@Autowired
	CarteBancaireRepository carteBancaireRepository;
	
	
	@Override
	public Banque getBanqueById(Integer id) {
		return banqueRepository.getOne(id);
	}

	@Override
	public Banque saveBanque(Banque bank) {
		return banqueRepository.saveAndFlush(bank);
	}

	@Override
	public boolean deleteBank(Integer id) {
		banqueRepository.deleteById(id);
		return true;
	}

	@Override
	public Conseiller getConseillerById(Integer id) {
		return conseillerRepository.getOne(id);
	}

	@Override
	public Conseiller saveConseillier(Conseiller conseiller) {
		return conseillerRepository.saveAndFlush(conseiller);
	}

	@Override
	public boolean deleteConseillier(Integer id) {
		conseillerRepository.deleteById(id);
		return true;
	}

	@Override
	public User_App AjouterCompteAgencyConseillier(Conseiller conseillier,  String email, String login) throws IllegalArgumentException {
		return serviceUser.addUserConseiller(conseillier,email, login);
	}

	@Override
	public InfoCompte getInfoCompteById(Integer id) {
		return infoCompteRepository.getOne(id);
	}

	@Override
	public InfoCompte saveInfoCompte(InfoCompte infoCompte) {
		return infoCompteRepository.saveAndFlush(infoCompte);
	}

	@Override
	public boolean deleteInfoCompte(Integer id) {
		infoCompteRepository.deleteById(id);
		return true;
	}
	/*
	 * ADIENG
	 * 
	 */
	@Override
	public List<Client> getClientByNom(String nom) {
		return (List<Client>) clientRepository.chercherByNom(nom);
	}
	@Override
	public CarteBancaire getCarteNumcarte (String numcarte) {
		return carteBancaireRepository.findByNumcarte(numcarte);
	}
	@Override
	public List<Client> getClientByCarte(CarteBancaire carte) {
		return clientRepository.findByCarteBancaires(carte);
	}
	@Override
	public CarteBancaire getCarteByClient(Client client) {
		return carteBancaireRepository.findByClient(client);
	}
}
