package se.rfjell.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.rfjell.model.Provider;
import se.rfjell.repository.ProviderRepository;

@Service("providerService")
public class ProviderServiceImpl implements ProviderService {

	@Autowired
	private ProviderRepository providerRepository;

	@Override
	public Provider save(Provider provider) {
		return providerRepository.save(provider);
	}

	@Override
	public Provider findByName(String name) {
		return providerRepository.findByName(name);
	}

	@Override
	public Provider findById(long id) {
		return providerRepository.findOne(id);
	}

	@Override
	public List<Provider> findAllProviders() {
		return providerRepository.findAll();
	}

}
