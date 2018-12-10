package fr.bicomat.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.bicomat.dao.BanqueRepository;
import fr.bicomat.entities.Banque;
import fr.bicomat.entities.CompteTiers;

@Controller
public class BanqueController {
	@Autowired
	private BanqueRepository banqueRepository;
	@RequestMapping(value="/newbanque")
	public String form(Model model) {
		model.addAttribute("banque", new Banque());
		return "newbanque";
	}

	@RequestMapping(value="/saveBanque")
	public String savebanque(Model model,
			@Valid Banque b,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return "newbanque";
		banqueRepository.save(b);
		return "redirect:/";
		
	}
}
