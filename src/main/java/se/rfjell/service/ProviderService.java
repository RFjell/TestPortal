package se.rfjell.service;

import java.util.List;

import se.rfjell.model.Provider;

public interface ProviderService {
	Provider save(Provider provider);
	Provider findByName(String name);
	Provider findById(long id);
	List<Provider> findAllProviders();
}
