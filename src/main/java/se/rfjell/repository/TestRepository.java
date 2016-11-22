package se.rfjell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.rfjell.model.Test;

@Repository("testRepository")
public interface TestRepository  extends JpaRepository<Test, Long> {

}
