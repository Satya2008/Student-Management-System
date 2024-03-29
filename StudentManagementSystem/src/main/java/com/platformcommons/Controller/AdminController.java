package com.platformcommons.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platformcommons.Entities.Admin;
import com.platformcommons.Entities.Course;
import com.platformcommons.Entities.Student;
import com.platformcommons.Exceptions.AdminException;
import com.platformcommons.Models.CourseDTO;
import com.platformcommons.Models.StudentAddressDTO;
import com.platformcommons.Models.StudentDTO;
import com.platformcommons.Services.AdminService;

import jakarta.persistence.EntityNotFoundException;
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    
    @Autowired
	private PasswordEncoder passwordEncoder;
    
	@PostMapping("/signin")
	public ResponseEntity<String> logInUserHandler(Authentication auth) throws AdminException {
		Admin admin = adminService.findByEmail(auth.getName()).get();
		return new ResponseEntity<>(admin.getEmail() + " Logged In Successfully", HttpStatus.ACCEPTED);
	}


    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) throws AdminException {
        try {
        	admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            Admin registeredAdmin = adminService.registerAdmin(admin);
            
            return new ResponseEntity<>(registeredAdmin, HttpStatus.OK);
        } catch (AdminException e) {
            return new ResponseEntity<>("Error registering admin: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/admitStudent")
    public ResponseEntity<?> admitStudent(@RequestBody StudentDTO studentDTO)  throws AdminException  {
        try {
        	studentDTO.setPassword(passwordEncoder.encode(studentDTO.getPassword()));
            Student admittedStudent = adminService.admitStudent(studentDTO);
            return new ResponseEntity<>(admittedStudent, HttpStatus.OK);
        } catch (AdminException e) {
            return new ResponseEntity<>("Error admitting student: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addMoreAddress/{studentId}")
    public ResponseEntity<?> addMoreAddressOfStudent(@PathVariable Long studentId,
            @RequestBody StudentAddressDTO studentAddressDTO) {
        try {
            Student updatedStudent = adminService.addAddressOfstudent(studentId, studentAddressDTO);
            return new ResponseEntity<>("Address has beed saved", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Student not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding address to student: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/SearchStudentByName")
    public ResponseEntity<?> getStudentsByName(@RequestParam String studentName) {
        try {
            List<Student> students = adminService.getStudentsByName(studentName);
            if(students.isEmpty()) {
            	 return new ResponseEntity<>("No Student found by given name " + studentName
            			 , HttpStatus.OK);
            }
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving students by name: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/uploadCourse")
    public ResponseEntity<?> uploadCourse(@RequestBody CourseDTO courseDTO) {
        try {
            Course uploadedCourse = adminService.uploadCourse(courseDTO);
            return new ResponseEntity<>(uploadedCourse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error uploading course: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{studentId}/assignCourse")
    public ResponseEntity<?> assignCourseToStudent(@PathVariable Long studentId, @RequestParam Long courseId) {
        try {
            adminService.assignCoursesToStudent(studentId, courseId);
            return new ResponseEntity<>("Course assigned to student successfully", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error assigning course to student: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{courseId}/students")
    public ResponseEntity<?> getStudentsByCourse(@PathVariable Long courseId) {
        try {
            List<Student> students = adminService.getStudentsByCourse(courseId);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving students by course: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
