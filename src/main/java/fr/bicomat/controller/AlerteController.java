package fr.bicomat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.bicomat.dao.AlerteRepository;
import fr.bicomat.entities.Alerte;

@Controller
public class AlerteController {
	
	@Autowired
	private AlerteRepository alerteRepository;
	
	@RequestMapping("/client/alertes")
	  public String getalerte(Pageable pageable,
	                             Model model) {
	      Page<Alerte> page = alerteRepository.findAll(pageable);
	      model.addAttribute("page", page);
	      return "client/alertes";
	  }

	@RequestMapping(value = "/client/alerte/{id}", method = RequestMethod.GET)
	public String showUser(@PathVariable Integer id, Model model) {
		model.addAttribute("alerte", alerteRepository.getOne(id));
		return "client/alertevue";
	}

	@RequestMapping(value = "/admin/alerte/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id, Model model) {
		model.addAttribute("user", alerteRepository.getOne(id));
		return "client/alerteform";
	}

	@RequestMapping(value = "/admin/alerte/new", method = RequestMethod.GET)
	public String newUser(Model model) {
		model.addAttribute("alerte", new Alerte());
		return "client/alerteform";
	}
}
