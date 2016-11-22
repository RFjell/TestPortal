package se.rfjell.service;

import java.util.List;

import se.rfjell.model.Test;

public interface TestService {
	Test save(Test test);
	List<Test> findAllTests();
	Test findById(Long id);
}
