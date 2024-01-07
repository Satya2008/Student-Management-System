package com.platformcommons.Services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platformcommons.Entities.Course;
import com.platformcommons.Entities.Student;
import com.platformcommons.Exceptions.StudentExceptions;
import com.platformcommons.Models.StudentDTO;
import com.platformcommons.Repository.CourseRepository;
import com.platformcommons.Repository.StudentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Student updateStudentProfile(Long studentId, StudentDTO updatedStudent) throws StudentExceptions {
        try {
            Student existingStudent = studentRepository.findById(studentId)
                    .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

            modelMapper.map(updatedStudent, existingStudent);

            return studentRepository.save(existingStudent);
        } catch (Exception e) {
            throw new StudentExceptions("Error while updating student profile");
        }
    }

    @Override
    public List<Course> getAssignedCourses(Long studentId) throws StudentExceptions {
        try {
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

            return student.getCourses();
        } catch (Exception e) {
            throw new StudentExceptions("Error while getting assigned courses");
        }
    }

    @Override
    public void leaveCourse(Long studentId, Long courseId) throws StudentExceptions {
        try {
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseId));

            List<Course> studentCourses = student.getCourses();
            if (studentCourses.contains(course)) {
                studentCourses.remove(course);
                studentRepository.save(student);
            }
        } catch (Exception e) {
            throw new StudentExceptions("Error while leaving course");
        }
    }

	@Override
	public Optional<Student> findByUniqueStudentCode(String uniqueStudentCode) {
	  
	        return studentRepository.findByUniqueStudentCode(uniqueStudentCode);
	    }
	}

