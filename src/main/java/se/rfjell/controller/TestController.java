package se.rfjell.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import se.rfjell.model.Center;
import se.rfjell.model.StudentTest;
import se.rfjell.model.Test;
import se.rfjell.model.User;
import se.rfjell.service.CenterService;
import se.rfjell.service.ProviderService;
import se.rfjell.service.StudentTestService;
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
	@Autowired
	private StudentTestService studentTestService;

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

	@PostMapping("/booktest")
	public String booktest(
			@RequestParam(value="test") Long testId,
			@RequestParam(value="center") Long centerId,
			@RequestParam(value="testDate") String testDate,
			Model model,
			HttpSession session,
			RedirectAttributes redirAttr) {

		if(centerId == null) {
			model.addAttribute("error","You need to select a test center before submitting");
			return "testslist";
		}
		Center center = centerService.findById( centerId );

		User user = userService.getLoggedInUser(session);

		if(testId == null) {
			model.addAttribute("error","You need to select a test from the list before submitting");
			return "testslist";
		}
		Test test = testService.findById( testId );

		StudentTest studentTest = new StudentTest();
		studentTest.setTester( user );
		studentTest.setTest( test );
		studentTest.setCenter( center );

		Date d = parseDate(testDate);
		if( d == null ) {
			model.addAttribute("error", "Error parsing date");
			return "testslist";
		}

		//TODO: See if center is available at testDate

		studentTest.setTestDate( d );
		studentTest.setBookingDate( new Date() );
		studentTest.setPayed("N");
		System.out.println(testDate);

		studentTestService.save(studentTest);
		userService.update(user, studentTest);

		redirAttr.addFlashAttribute("message", "Test booked. Check your email for confirmation.");

		return "redirect:index";
	}

	private Date parseDate(String date) {
		Date parsed = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			parsed = format.parse(date);
		} catch(ParseException pe) {
			pe.printStackTrace();
			return null;
		}
		return parsed;
	}
}
