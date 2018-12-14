package fr.bicomat.Auth.controller;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.bicomat.Utils;
import fr.bicomat.Auth.entities.UserQuestion;
import fr.bicomat.Auth.entities.User_App;
import fr.bicomat.Auth.entities.dtoChangedPassword;
import fr.bicomat.Auth.entities.dtoResetPassword;
import fr.bicomat.Auth.service.UserService;

@RestController
@RequestMapping("/api/users")
public class RestUserController {
	@Autowired
	private UserService userService;

	@PostMapping(value="/changePwd")
	public String changePwd(@RequestBody dtoChangedPassword response){

		userService.changePwd(response);
		return "Post Successfully!";
	}


	@GetMapping(value="/listQuestion")
	public List<UserQuestion> getListQUestion(){
		return userService.getAllQuestion();
	}

	@RequestMapping("/searchLogin{login}")
	public @ResponseBody String searchLogin(@RequestParam(value = "login") String login) throws JSONException{
		User_App userRes = null;
		if (Utils.isValidEmailAddress(login))
		{
			userRes = userService.getUserByEmail(login);
		}
		userRes = userService.getUserByssoId(login);
		if (userRes.getId()>0 && userRes.getUserQuestion() == null)
		{
			String jsonString = new JSONObject()
	                  .put("code", "1")
	                  .put("message", "Utilisateur trouvé")
	                  .put("user", new JSONObject()
	                       .put("ssoId", userRes.getSsoId())
	                       .put("roles", userRes.getUserProfiles())
	                       .put("question",userRes.getUserQuestion().getQuestion())).toString();

			return jsonString;
		}
		else
		{
			String jsonString = new JSONObject()
	                  .put("code", "0")
	                  .put("message", "demande non accepté")
	                  .toString();
			return jsonString;
		}
	}
	
	@PostMapping(value="/resetPwd")
	public String setResetPwd(@RequestBody dtoResetPassword response) throws JSONException{
		if (userService.resetPwd(response.getSsoId(),response.getNumCard(),response.getAnswer())) {
			String jsonString = new JSONObject()
	                  .put("code", "1")
	                  .put("message", "Utilisateur trouvé").toString();
			return jsonString;
		}
		else
		{
			String jsonString = new JSONObject()
	                  .put("code", "0")
	                  .put("message", "demande non accepté")
	                  .toString();
			return jsonString;
		}
	
	}
	
}
