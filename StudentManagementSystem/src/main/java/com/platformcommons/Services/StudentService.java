package com.platformcommons.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.platformcommons.Entities.Course;
import com.platformcommons.Entities.Student;
import com.platformcommons.Exceptions.StudentExceptions;
import com.platformcommons.Models.StudentDTOupdate;

@Service
public interface StudentService {
   
	  Student updateStudentProfile(Long studentId, StudentDTOupdate updatedStudent) throws StudentExceptions;
	    List<Course> getAssignedCourses(Long studentId) throws StudentExceptions;
	    void leaveCourse(Long studentId, Long courseId) throws StudentExceptions;
		
	    Optional<Student> findByUniqueStudentCode(String uniqueStudentCode);
	
}
