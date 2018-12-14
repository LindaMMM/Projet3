package fr.bicomat.Service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.bicomat.dao.CarteBancaireRepository;
import fr.bicomat.dao.ClientRepository;
import fr.bicomat.entities.CarteBancaire;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	CarteBancaireRepository carteRepository;
	
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

}
