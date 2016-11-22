package se.rfjell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.rfjell.model.Provider;

@Repository("providerRepository")
public interface ProviderRepository extends JpaRepository<Provider, Long> {

    Provider findByName(String name);

}
