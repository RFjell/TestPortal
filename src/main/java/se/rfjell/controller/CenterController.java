package se.rfjell.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import se.rfjell.model.Center;
import se.rfjell.service.CenterService;

@Controller
public class CenterController {

	@Autowired
	private CenterService centerService;

	@GetMapping(value="/centers")
	public @ResponseBody List<Center> findAllCenters() {
		List<Center> asd = centerService.findAllCenters();
		return asd;
	}

	@GetMapping(value="/center/{id}")
	public @ResponseBody Center findCenter(@PathVariable("id") Long centerId) {
		return centerService.findById(centerId);
	}


}
