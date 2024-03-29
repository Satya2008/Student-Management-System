package com.platformcommons.Services;

import java.util.List;
import java.util.Optional;

import com.platformcommons.Entities.Admin;
import com.platformcommons.Entities.Course;
import com.platformcommons.Entities.Student;
import com.platformcommons.Exceptions.AdminException;
import com.platformcommons.Models.CourseDTO;
import com.platformcommons.Models.StudentAddressDTO;
import com.platformcommons.Models.StudentDTO;

public interface AdminService {
    Student admitStudent(StudentDTO studentDTO) throws AdminException;
    Student addAddressOfstudent(Long studentId,StudentAddressDTO studentAddressDTO) throws AdminException;
    List<Student> getStudentsByName(String studentName) throws AdminException;
    void assignCoursesToStudent(Long studentId, Long courseId) throws AdminException;
    Course uploadCourse(CourseDTO courseDTO) throws AdminException;
    List<Student> getStudentsByCourse(Long courseId) throws AdminException;
    Admin registerAdmin(Admin admin) throws AdminException;
    Optional<Admin> findByEmail(String Email);
}
