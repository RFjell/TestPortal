package se.rfjell.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import se.rfjell.model.StudentTest;
import se.rfjell.model.User;
import se.rfjell.service.CenterService;
import se.rfjell.service.ProviderService;
import se.rfjell.service.TestService;
import se.rfjell.service.UserService;

@Controller
public class TestController {

	@Autowired
	private TestService testService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProviderService providerService;
	@Autowired
	private CenterService centerService;

	@GetMapping("/mytests")
	public String myTestPage(Model model, HttpSession session) {
		User user = userService.getLoggedInUser(session);
		List<StudentTest> tests = user.getStudentTests();
		model.addAttribute("tests", tests);
		return "mytests";
	}

	@GetMapping("/testslist")
	public String tests(Model model) {
		model.addAttribute("providers", providerService.findAllProviders());
		model.addAttribute("centers", centerService.findAllCenters());
		return "testslist";
	}
}
