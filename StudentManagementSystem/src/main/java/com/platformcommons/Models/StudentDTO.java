package com.platformcommons.Models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.platformcommons.Entities.GENDER;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
	private String studentName;
	private LocalDate studentDateOfBirth;
	private GENDER studentGender;
	private String uniqueStudentCode;
	private String email;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String mobileNo;
	private String parentName;
}
