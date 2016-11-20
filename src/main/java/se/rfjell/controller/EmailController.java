package se.rfjell.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import se.rfjell.config.MyUserDetailsService;
import se.rfjell.model.User;
import se.rfjell.service.UserService;

@Controller
public class EmailController {

	@Autowired
	UserService userService;

	@Autowired
	MyUserDetailsService userDetailsService;


	@GetMapping("/validate-email")
	public String validateEmail(@RequestParam String id, RedirectAttributes redirAttr) {
		User user = userService.findByConfirmationLink(id);
		if( user != null ) {
			redirAttr.addFlashAttribute("message", "User validated.");

			//Auto sign in newly validated user
			UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
			Authentication auth = new UsernamePasswordAuthenticationToken(
									userDetails.getUsername(),
									userDetails.getPassword(),
									userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
			
			return "redirect:index";
		} else {
			redirAttr.addFlashAttribute("message", "User could not be validated.");
			return "redirect:index";
		}
	}
}
