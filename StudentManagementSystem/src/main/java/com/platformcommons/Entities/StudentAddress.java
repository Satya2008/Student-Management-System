package com.platformcommons.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotEmpty
    private String area;

    @NotEmpty
    private String state;

    @NotEmpty
    private String district;

    @NotEmpty
    private String pincode;

    @NotEmpty
    private String addressType;
    
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}