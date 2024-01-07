package com.platformcommons.Services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platformcommons.Entities.Admin;
import com.platformcommons.Entities.Course;
import com.platformcommons.Entities.Role;
import com.platformcommons.Entities.Student;
import com.platformcommons.Entities.StudentAddress;
import com.platformcommons.Exceptions.AdminException;
import com.platformcommons.Models.CourseDTO;
import com.platformcommons.Models.StudentAddressDTO;
import com.platformcommons.Models.StudentDTO;
import com.platformcommons.Repository.AdminRepository;
import com.platformcommons.Repository.CourseRepository;
import com.platformcommons.Repository.StudentAddressRepository;
import com.platformcommons.Repository.StudentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentAddressRepository studentAddressRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AdminRepository adminRepository;
    
    
    @Override
    public Admin registerAdmin(Admin admin) throws AdminException {
        try {
    
            return adminRepository.save(admin);
        } catch (Exception e) {
            throw new AdminException("Error while registering admin");
        }
    }

    @Override
    public Student admitStudent(StudentDTO studentDTO) throws AdminException {
        try {
            Student student = modelMapper.map(studentDTO, Student.class);
            student.setRole(Role.STUDENT);
            return studentRepository.save(student);
        } catch (Exception e) {
            throw new AdminException("Error while admitting student");
        }
    }

    @Override
    public Student addAddressOfstudent(Long studentId, StudentAddressDTO studentAddressDTO) throws AdminException {
        try {
            Optional<Student> optionalStudent = studentRepository.findById(studentId);

            if (optionalStudent.isPresent()) {
                Student student = optionalStudent.get();

                StudentAddress studentAddress = modelMapper.map(studentAddressDTO, StudentAddress.class);
                studentAddress.setStudent(student);

                student.getAddresses().add(studentAddress);

                studentRepository.save(student);
                studentAddressRepository.save(studentAddress);

                return student;
            } else {
                throw new EntityNotFoundException("Student not found with ID: " + studentId);
            }
        } catch (Exception e) {
            throw new AdminException("Error while adding address to student");
        }
    }

    @Override
    public List<Student> getStudentsByName(String studentName) throws AdminException {
        try {
            return studentRepository.findByStudentNameContaining(studentName);
        } catch (Exception e) {
            throw new AdminException("Error while getting students by name");
        }
    }

    @Override
    public Course uploadCourse(CourseDTO courseDTO) throws AdminException {
        try {
            Course course = modelMapper.map(courseDTO, Course.class);
            return courseRepository.save(course);
        } catch (Exception e) {
            throw new AdminException("Error while uploading course");
        }
    }

    @Override
    public void assignCoursesToStudent(Long studentId, Long courseId) throws AdminException {
        try {
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseId));

            List<Course> studentCourses = student.getCourses();
            if (!studentCourses.contains(course)) {
                studentCourses.add(course);
                studentRepository.save(student);
            }
        } catch (Exception e) {
            throw new AdminException("Error while assigning courses to student");
        }
    }

    @Override
    public List<Student> getStudentsByCourse(Long courseId) throws AdminException {
        try {
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseId));

            return course.getStudents();
        } catch (Exception e) {
            throw new AdminException("Error while getting students by course");
        }
    }
    
	@Override
	public Optional<Admin> findByEmail(String Email) {
		Optional<Admin> user= adminRepository.findByEmail(Email);
		 if(user.isEmpty()) throw new AdminException("No admin found");
		 return user;
	}

}
