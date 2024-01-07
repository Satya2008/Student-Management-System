package com.platformcommons.Models;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    @NotEmpty
    private String courseName;

    @NotEmpty
    private String description;

    @NotEmpty
    private String courseType;

    private int duration;

    @NotEmpty
    private String topics;

}
