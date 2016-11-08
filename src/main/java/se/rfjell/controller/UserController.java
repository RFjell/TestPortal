package se.rfjell.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import se.rfjell.config.MyUserDetailsService;
import se.rfjell.model.User;
import se.rfjell.service.UserService;;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	MyUserDetailsService userDetailsService;

	@GetMapping("/addUser")
	public String getAddUserPage(Model model){
		model.addAttribute("user", new User());
		return "addUser";
	}

	@PostMapping("/addUser")
	public String addUser(@Valid User user, BindingResult result, RedirectAttributes redirAttr){
		if(result.hasErrors()) {
			return "addUser";
		}

		userService.save(user);
		redirAttr.addFlashAttribute("message", "User added");

		//Auto sign in newly created user
		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
		Authentication auth = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		return "redirect:index";

	}

	@GetMapping("/updateUser")
	public String getUpdateUserPage(HttpSession session, Model model){
		User user = getLoggedInUser(session);
		model.addAttribute("user", user);

		return "updateUser";
	}

	@PostMapping("/updateUser")
	public String updateUser(@Valid User newUserInfo, BindingResult result, @RequestParam String newPassword, HttpSession session, RedirectAttributes redirAttr){
		User user = getLoggedInUser(session);

		if(result.hasErrors()) {
			return "updateUser";
		}

		userService.update(user, newUserInfo, newPassword);

		redirAttr.addFlashAttribute("message", "User info updated");
		return "index";

	}

	@GetMapping("/user/{id}")
	public @ResponseBody User getUserById(@PathVariable Long id){
		return userService.findById(id);
	}
	
	private User getLoggedInUser(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			SecurityContextImpl sci = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
			String username = sci.getAuthentication().getName();

			user = userService.findByUsername(username);
			session.setAttribute("user", user);
		}
		return user;
	}
}
