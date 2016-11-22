package se.rfjell.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.rfjell.model.Test;
import se.rfjell.repository.TestRepository;

@Service("testService")
public class TestServiceImpl implements TestService {

	@Autowired
	private TestRepository testRepository;

	@Override
	public Test save(Test test) {
		return testRepository.save(test);
	}

	@Override
	public List<Test> findAllTests() {
		return testRepository.findAll();
	}

	@Override
	public Test findById(Long id) {
		return testRepository.findOne(id);
	}

}
