package se.rfjell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.rfjell.model.StudentTest;

@Repository("studentTestRepository")
public interface StudentTestRepository extends JpaRepository<StudentTest, Long> {

}
