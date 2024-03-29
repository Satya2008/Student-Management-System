package com.platformcommons.Entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @NotEmpty(message = "Student name cannot be empty")
    @Size(min = 3, max = 50, message = "Student name must be between {min} and {max} characters")
    private String studentName;

    @NotNull(message = "Date of birth cannot be null")
    private LocalDate studentDateOfBirth;

    @Enumerated(EnumType.STRING)
    private GENDER studentGender;

    @NotEmpty(message = "Unique student code cannot be empty")
    @Column(unique = true)
    private String uniqueStudentCode;

    @Email(message = "Invalid email format")
    private String email;
     
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password cannot be null")
    private String password;

    
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private Role role;

   
    private String mobileNo;

    @NotEmpty(message = "Parent name cannot be empty")
    private String parentName;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<StudentAddress> addresses;

    @JsonIgnore
    @ManyToMany
    private List<Course> courses;
}
