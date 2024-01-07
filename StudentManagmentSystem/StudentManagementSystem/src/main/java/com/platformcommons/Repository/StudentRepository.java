package com.platformcommons.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.platformcommons.Entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
  
	    @Query("SELECT s FROM Student s WHERE s.studentName LIKE %:studentName%")
	    List<Student> findByStudentNameContaining(@Param("studentName") String studentName);
	    Optional<Student> findByUniqueStudentCode(String uniqueStudentCode);
}
