package com.platformcommons.Models;

import java.time.LocalDate;

import com.platformcommons.Entities.GENDER;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTOupdate {
	private String studentName;
	private LocalDate studentDateOfBirth;
	private GENDER studentGender;
	private String uniqueStudentCode;
	private String email;
	private String mobileNo;
	private String parentName;
}
