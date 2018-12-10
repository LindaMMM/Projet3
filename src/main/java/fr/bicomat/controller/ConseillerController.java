package fr.bicomat.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.bicomat.dao.ConseillerRepository;
import fr.bicomat.entities.Conseiller;



@Controller
public class ConseillerController {
	@Autowired
	private ConseillerRepository conseillerRepository;
	@RequestMapping(value="/agent/newconseiller")
	public String form(Model model) {
		model.addAttribute("conseiller", new  Conseiller());
		return "agent/newconseiller";
	}
	
	
	@RequestMapping(value="/agent/saveConseiller")
	public String save(Model model,
			@Valid Conseiller C,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return "Conseiller";
		conseillerRepository.save(C);
		
		return "redirect:/agent/";
	}
	
}
