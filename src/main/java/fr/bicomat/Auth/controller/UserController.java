package fr.bicomat.Auth.controller;



import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.bicomat.Auth.entities.User_App;
import fr.bicomat.Auth.service.UserService;
import fr.bicomat.ExcelDownload.ExportClientToExcel;
import fr.bicomat.Service.BanqueService;
import fr.bicomat.entities.CarteBancaire;
import fr.bicomat.entities.Client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private BanqueService banqueservice;
	
	
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "client/index";
	}

	@RequestMapping(value = "/agent", method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "agent/index";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String dbaPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "admin/index";
	}

	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "accessDenied";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
	@RequestMapping(value = "/changedpassword", method = RequestMethod.GET)
	public String changedPassword( Model model) {
		model.addAttribute("user",userService.getUserByssoId(getPrincipal()));
		return "changedpassword";
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public String resetPassword( Model model) {
		model.addAttribute("user",userService.getUserByssoId(getPrincipal()));
		return "resetPassword";
	}
	
/*	@RequestMapping(value = "/agent/searchClient", method = RequestMethod.GET)
	public String getGestCompteAgent(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "agent/searchClient";
	}
*/
		
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin/users", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("users", userService.listAllUsers());
		return "admin/users";
	}

	@RequestMapping(value = "/admin/user/{id}", method = RequestMethod.GET)
	public String showUser(@PathVariable Integer id, Model model) {
		model.addAttribute("user", userService.getUserById(id));
		return "admin/usershow";
	}

	@RequestMapping(value = "/admin/user/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id, Model model) {
		model.addAttribute("user", userService.getUserById(id));
		return "admin/userform";
	}

	@RequestMapping(value = "/admin/new", method = RequestMethod.GET)
	public String newUser(Model model) {
		model.addAttribute("user", new User_App());
		return "admin/userform";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public String saveProduct(User_App user) {
		userService.saveUser(user);
		return "redirect:/admin/user/" + user.getId();
	}

	@RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Integer id) {
		userService.deleteUser(id);
		return "redirect:/admin/users";
	}
	
	/*
	 * ADIENG
	 * 
	 */


	/*//@RequestMapping( "/download", method = RequestMethod.GET)
	 public ResponseEntity<InputStreamResource> excelClienReport (  
		@RequestParam("reponse") String reponse,
		
	 @RequestParam (name="chercher") String chercher){
		 System.out.println("======reponses" + reponse);
		   try {
				
			
				if (reponse.equals("yes")) {
					CarteBancaire cb=banqueservice.getCarteNumcarte(chercher);
					 List<Client> clients = (List<Client>) banqueservice.getClientByCarte(cb) ;
					 ByteArrayInputStream in = ExportClientToExcel.clientsToExcel(clients);
					
						HttpHeaders headers = new HttpHeaders();
					    headers.add("Content-Disposition", "attachment; filename=clients.xlsx");	
				

						 return ResponseEntity
					                .ok()
					                .headers(headers)
					                .body(new InputStreamResource(in));
				}
				if (reponse.equals("no")) {
					
					 List<Client> clients = (List<Client>) banqueservice.getClientByNom(chercher) ;
					
					 ByteArrayInputStream in = ExportClientToExcel.clientsToExcel(clients);
						
						HttpHeaders headers = new HttpHeaders();
					    headers.add("Content-Disposition", "attachment; filename=clients.xlsx");	
				

						 return ResponseEntity
					                .ok()
					                .headers(headers)
					                .body(new InputStreamResource(in));
				
			    
			
				}
		} catch (Exception e) {
			 System.out.println(e );
		}
		   return null; 
			

		
    }*/
	@RequestMapping(value = "/agent/searchClient", method = RequestMethod.GET)
	public String getGestCompteAgent(ModelMap model,String reponse,String chercher ) {
	  
	   try {
		
			if (reponse.equals("yes")) {
				
				CarteBancaire cb=banqueservice.getCarteNumcarte(chercher);
				//model.addAttribute("carte",banqueservice.getCarteNumcarte(chercher));
				model.addAttribute("listClient",banqueservice.getClientByCarte(cb));
				
			
			}
			if (reponse.equals("no")) {
				model.addAttribute("listClient",banqueservice.getClientByNom(chercher) );
		
			}
	} catch (Exception e) {
		 System.out.println(e );
	}
	

		return "agent/searchClient";
	}

}
