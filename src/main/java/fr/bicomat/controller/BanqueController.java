package fr.bicomat.controller;



import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.bicomat.dao.BanqueRepository;
import fr.bicomat.entities.Banque;
import fr.bicomat.entities.CompteTiers;

import fr.bicomat.entities.Virement;

@Controller
public class BanqueController {
	@Autowired
	private BanqueRepository banqueRepository;
	@RequestMapping(value="/newbanque")
	public String form(Model model) {
		model.addAttribute("banque", new Banque());
		return "newbanque";
	}
	


	
	@RequestMapping("/homes")
    public String opera() throws Exception {
		Date dates =new SimpleDateFormat("yyyy-MM-dd").parse("2018-12-15");
		System.out.println(dates);
		System.out.println(" test ================================");
		/*for(OperationTemp op :  ibanqueService.getOperationEchue(dates) ) {
			System.out.println(op.getDateechance());
			System.out.println("================================");
			}
 */
        return "homes";
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
