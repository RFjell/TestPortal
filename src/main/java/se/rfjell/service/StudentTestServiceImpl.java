package se.rfjell.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.rfjell.model.StudentTest;
import se.rfjell.repository.StudentTestRepository;

@Service("studentTestService")
public class StudentTestServiceImpl implements StudentTestService {

	@Autowired
	private StudentTestRepository studentTestRepository;

	@Override
	public StudentTest save(StudentTest test) {
		return studentTestRepository.save(test);
	}

	@Override
	public List<StudentTest> findAllTest() {
		return studentTestRepository.findAll();
	}

}
