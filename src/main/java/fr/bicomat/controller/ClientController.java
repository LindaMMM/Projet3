package fr.bicomat.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.bicomat.dao.AlerteRepository;
import fr.bicomat.dao.ClientRepository;
import fr.bicomat.dao.ConseillerRepository;
import fr.bicomat.entities.Alerte;
import fr.bicomat.entities.Client;
import fr.bicomat.entities.Conseiller;


@Controller
public class ClientController {
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private ConseillerRepository conseillerRepository;
	
	
/*	@RequestMapping(value="/saveInterne")
	public String saveInterne(Model model,
			@Valid  Interne i,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return "newinterne";
		clientRepository.save(i);
		
		return "redirect:/agent/";
		
	}
	
	@RequestMapping(value="/agent/saveTiers")
	public String save(Model model,
			@Valid Tiers t,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return "newtiers";
		clientRepository.save(t);
		
		return "redirect:/";
		
	}
	
	@RequestMapping(value="/agent/newinterne")
	public String forminterne(Model model) {
		model.addAttribute("conseiller", conseillerRepository.findAll());
		model.addAttribute("interne", new Interne());
		return "agent/newinterne";
	}
	
	@RequestMapping(value="/agent/newtiers")
	public String form(Model model) {
		model.addAttribute("tiers", new Tiers());
		return "agent/newtiers";
	}

*/
	
	
	
	
	
}
