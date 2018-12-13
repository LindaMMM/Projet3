package fr.bicomat.Auth.controller;

import java.util.List;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.bicomat.Auth.entities.User_App;
import fr.bicomat.Auth.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
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
	
   
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/pages/users", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("users", userService.listAllUsers());
		return "pages/users";
	}

	@RequestMapping(value = "/pages/user/{id}", method = RequestMethod.GET)
	public String showUser(@PathVariable Integer id, Model model) {
		model.addAttribute("user", userService.getUserById(id));
		return "pages/usershow";
	}

	@RequestMapping(value = "/pages/user/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id, Model model) {
		model.addAttribute("user", userService.getUserById(id));
		return "pages/userform";
	}

	@RequestMapping(value = "/user/new", method = RequestMethod.GET)
	public String newUser(Model model) {
		model.addAttribute("user", new User_App());
		return "pages/userform";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String saveProduct(User_App user) {
		userService.saveUser(user);
		return "redirect:/pages/user/" + user.getId();
	}

	@RequestMapping(value = "/user/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Integer id) {
		userService.deleteUser(id);
		return "redirect:/pages/users";
	}
}
