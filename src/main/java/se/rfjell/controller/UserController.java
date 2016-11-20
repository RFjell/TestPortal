package se.rfjell.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import se.rfjell.model.User;
import se.rfjell.service.UserService;;

@Controller
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("/signup")
	public String getSignupPage(Model model){
		model.addAttribute("user", new User());
		return "signup";
	}

	@PostMapping("/signup")
	public String signup(@Valid User user, BindingResult result, RedirectAttributes redirAttr){
		if(result.hasErrors()) {
			return "signup";
		}

		if( userService.save(user) == null) {
			result.reject("email", "Email already in use.");
			return "signup";
		}

		redirAttr.addFlashAttribute("message", "User added. Check your email to finish account creation.");

		return "redirect:index";
	}

	@GetMapping("/edituserinfo")
	public String getUpdateUserPage(HttpSession session, Model model){
		User user = getLoggedInUser(session);
		model.addAttribute("user", user);

		return "edituserinfo";
	}

	@PostMapping("/edituserinfo")
	public String updateUser(@Valid User newUserInfo, BindingResult result, 
								@RequestParam String currentPassword, HttpSession session, 
								RedirectAttributes redirAttr, Model model){
		if(result.hasErrors()) {
			return "edituserinfo";
		}

		User user = getLoggedInUser(session);

		if(userService.update(user, newUserInfo, currentPassword) == null) {
			model.addAttribute("error", "Wrong password! Info not updated.");
			return "edituserinfo";
		}

		redirAttr.addFlashAttribute("message", "User info updated");
		return "redirect:index";

	}

	@GetMapping("/user/{id}")
	public @ResponseBody User getUserById(@PathVariable Long id){
		return userService.findById(id);
	}
	
	private User getLoggedInUser(HttpSession session) {
		SecurityContextImpl sci = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
		String username = sci.getAuthentication().getName();

		User user = userService.findByUsername(username);
		return user;
	}
}
