package com.bca.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bca.dto.ErrorMessage;
import com.bca.dto.LoginForm;
import com.bca.entity.User;
import com.bca.services.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private HttpSession session;

	@GetMapping
	public String login(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "login";
	}

	@PostMapping
	public String prosesLogin(LoginForm loginForm, Model model) {
		try {
			User user = userService.login(loginForm.getEmail(), loginForm.getPassword());
			if(user != null) {
				session.setAttribute("CURRENT_USER", user);
				return "redirect:/";
			}else {
				ErrorMessage msg = new ErrorMessage();
				msg.getMessages().add("Invalid email or password");
				model.addAttribute("ERROR", msg);
				model.addAttribute("loginForm", loginForm);
				return "login";
			}
		}catch(Exception ex) {
			ErrorMessage msg = new ErrorMessage();
			msg.getMessages().add("Error: "+ ex.getMessage());
			model.addAttribute("ERROR", msg);
			model.addAttribute("loginForm", loginForm);
			return "login";
		}
	}

}
