package fr.bicomat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.bicomat.dao.AlerteRepository;
import fr.bicomat.dao.ClientRepository;
import fr.bicomat.entities.Alerte;

@RestController
@RequestMapping("/api/client")
public class RestClientController {
	@Autowired
	private AlerteRepository AlerteRepository;
	
	
	@RequestMapping(value = "/listalerte", method = RequestMethod.GET)
	Page<Alerte> employeesPageable( Pageable pageable) {
		return AlerteRepository.findAll(pageable);
	}

}
