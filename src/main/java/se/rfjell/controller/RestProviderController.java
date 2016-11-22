package se.rfjell.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.rfjell.model.Provider;
import se.rfjell.model.Test;
import se.rfjell.service.ProviderService;

@RestController
public class RestProviderController {

	@Autowired
	ProviderService providerService;

	@GetMapping("/tests")
	public List<Test> getTests(@RequestParam long providerId) {
		Provider p = providerService.findById(providerId);
		if( p != null )
			return p.getTests();
		return null;
	}
}
