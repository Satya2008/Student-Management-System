package com.platformcommons.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @NotEmpty
    private String courseName;

    @NotEmpty
    private String description;

    @NotEmpty
    private String courseType;

    private int duration;

    @NotEmpty
    private String topics;

    @JsonIgnore
    @ManyToMany(mappedBy = "courses")
    private List<Student> students;
}