package com.platformcommons.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.platformcommons.Entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
   
}