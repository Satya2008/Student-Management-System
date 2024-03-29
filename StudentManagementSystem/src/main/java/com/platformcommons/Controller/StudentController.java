package com.platformcommons.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platformcommons.Entities.Admin;
import com.platformcommons.Entities.Course;
import com.platformcommons.Entities.Student;
import com.platformcommons.Exceptions.AdminException;
import com.platformcommons.Exceptions.StudentExceptions;
import com.platformcommons.Models.StudentDTO;
import com.platformcommons.Models.StudentDTOupdate;
import com.platformcommons.Services.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    

  	@PostMapping("/signin")
  	public ResponseEntity<String> logInUserHandler(Authentication auth) throws AdminException {
  		Student student = studentService.findByUniqueStudentCode(auth.getName()).get();
  		return new ResponseEntity<>(student.getUniqueStudentCode() + " Logged In Successfully", HttpStatus.ACCEPTED);
  	}

    
    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudentProfile(@PathVariable Long studentId,
                                                       @RequestBody StudentDTOupdate updatedStudent) {
        try {
            Student updatedStudentProfile = studentService.updateStudentProfile(studentId, updatedStudent);
            return  new  ResponseEntity<>(updatedStudentProfile, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{studentId}/courses")
    public ResponseEntity<List<Course>> getAssignedCourses(@PathVariable Long studentId) {
        try {
            List<Course> assignedCourses = studentService.getAssignedCourses(studentId);
            return  new  ResponseEntity<>(assignedCourses, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<?> leaveCourse(@PathVariable Long studentId, @PathVariable Long courseId) throws StudentExceptions {
        try {
            studentService.leaveCourse(studentId, courseId);
            return new  ResponseEntity<String>("Course left successfully", HttpStatus.ACCEPTED);
        } catch (StudentExceptions e) {
            return new  ResponseEntity<String>("Find error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new  ResponseEntity<String>("Somthing wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
