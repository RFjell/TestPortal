package se.rfjell.service;

import java.util.List;

import se.rfjell.model.StudentTest;

public interface StudentTestService {
	StudentTest save(StudentTest test);
    List<StudentTest> findAllTest();
}
