package se.rfjell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.rfjell.model.Center;

@Repository("centerRepository")
public interface CenterRepository extends JpaRepository<Center, Long> {

}
