package fr.bicomat.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.bicomat.Service.BanqueService;
import fr.bicomat.dao.AlerteRepository;

import fr.bicomat.entities.Alerte;
import fr.bicomat.entities.CarteBancaire;
import fr.bicomat.entities.Client;

@RestController
@RequestMapping("/api/client")
public class RestClientController {
	@Autowired
	private AlerteRepository AlerteRepository;
	
	@Autowired
	private BanqueService banqueservice;
	
	@RequestMapping(value = "/listalerte", method = RequestMethod.GET)
	Page<Alerte> employeesPageable( Pageable pageable) {
		return AlerteRepository.findAll(pageable);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public List<Client> getGestCompteAgent(ModelMap model,String reponse,String chercher ) {

		try {

			if (reponse.equals("yes")) {
				CarteBancaire cb=banqueservice.getCarteNumcarte(chercher);	
				return banqueservice.getClientByCarte(cb);

			}
			if (reponse.equals("no")) {
				return banqueservice.getClientByNom(chercher);
				
			}
		} catch (Exception e) {
			System.out.println(e );
		}


		return null;
	}

}
